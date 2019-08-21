import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class OtherPanel extends JPanel {

    public ImageIcon digitalScreenIcon = new ImageIcon(getClass().getResource("black.png"));
    public JLabel centerLabel = new JLabel();
    public JLabel rightLabel = new JLabel("0", digitalScreenIcon,JLabel.CENTER);
    public JButton smileyFace;
    public JLabel flagCount = new JLabel("", digitalScreenIcon,JLabel.CENTER);
    public ImageIcon smileyFaceIcon= new ImageIcon(getClass().getResource("happyFace.png"));
    public ImageIcon deadFace =  new ImageIcon(getClass().getResource("deadFace.png"));
    public Timer time = new Timer(1000,new Handler());
    public int stopwatch = 0;
    public OtherPanel(){
        super();
        setLayout(new BorderLayout());

        //time.start();

        rightLabel.setLayout(new FlowLayout());
        rightLabel.setFont(new Font("Unispace",Font.PLAIN,30 ));
        rightLabel.setForeground(Color.red);
        rightLabel.setPreferredSize(new Dimension(100,50));
        rightLabel.setHorizontalTextPosition(JLabel.CENTER);
        rightLabel.setVerticalTextPosition(JLabel.CENTER);
        //rightLabel.setBorder(BorderFactory.createLineBorder(Color.black));


        smileyFace = new JButton();
        smileyFace.setPreferredSize(new Dimension(40,40));
        smileyFace.addActionListener(new Handler());
        smileyFace.setIcon(smileyFaceIcon);

        centerLabel.setLayout(new FlowLayout());
        centerLabel.setPreferredSize(new Dimension(50,50));
        centerLabel.add(smileyFace);


        flagCount.setLayout(new FlowLayout());
        flagCount.setFont(new Font("Unispace",Font.PLAIN,30 ));
        flagCount.setHorizontalTextPosition(JLabel.CENTER);
        flagCount.setVerticalTextPosition(JLabel.CENTER);
        flagCount.setPreferredSize(new Dimension(100,50));
        flagCount.setForeground(Color.red);


        updateFlagCount();
        add(flagCount,BorderLayout.WEST);
        add(centerLabel,BorderLayout.CENTER);
        add(rightLabel,BorderLayout.EAST);
        setBackground(Color.darkGray);
    }
    public void updateFlagCount(){
        flagCount.setText("0"+Integer.toString(Main.myBoard.remainingFlags));
    }
    public void resetTimer(){
        stopwatch = 0;
        time.stop();
        rightLabel.setText("0");
    }
    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==smileyFace){
                Main.newGame();
            }
            if (e.getSource()==time){
                if (stopwatch<999){
                    stopwatch++;
                }
                rightLabel.setText(Integer.toString(stopwatch));
            }


        }
    }
}
