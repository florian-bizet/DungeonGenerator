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
    List<BufferedImage> legendTileList;
    List<BufferedImage> tinyWoodsTileList;
    BufferedImage floorTex;

    Floor f;
    int[][] floorArray;
    
    public FloorPanel() {
        
        this.f = new Floor(50, 50, 8);
        floorArray = f.generate();
        this.legendTileList = new ArrayList<BufferedImage>();
        this.tinyWoodsTileList = new ArrayList<BufferedImage>();
        //9 pixels sur la gauche
        //163 pixels vers le bas
        //24*24
        try {
            BufferedImage tileset = ImageIO.read(new File("tiles/tinyWoods.png"));

            //Legend Tiles
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < 3; j++) {
                    legendTileList.add(tileset.getSubimage(9+j*25, 163+i*25, 24, 24));
                }
            }

            //Tiny Woods
            for (int i = 0; i < 24; i++) {
                for (int j = 3; j < 6; j++) {
                    tinyWoodsTileList.add(tileset.getSubimage(9+j*25, 163+i*25, 24, 24));
                }
            }

            for (int i = 0; i < 24; i++) {
                for (int j = 6; j < 9; j++) {
                    tinyWoodsTileList.add(tileset.getSubimage(9+j*25, 163+i*25, 24, 24));
                }
            }

            for (int i = 0; i < 24; i++) {
                for (int j = 9; j < 12; j++) {
                    tinyWoodsTileList.add(tileset.getSubimage(9+j*25, 163+i*25, 24, 24));
                }
            }

            
            tinyWoodsTileList.add(tileset.getSubimage(9+13*25, 163+1*25, 24, 24));

            this.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < floorArray.length; i++) {
            for (int j = 0; j < floorArray[i].length; j++) {
                if (floorArray[i][j] < 0) {
                    g.drawImage(tinyWoodsTileList.get(tinyWoodsTileList.size() + floorArray[i][j]), j*24, i*24, null);
                    continue; 
                }
                g.drawImage(tinyWoodsTileList.get(floorArray[i][j]), j*24, i*24, null); 
                //g.drawString(String.format("%d",floorArray[i][j]), j*24, i*24+12);
                continue;
            }
        }
    }

    public void regenerate() {
        f = new Floor(50, 50, 8);
        floorArray = f.generate();
        this.repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(floorArray[0].length*24, floorArray.length*24);
    }
}
