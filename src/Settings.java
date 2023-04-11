import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings implements ActionListener {
    JFrame f;
    JPanel p;
    JLabel soundLabel, volumeLabel;
    JSlider volumeSlider;
    JButton soundButton, helpButton, backButton;
    boolean sound;
    public Settings() {
        f = new JFrame("Settings");
        p = new JPanel();

        soundLabel = new JLabel("Sound:");
        volumeLabel = new JLabel("Volume:");

        volumeSlider = new JSlider(-30, 0);

        soundButton = new JButton("ON");
        helpButton = new JButton("HOW TO PLAY");
        backButton = new JButton("BACK");

        soundButton.addActionListener(this);
        helpButton.addActionListener(this);
        backButton.addActionListener(this);

        volumeSlider.addChangeListener(e -> {
            JSlider slider = (JSlider) e.getSource();
            int value = slider.getValue();
            Music.setVolume(value);
        });
        sound = true;

        layout();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helpButton) {
            JOptionPane.showMessageDialog(null, "<html>Try to guess the secret word one letter at a time!<br>If you guess an incorrect letter, TamTam will lose<br>one life. To win, spell the word before he runs out!<html>");
        }
        else if (e.getSource() == backButton) {
            f.dispose();
        }
        else if (e.getSource() == soundButton) {
            if (sound == true) {
                sound = false;
                Music.setVolume(-30);
                soundButton.setText("OFF");
            }
            else {
                sound = true;
                Music.setVolume(0);
                soundButton.setText("ON");
            }
        }
    }
    public void layout() {
        f.setSize(300, 300);
        f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);

        p.setLayout(new GridLayout(6, 1, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        f.add(p);
        p.add(soundLabel);
        p.add(soundButton);
        p.add(volumeLabel);
        p.add(volumeSlider);
        p.add(helpButton);
        p.add(backButton);
    }
}