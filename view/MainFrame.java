package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame implements ActionListener {
    private JButton next;
    private FloorPanel floorPanel;
    
    public MainFrame() {
        this.setTitle("Dungeon Generator");
        this.setSize(1280, 720);

        next = new JButton("Étage suivant");
        next.addActionListener(this);

        floorPanel = new FloorPanel();

        this.add(new JScrollPane(floorPanel));
        this.add(next, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        floorPanel.regenerate();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
