import javax.swing.*;
import java.awt.*;

public class Main {
    private static Frame myFrame;
    public static Board myBoard;
    public static OtherPanel topPanel;
    public static MouseClass listenner = new MouseClass();
    public static Preset myPreset = new Preset("Intermediate");
    public static boolean isFinished;

    public static void gameOver() {
        System.out.println("you lost the game");
        topPanel.time.stop();
        topPanel.smileyFace.setIcon(topPanel.deadFace);
        myBoard.revealAllTiles();
        isFinished = true;
    }

    public static void winEndGame (){
            System.out.println("you won the game");
            topPanel.time.stop();
            //topPanel.smileyFace.setIcon(topPanel.deadFace);
            //myBoard.revealAllTiles();
            isFinished = true;

    }
    public static void newGame(){

        topPanel.resetTimer();
        myPreset = new Preset(Preset.presetDifficulty);
        isFinished = false;
        topPanel.smileyFace.setIcon(topPanel.smileyFaceIcon);
        myFrame.remove(myBoard);
        //myFrame.remove(topPanel);
        myBoard = new Board();
        topPanel.updateFlagCount();
        myBoard.addMouseListener(listenner);
        myBoard.repaint();
        myFrame.add(myBoard, BorderLayout.CENTER);
        //myFrame.add(topPanel, BorderLayout.NORTH);
        myBoard.repaint();
        //topPanel.repaint();

        SwingUtilities.updateComponentTreeUI(myFrame);
        myFrame.pack();
        System.out.println("repainted");


    }

    public static void main(String[] args) {

        myFrame = new Frame();
        myBoard = new Board();
        topPanel = new OtherPanel();
        myBoard.addMouseListener(listenner);
        myBoard.repaint();
        myFrame.add(myBoard, BorderLayout.CENTER);
        myFrame.add(topPanel, BorderLayout.NORTH);
        myBoard.repaint();
        System.out.println("repainted");
        myFrame.setSize(800,800);
        myFrame.pack();
        //myFrame.setSize(myBoard.COL*myBoard.CELL_SIZE+15,myBoard.ROW*myBoard.CELL_SIZE+61);

        myFrame.setVisible(true);
    }
}
