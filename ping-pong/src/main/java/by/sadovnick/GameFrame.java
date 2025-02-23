package by.sadovnick;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private GamePanel panel;

    public GameFrame() throws HeadlessException {
        this.panel = new GamePanel();
        this.add(panel);
        this.setTitle("Ping-Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
