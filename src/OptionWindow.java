import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionWindow extends JFrame {
        private JTextField row = new JTextField("Row");
        private JTextField col = new JTextField("Columns");
        private JTextField mines = new JTextField("Mines");

    private JTextField inputRow = new JTextField(String.valueOf(Preset.customRow));
    private JTextField inputCol = new JTextField(String.valueOf(Preset.customCol));
    private JTextField inputMines = new JTextField(String.valueOf(Preset.customMines));

    private JPanel centerPanel = new JPanel(new GridLayout(3,2));
    private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel botPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
   private JLabel options = new JLabel("Options", JLabel.CENTER);
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");
    private JButton startButton = new JButton("Start");



    public OptionWindow(){
        super("Minesweeper");
        setLayout(new BorderLayout());
        setSize(300,200);
        setLocation(150,150);
        setResizable(false);


        topPanel.add(options);

        formatUneditableText(col);
        formatUneditableText(row);
        formatUneditableText(mines);


        centerPanel.add(row);
        centerPanel.add(inputRow);
        centerPanel.add(col);
        centerPanel.add(inputCol);
        centerPanel.add(mines);
        centerPanel.add(inputMines);
        centerPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.darkGray));

        okButton.addActionListener(new Handler());
        okButton.setPreferredSize(new Dimension(80,30));
        cancelButton.addActionListener(new Handler());
        cancelButton.setPreferredSize(new Dimension(80,30));
        startButton.addActionListener(new Handler());
        startButton.setPreferredSize(new Dimension(80,30));

        botPanel.add(startButton);
        botPanel.add(okButton);
        botPanel.add(cancelButton);

        options.setForeground(Color.white);
        topPanel.setBackground(Color.darkGray);

        add(centerPanel, BorderLayout.CENTER);
        add(botPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);


        setVisible(true);

    }
    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==okButton) {
                setValuesColRowMines();
            }
            if (e.getSource()==cancelButton) {
                CloseFrame();
            }
            if (e.getSource()==startButton) {
                setValuesColRowMines();
                Preset.presetDifficulty = "Custom";
                Main.newGame();
            }
        }
    }
    public void CloseFrame(){
        super.dispose();
    }
    public void setValuesColRowMines(){
        Preset.customRow = Integer.valueOf(inputRow.getText());
        Preset.customCol = Integer.valueOf(inputCol.getText());
        Preset.customMines = Integer.valueOf(inputMines.getText());
        if (Preset.customRow* Preset.customCol < Preset.customMines){
            Preset.customMines = Preset.customRow *Preset.customCol;
        }
        CloseFrame();

    }
    public void formatUneditableText(JTextField t){
        t.setEditable(false);
        t.setHorizontalAlignment(SwingConstants.CENTER);
        t.setBackground(Color.lightGray);
        t.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.darkGray));
        t.setFont(new Font("Arial",Font.BOLD,12 ));
    }
}

