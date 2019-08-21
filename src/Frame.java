import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame {

    private JLabel statusbar;
    private JLabel timer;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu options;
    private OptionWindow optionsWindow;

    public static JMenuItem newGame = new JMenuItem("New Game");
    public static JMenuItem beginner = new JMenuItem("Begginer");
    public static JMenuItem intermediate = new JMenuItem("Intermediate");
    public static JMenuItem expert = new JMenuItem(" Expert");
    public static JMenuItem custom = new JMenuItem(" Custom");

    public static JMenuItem configureCustom = new JMenuItem("Configure Custom Preset");



    public Frame(){
        super("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocation(100,100);
        //statusbar = new JLabel("Click to start counter");
        //timer = new JLabel("001");


        menuBar = new JMenuBar();
        file = new JMenu("File");
        options = new JMenu("Options");

        newGame.addActionListener(new Handler());
        newGame.setBorder(BorderFactory.createMatteBorder(0, 0, 1,0 , Color.BLACK));
        beginner.addActionListener(new Handler());
        intermediate.addActionListener(new Handler());
        expert.addActionListener(new Handler());
        expert.setBorder(BorderFactory.createMatteBorder(0, 0, 1,0 , Color.BLACK));
        custom.addActionListener(new Handler());
        configureCustom.addActionListener(new Handler());




        file.add(newGame);
        file.add(beginner);
        file.add(intermediate);
        file.add(expert);
        file.add(custom);

        options.add(configureCustom);

        menuBar.add(file);
        menuBar.add(options);
        setJMenuBar(menuBar);

    }
    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==newGame){
                Main.newGame();
            }
            if (e.getSource()==beginner){
                Preset.presetDifficulty = "Beginner";
                Main.newGame();
            }
            if (e.getSource()==intermediate) {
                Preset.presetDifficulty = "Intermediate";
                Main.newGame();
            }
            if (e.getSource()==expert) {
                Preset.presetDifficulty = "Expert";
                Main.newGame();
            }
            if (e.getSource()==custom) {
                Preset.presetDifficulty = "Custom";
                Main.newGame();
            }
            if (e.getSource()==configureCustom) {
                optionsWindow = new OptionWindow();
            }
        }
    }
}


