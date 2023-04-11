import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Formatter;

public class Game implements ActionListener {
    JFrame f;
    JPanel p;
    JLabel levelLabel, clueLabel, wordLabel, lifeLabel, scoreLabel;
    JButton homeButton;
    JButton[] alphabetButtons;
    char[] alphabet, tempChar;
    String name, temp;
    String[] words, clues;
    int difficulty, level, score, life;
    Formatter formatter;
    public Game(String n, int d, int l, int s) {
        f = new JFrame("HangTam");
        p = new JPanel();

        name = n;
        difficulty = d;
        level = l;
        score = s;
        life = 8;

        if (d == 0) {
            words = new String[]{"TAMTAM", "NICANOR REYES", "MORAYTA", "SEVEN", "THREE", "FAR EASTERN UNIVERSITY", "PANDOG", "BE BRAVE", "GREEN AND GOLD", "TAMARAWS"};
            clues = new String[]{"FEU Mascot", "Founder of FEU", "Location of FEU Main and FIT", "Number of schools in the FEU Group", "Number of semesters per year in FIT", "FEU stands for?", "Best selling snack in FIT", "FEU Motto", "Colors of FEU", "Nickname of FEU students"};
        }
        else {
            words = new String[]{"FEU EAST ASIA COLLEGE", "UPRIGHTNESS", "MICHAEL ALBA", "CONCIERTO PIYU", "TATAK TAMARAW", "FEU DILIMAN", "FORTITUDE", "HENRY SY", "YOUNG HEARTS", "EXCELLENCE"};
            clues = new String[]{"First name of FIT", "U in FEU core values", "Current president of FEU", "FEU Foundation Day Concert", "FEU Opening Event for Freshmen Students", "Sister School of FIT in Q.C.", "F in FEU Core Values", "FEU Alumni and Founder of SM", "In thy happy halls, our ________ saw the light", "E in FEU Core Values"};
        }

        alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F',
                              'G', 'H', 'I', 'J', 'K', 'L',
                              'M', 'N', 'O', 'P', 'Q', 'R',
                              'S', 'T', 'U', 'V', 'W', 'X',
                              'Y', 'Z'};

        levelLabel = new JLabel("LEVEL " + (level+1), SwingConstants.CENTER);
        clueLabel = new JLabel(clues[level], SwingConstants.CENTER);
        wordLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel = new JLabel(""+score, SwingConstants.LEFT);
        lifeLabel = new JLabel("Life: " + life, SwingConstants.RIGHT);

        alphabetButtons = new JButton[26];
        for (int i = 0; i < 26; i++) {
            alphabetButtons[i] = new JButton(Character.toString(alphabet[i]));
            alphabetButtons[i].addActionListener(this);
            p.add(alphabetButtons[i]);
        }

        homeButton = new JButton("");
        homeButton.addActionListener(this);
        
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);

        temp = "";
        for (int i = 0; i < words[level].length(); i++) {
            wordLabel.setText(wordLabel.getText() + "_");
            temp += "_";
        }
        tempChar = temp.toCharArray();

        layout();
        openFile();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to save your game?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == 0) {
                Save save = new Save(name, difficulty, level, score);
            }
            else {
                MainMenu mainMenu = new MainMenu();
            }
            f.dispose();
        }
        else {
            boolean contains = false;
            for (int i = 0; i < 26; i++) {
                if (alphabetButtons[i] == e.getSource()) {
                    alphabetButtons[i].setVisible(false);
                    for (int j = 0; j < words[level].length(); j++) {
                        if (words[level].charAt(j) == alphabet[i]) {
                            contains = true;
                            tempChar[j] = alphabet[i];
                        }
                    }
                    temp = String.valueOf(tempChar);
                    wordLabel.setText(temp);
                    if (temp.equals(words[level])) {
                        JOptionPane.showMessageDialog(null, "Level solved!");
                        f.dispose();
                        if (difficulty == 0)
                            score = score + (life+2);
                        else
                            score = score + (life+2)*2;
                        if (level == 9) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You have completed HangTam!");
                            formatter.format("%s%n", score);
                            formatter.close();
                            f.dispose();
                            MainMenu mainMenu = new MainMenu();
                        }
                        else {
                            Game game = new Game(name, difficulty, level + 1, score);
                        }
                    }
                }
            }
            if (!contains) {
                life--;
                lifeLabel.setText("Life: " + life);
                if (life == 0) {
                    JOptionPane.showMessageDialog(null, "Game over!");
                    formatter.format("%s%n", score);
                    f.dispose();
                    formatter.close();
                    Music.setVolume(-30);
                    MainMenu mainMenu = new MainMenu();
                }
            }
        }
    }
    public void openFile() {
        try {
            FileWriter fileWriter = new FileWriter("ranking.txt", true);
            formatter = new Formatter(fileWriter);
        }
        catch (Exception e) {
            System.out.println("Error.");
        }
    }
    public void layout() {
        f.setSize(800, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        ImageIcon backgroundIcon = new ImageIcon(this.getClass().getResource("/images/game.png"));
        JLabel backgroundHolder = new JLabel(backgroundIcon);
        backgroundHolder.setSize(800,600);

        p.setSize(300, 250);
        p.setLayout(new GridLayout(5, 6, 5,5));

        levelLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        clueLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        wordLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 42));
        lifeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        
        levelLabel.setForeground(Color.WHITE);
        clueLabel.setForeground(Color.WHITE);
        wordLabel.setForeground(Color.WHITE);
        lifeLabel.setForeground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);
        
        levelLabel.setBounds(0, 20, 800, 40);
        clueLabel.setBounds(0, 70, 800, 40);
        wordLabel.setBounds(0, 100, 800, 40);
        scoreLabel.setBounds(460,460,200,40);
        lifeLabel.setBounds(700,10,70,20);
        homeButton.setBounds(10,10,70,40);
        p.setBounds(50, 200, 360, 300);
        
        p.setBackground(Color.BLACK);
        
        f.add(levelLabel);
        f.add(clueLabel);
        f.add(wordLabel);
        f.add(lifeLabel);
        f.add(scoreLabel);
        f.add(homeButton);
        f.add(p);
        f.add(backgroundHolder);
    }
}