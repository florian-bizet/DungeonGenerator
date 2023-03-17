package view;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Floor;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

public class FloorPanel extends JPanel {
    List<BufferedImage> wallList;
    BufferedImage floorTex;

    Floor f;
    char[][] floorArray;
    
    public FloorPanel() {
        
        this.f = new Floor(50, 50, 8);
        floorArray = f.generate();
        this.wallList = new ArrayList<BufferedImage>();
        //9 pixels sur la gauche
        //163 pixels vers le bas
        //24*24

        try {
            BufferedImage tileset = ImageIO.read(new File("tiles/tinyWoods.png"));

            //Walls
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < 3; j++) {
                    wallList.add(tileset.getSubimage(9+(3+j)*25, 163+i*25, 24, 24));
                }
            }

            this.floorTex = tileset.getSubimage(9+13*25, 163+1*25, 24, 24);
            this.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < floorArray.length; i++) {
            for (int j = 0; j < floorArray[i].length; j++) {
                if (floorArray[i][j] == ' ') {g.drawImage(floorTex, j*24, i*24, null); continue;}

                g.drawImage(wallList.get((int) (floorArray[i][j] - '!')), j*24, i*24, null); 
                //g.drawString(String.format("%d", (int) (floorArray[i][j] - '!')), j*24, i*24);
                continue;
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(floorArray[0].length*24, floorArray.length*24);
    }
}
