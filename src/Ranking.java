import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ranking implements ActionListener {
    JFrame f;
    JPanel p1, p2, p3;
    JPanel[] panels;
    JLabel label;
    JLabel[] labels;
    JButton backButton;
    Scanner scanner;
    ArrayList<Integer> scores = new ArrayList<Integer>();
    boolean exists = true;
    public Ranking() {
        f =  new JFrame("HangTam");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        label = new JLabel("LEADERBOARD", SwingConstants.CENTER);
        p1.add(label);

        panels = new JPanel[25];
        labels = new JLabel[25];

        backButton = new JButton("BACK");
        backButton.addActionListener(this);
        
//        backButton.setBounds(340, 480, 130, 40); 
//        backButton.setOpaque(false);
//        backButton.setContentAreaFilled(false);
//        backButton.setBorderPainted(false);
        p3.add(backButton);

        for (int i = 0; i < 25; i++) {
         
            panels[i] = new JPanel();
            labels[i] = new JLabel("Top " + (i+1) + ": ", SwingConstants.LEFT);

            panels[i].setBorder(BorderFactory.createLineBorder(Color.black));

            p2.add(panels[i]);
            panels[i].add(labels[i]);

            if (i == 0)
                panels[i].setBackground(Color.YELLOW);
            else if (i == 1)
                panels[i].setBackground(Color.GREEN);
            else if (i == 2)
                panels[i].setBackground(Color.CYAN);
            else if (i < 10)
                panels[i].setBackground(Color.PINK);
            else
                panels[i].setBackground(Color.WHITE);
        }

        layout();
        openFile();
        if (exists)
            readFile();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        f.dispose();
        Music.setVolume(-30);
        MainMenu mainMenu = new MainMenu();
    }
    public void openFile() {
        try {
            scanner = new Scanner(new File("ranking.txt"));
        }
        catch (Exception e) {
            exists = false;
        }
    }
    public void readFile() {
        while (scanner.hasNext()) {
            scores.add(Integer.parseInt(scanner.next()));
        }
        Collections.sort(scores);
        Collections.reverse(scores);
        for (int i = 0; i < scores.size(); i++)
            labels[i].setText("Top " + (i+1) + ": " + scores.get(i) + " points");
        scanner.close();
    }
    public void layout() {
        f.setSize(800, 600);
        
        ImageIcon backgroundIcon = new ImageIcon(this.getClass().getResource("/images/leaderboard.png"));
        JLabel backgroundHolder = new JLabel(backgroundIcon);
        backgroundHolder.setSize(800,600);
        
        
        f.setLayout(new BorderLayout());
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        

        p1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        p2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        p3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p2.setLayout(new GridLayout(5, 5, 20, 20));

        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.CENTER);
        f.add(p3, BorderLayout.SOUTH);
//        f.add(backgroundHolder);
    }
}
