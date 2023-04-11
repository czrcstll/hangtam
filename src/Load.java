import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Load implements ActionListener {
    JFrame f;
    JPanel p;
    JLabel label;
    JButton[] slotButtons;
    Scanner scanner;
    String name;
    int difficulty, level, score, slot;
    boolean exists = true;
    public Load() {
        f = new JFrame("Load");
        p = new JPanel();

        label = new JLabel("Select a slot to load your game.", SwingConstants.CENTER);
        p.add(label);

        slotButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            slotButtons[i] = new JButton("Slot " + (i+1) + ": ");
            slotButtons[i].addActionListener(this);
            slotButtons[i].setHorizontalAlignment(SwingConstants.LEFT);
            p.add(slotButtons[i]);
        }

        layout();
        openFile();
        if (exists)
            readFile();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 5; i++) {
            if (slotButtons[i] == e.getSource())
                loadGame(i);
        }
    }
    public void openFile() {
        try {
            scanner = new Scanner(new File("saves.txt"));

        }
        catch (Exception e) {
            f.dispose();
            exists = false;
            JOptionPane.showMessageDialog(null, "There are no saved games.");
            Music.setVolume(-30);
            MainMenu mainMenu = new MainMenu();
        }
    }
    public void readFile() {
        while (scanner.hasNext()) {
            slot = Integer.parseInt(scanner.next());
            name = scanner.next();
            difficulty = Integer.parseInt(scanner.next());
            level = Integer.parseInt(scanner.next());
            score = Integer.parseInt(scanner.next());
            slotButtons[slot].setText("Slot " + (slot+1) + ": " + name);
        }
    }
    public void loadGame(int i) {
        scanner.close();
        openFile();
        while (scanner.hasNext()) {
            slot = Integer.parseInt(scanner.next());
            name = scanner.next();
            difficulty = Integer.parseInt(scanner.next());
            level = Integer.parseInt(scanner.next());
            score = Integer.parseInt(scanner.next());
            if (slot == i) {
                Game game = new Game(name, difficulty, level, score);
                f.dispose();
                scanner.close();
                break;
            }
        }
    }
    public void layout() {
        f.setSize(300, 400);
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        p.setLayout(new GridLayout(6,5,10,10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        f.add(p);
    }
}