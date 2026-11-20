/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class BackgroundPanel extends JPanel {

    private Image bg;

    public BackgroundPanel() {
        try {
            // Karena bg.png ada di package Game
            bg = ImageIO.read(getClass().getResource("bg.png"));
        } catch (Exception e) {
            System.out.println("Gambar tidak ditemukan!");
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
