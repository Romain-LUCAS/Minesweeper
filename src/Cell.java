import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Cell {

    private static int totalMines;
    private static Random rng;


    public int x;
    public int y;
    public int proximity;
    public boolean isFlagged;
    public boolean hasMine;
    public boolean redMine;
    public boolean isClicked;
    public boolean notFound;

    //Constructor

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.proximity = -1;
        this.isFlagged = false;
        this.hasMine = false;
        this.redMine = false;
        this.isClicked = false;
        this.notFound = false;
    }
    //Methods


    @Override
    public String toString() {
        return String.format(getClass().getName() +
                ": x = %d, y = %d, proximity = %d, isClicked = %b,hasMine = %b, isFlagged = %b", this.x, this.y, this.proximity, this.isClicked, this.hasMine, this.isFlagged);

    }
}
