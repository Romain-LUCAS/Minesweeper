public class Preset {
    int col = 0;
    int row = 0;
    int mines = 0;
    public static int customCol = 30;
    public static int customRow = 30;
    public static int customMines = 99;

    public static String presetDifficulty = "Intermediate";

    public Preset(String diff){
        if (diff.equals("Beginner")){
            this.col = 17;
            this.row = 15;
            this.mines = 40;
        } else if (diff.equals("Intermediate")){
            this.col = 24;
            this.row = 20;
            this.mines = 60;
        } else if (diff.equals("Expert")) {
            this.col = 30;
            this.row = 24;
            this.mines = 99;
        }else if (diff.equals("Custom")) {
            this.col = customCol;
            this.row = customRow;
            this.mines = customMines;
        }
    }
}
