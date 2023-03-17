package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
    public MainFrame() {
        this.setTitle("Dungeon Generator");
        this.setSize(1280, 720);

        this.add(new JScrollPane(new FloorPanel()));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
