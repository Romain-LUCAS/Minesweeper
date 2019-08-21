import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Board extends JPanel {

    public final int COL = Main.myPreset.col;
    public final int ROW = Main.myPreset.row;
    public final int CELL_SIZE = 15;
    private  final int NUM_MINES = Main.myPreset.mines;
    public int remainingFlags = Main.myPreset.mines;
    private int totClicked = 0;


    public Cell[][] cellArray = new Cell[COL][ROW];
    public Cell c;

    public BufferedImage[] imageArray = new BufferedImage[9];
    public BufferedImage imageMine;
    public BufferedImage imageMineRed;
    public BufferedImage imageUnpressedButton;
    public BufferedImage imageFlag;
    public BufferedImage imageCross;
    public BufferedImage blackScreen;
    public ImageIcon coolFace= new ImageIcon(getClass().getResource("coolFace.png"));

    private Random rng;

//Constructor

    public Board() {
        //setLayout(new FlowLayout());
        setSize(500,500);
        setPreferredSize(new Dimension(COL*CELL_SIZE,ROW*CELL_SIZE));
        bufferImages();
        fillCellArray();
        addMines();
        assignCellValues();
        repaint();
    }

//Methods
    private void bufferImages() {
        for (int i = 0; i < 9; i++) {
            try {
                System.out.println(i);
                imageArray[i] = ImageIO.read(getClass().getResource(i+".png"));

            } catch (IOException e) {
                System.out.println("there was an IO exception in number images");
            }
        }
        try {
            imageMineRed = ImageIO.read(getClass().getResource("mineRed.png"));
            imageUnpressedButton = ImageIO.read(getClass().getResource("unpressedButton.png"));
            imageFlag = ImageIO.read(getClass().getResource("unpressedButtonFlagged.png"));
            imageCross = ImageIO.read(getClass().getResource("cross.png"));
            blackScreen = ImageIO.read(getClass().getResource("blackScreen.png"));
            imageMine = ImageIO.read(getClass().getResource("mine.png"));

        }catch (IOException e){
            System.out.println("there was an IO exception in other images");
        }
    }
    private void fillCellArray() {
        for (int x = 0; x < COL; x++) {
            for (int y = 0; y < ROW; y++) {
                cellArray[x][y] = new Cell(x, y);
            }
        }
    }

    private void addMines() {
        int addedMines = 0;
        rng = new Random();
        int rnd_x;
        int rnd_y;
        while (addedMines < NUM_MINES) {
            rnd_x = rng.nextInt(cellArray.length);
            rnd_y = rng.nextInt(cellArray[1].length);
            if (!cellArray[rnd_x][rnd_y].hasMine) { // checks if it has a mine before adding it
                cellArray[rnd_x][rnd_y].hasMine = true;
                addedMines++;
            }
        }
    }

    private void assignCellValues() {
        for (int x = 0; x < COL; x++) {
            for (int y = 0; y < ROW; y++) {
                if (!cellArray[x][y].hasMine){
                    cellArray[x][y].proximity = minesInCluster(x,y);
                }
            }
        }
    }
    public void clickSafeField(int x, int y){ // Recursive method to click all fields
        click(cellArray[x][y]);
        if (cellArray[x][y].proximity == 0){
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                     if (checkInBounds(x + dx,y + dy)) {
                         if (!cellArray[x + dx][y + dy].isClicked) {
                             click(cellArray[x + dx][y + dy]);
                             clickSafeField(x + dx, y + dy);
                         }
                     }
                }
            }
        }
    }

    private int minesInCluster(int x, int y) {
        //return number of mine in the 3*3 cell custer around cell (x,y)
        int mines = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (checkInBounds(x + dx,y + dy)){
                    if (cellArray[x + dx][y + dy].hasMine) {
                        mines++;
                    }
                }
            }
        }
        return mines;
    }
    private boolean checkInBounds(int x,int y){
       return (x >= 0 && x < COL && y >= 0 && y < ROW);
    }
    public void revealAllTiles() {
        for (int x = 0; x < COL; x++) {
            for (int y = 0; y < ROW; y++) {
                c = cellArray[x][y];
                if (!c.isClicked && !c.isFlagged && !c.hasMine) {
                    c.isClicked = true;
                    c.notFound = true;
                }
                if (c.hasMine && !c.isFlagged) {
                    c.isClicked = true;

                }
                if (!c.hasMine && c.isFlagged){
                    c.notFound = true;
                }
            }
        }
    }
    public void click(Cell c){
      Main.topPanel.time.start();
        if (!c.isClicked){
          c.isClicked = true;
          totClicked++;
          System.out.println("clicked = " + totClicked);
         }
    }public void flagOrUnflagg(Cell c){
        if (remainingFlags > 0) {
            c.isFlagged ^= true; //toggle boolean
            if (c.isFlagged) {
                remainingFlags -= 1;
            } else {
                remainingFlags += 1;
            }
            Main.topPanel.updateFlagCount();
        } else if(c.isFlagged){
            c.isFlagged = false;
            remainingFlags += 1;
            Main.topPanel.updateFlagCount();
    }
}

    public void checkWin(){
        if (totClicked >= (COL*ROW - NUM_MINES)){
            Main.topPanel.smileyFace.setIcon(coolFace);
            Main.winEndGame();
        }
    }

    private int paintCase(int x,int y) {
        c = cellArray[x][y];
        if (c.redMine){
            return 1;}
        else if (c.isFlagged){
            if (c.notFound) {
                return 6;
            }else {
                return 2;}}
        else if (!c.isClicked){
            return 3;}
        else if (c.hasMine){
            return 4;}
        else if (c.proximity >= 0){
            return 5;}
        else if (c.proximity >= 0){
            return 5;
            }
        System.out.println("no case was found ?!");
        System.out.println(cellArray[x][y]);
        return 6;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < COL; x++) {
            for (int y = 0; y < ROW; y++) {
                switch (paintCase(x, y)) {

                    case 1:
                        g.drawImage(imageMineRed, x * CELL_SIZE, y * CELL_SIZE, null);
                        break;
                    case 2:
                        g.drawImage(imageFlag, x * CELL_SIZE, y * CELL_SIZE, null);
                        break;
                    case 3:
                        g.drawImage(imageUnpressedButton, x * CELL_SIZE, y * CELL_SIZE, null);
                        break;
                    case 4:
                        g.drawImage(imageMine, x * CELL_SIZE, y * CELL_SIZE, null);
                        break;
                    case 5:
                        g.drawImage(imageArray[cellArray[x][y].proximity], x * CELL_SIZE, y * CELL_SIZE, null);
                        break;
                    case 6:
                        g.drawImage(imageCross, x * CELL_SIZE, y * CELL_SIZE, null);
                        break;

                } // add blackScreen if fail
                if (cellArray[x][y].notFound) {
                    g.drawImage(blackScreen, x * CELL_SIZE, y * CELL_SIZE, null);

                }

            }
        }
    }

}
