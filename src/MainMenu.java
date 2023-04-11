import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    JFrame f;
    JButton newButton, loadButton, settingsButton, rankingButton, exitButton;
    JLabel backgroundHolder;
    ImageIcon backgroundIcon, newIcon, loadIcon, rankIcon, exitIcon, settingsIcon;
    Music music;
    public MainMenu() {
        f =  new JFrame("HangTam");
        
        backgroundIcon = new ImageIcon(this.getClass().getResource("/images/background.png"));
        backgroundHolder = new JLabel(backgroundIcon);
        backgroundHolder.setSize(800,600);
        
        newButton = new JButton();
        loadButton = new JButton();
        settingsButton = new JButton();
        rankingButton = new JButton();
        exitButton = new JButton();
        
        newButton.addActionListener(this);
        loadButton.addActionListener(this);
        settingsButton.addActionListener(this);
        rankingButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        music = new Music();
        music.playMusic();
        layout();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            String name = JOptionPane.showInputDialog(null, "Enter your name:", "Player");
            if (name != null) {
                String[] buttons = {"EASY", "HARD"};
                int difficulty = JOptionPane.showOptionDialog(null, "How hard do you want?", "Difficulty", JOptionPane.WARNING_MESSAGE, 3, null, buttons, buttons[0]);
                f.dispose();
                Game game = new Game(name, difficulty, 0, 0);
            }
        }
        else if (e.getSource() == loadButton) {
            f.dispose();
            Load load = new Load();
        }
        else if (e.getSource() == settingsButton) {
            Settings settings = new Settings();
        }
        else if (e.getSource() == rankingButton) {
            f.dispose();
            Ranking ranking = new Ranking();
        }
        else {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to exit HangTam?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == 0)
                f.dispose();
        }
    }
    public void layout() {
        f.setSize(800, 600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    
        newButton.setBounds(160, 500, 100, 40);
        loadButton.setBounds(280, 500, 110, 40);     
        rankingButton.setBounds(410, 500, 110, 40);
        exitButton.setBounds(540, 500, 110, 40);
        settingsButton.setBounds(40, 20, 40, 50);
        
        newButton.setOpaque(false);
        newButton.setContentAreaFilled(false);
        newButton.setBorderPainted(false);
        
        loadButton.setOpaque(false);
        loadButton.setContentAreaFilled(false);
        loadButton.setBorderPainted(false);
        
        rankingButton.setOpaque(false);
        rankingButton.setContentAreaFilled(false);
        rankingButton.setBorderPainted(false);
        
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        

        f.add(backgroundHolder);
        f.add(newButton);
        f.add(loadButton);
        f.add(settingsButton);
        f.add(rankingButton);
        f.add(exitButton);
    }
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}