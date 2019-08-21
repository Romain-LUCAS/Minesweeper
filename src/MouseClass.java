import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MouseClass extends MouseAdapter {
    private int x;
    private int y;
    Cell c;
    private ImageIcon shockedFace =  new ImageIcon(getClass().getResource("shockedFace.png"));
    private ImageIcon smileyFaceIcon= new ImageIcon(getClass().getResource("happyFace.png"));

    @Override
    public void mouseReleased(MouseEvent e){
        if (!Main.isFinished){
            Main.topPanel.smileyFace.setIcon(smileyFaceIcon);
            x = e.getX() / Main.myBoard.CELL_SIZE;
            y = e.getY() / Main.myBoard.CELL_SIZE;
            c = Main.myBoard.cellArray[x][y]; // cell at coordinates clicked

            if (e.isMetaDown()){ // right click event
                Main.myBoard.flagOrUnflagg(c);

            } else{ // left click event
                if (!(c.isFlagged || c.isClicked)) { // cant click flagged cells or clicked cells

                     if (c.hasMine) {
                         c.redMine = true;
                         Main.gameOver();
                    } else {
                         Main.myBoard.clickSafeField(x, y);
                         Main.myBoard.click(c);
                     }
                }
            }

            Main.myBoard.repaint();
            System.out.println("repainted");
            Main.myBoard.checkWin();
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (!Main.isFinished){
        Main.topPanel.smileyFace.setIcon(shockedFace);
        }
    }
}

