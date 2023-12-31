package PartIV;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private Image img;
    
    public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
    }
    
    public ImagePanel(Image img) {
    	this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setLayout(null);
    }
    
    public void setImage(String img) {
        this.img = new ImageIcon(img).getImage();
        repaint();
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(img, 50, 0, null);
    }
}