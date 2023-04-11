import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.*;

public class Save implements ActionListener {
    JFrame f;
    JPanel p;
    JLabel label;
    JButton[] slotButtons;
    String name;
    int difficulty, level, score, slot;
    Formatter formatter;
    public Save(String n, int d, int l, int s) {
        f = new JFrame("Save");
        p = new JPanel();

        name = n;
        difficulty = d;
        level = l;
        score = s;

        label = new JLabel("<html>Select a slot to save your game.<br>Existing slot will be overwritten.<html>", SwingConstants.CENTER);
        p.add(label);

        slotButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            slotButtons[i] = new JButton("Slot " + (i+1));
            slotButtons[i].addActionListener(this);
            p.add(slotButtons[i]);
        }

        layout();
        openFile();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 5; i++) {
            if (slotButtons[i] == e.getSource()) {
                slot = i;
                formatter.format("%s %s %s %s %s%n", slot, name, difficulty, level, score);
            }
        }
        f.dispose();
        formatter.close();
        MainMenu mainMenu = new MainMenu();
    }
    public void openFile() {
        try {
            FileWriter fileWriter = new FileWriter("saves.txt", true);
            formatter = new Formatter(fileWriter);
        }
        catch (Exception e) {
            System.out.println("Error.");
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