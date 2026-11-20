/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.awt.*;
import javax.swing.*;

public class RoundedProgressBar extends JProgressBar {

    private int radius = 15;

    public RoundedProgressBar(int min, int max) {
        super(min, max);
        setOpaque(false);
        setBorder(null);
        setStringPainted(true);
    }

    public void setRadius(int r) {
        this.radius = r;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        int arc = radius * 2;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background bar
        g2.setColor(new Color(50, 50, 50)); // warna bar belakang
        g2.fillRoundRect(0, 0, w, h, arc, arc);

        // Progress bar (foreground)
        double percent = getValue() / (double) getMaximum();
        int progressWidth = (int) (w * percent);

        g2.setColor(getForeground()); // warna isi bar
        g2.fillRoundRect(0, 0, progressWidth, h, arc, arc);

        // Draw text (value string)
        if (isStringPainted()) {
            String text = getString();
            FontMetrics fm = g2.getFontMetrics();
            int textX = (w - fm.stringWidth(text)) / 2;
            int textY = (h + fm.getAscent() - fm.getDescent()) / 2;

            g2.setColor(Color.WHITE);
            g2.drawString(text, textX, textY);
        }

        g2.dispose();
    }
}
