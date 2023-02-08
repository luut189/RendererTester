
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel {
    
    public final int width, height;
    public final int size = 8;
    public int pixels[];

    public int xScroll = 0;
    public int yScroll = 0;

    public Renderer(int width, int height) {
        this.width = width/size*size;
        this.height = height/size*size;
        this.setPreferredSize(new Dimension(this.width, this.height));

        pixels = new int[(this.width*this.height)/size];
        generatePalette();
    }

    public void generatePalette() {
        for(int i = 0; i < pixels.length; i++) {
            int r = (i & 0b11100000) >> 5;
            int g = (i & 0b00011100) >> 2;
            int b = (i & 0b00000011) << 1;
            r = (r * 255) / 7;
            g = (g * 255) / 7;
            b = (b * 255) / 7;
            int color = (r << 16) | (g << 8) | b;
            pixels[i] = color;
        }
    }

    public Color getColor(int i) {
        int r = (i & 0xff0000) >> 16;
        int g = (i & 0xff00) >> 8;
        int b = (i & 0xff);
        return new Color(r,g,b);
    }

    @Override
    public void paintComponent(Graphics g) {
        int x = 0;
        int y = 0;
        for(int i = xScroll; i < pixels.length; i++) {
            g.setColor(getColor(pixels[i]));
            g.fillRect(x*size, y*size, size, size);
            x++;
            if(x >= 255) {
                y++;
                x = 0;
            }
        }
    }
    
}
