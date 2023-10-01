package Week04_kitechoi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BusinessCard extends JFrame {
    private Image img;
    private int img_x = 20, img_y = 20;

    public BusinessCard() {
        ImageIcon icon = new ImageIcon("cat2.jpg");
        img = icon.getImage();

        JPanel panel = new MyPanel();
        add(panel);

        setSize(500, 170);
        setTitle("Business Card");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 이미지를 그립니다.
            g.drawImage(img, img_x, img_y, 80, 80, this);

            // 텍스트를 그립니다...

            g.drawString("최연", 120, 40);
            g.drawString("개발자 지망생 & 사이버보안학과 학생", 120, 60);
            g.drawString("덕성여자대학교", 120, 80);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BusinessCard businessCard = new BusinessCard();
            businessCard.setVisible(true);
        });
    }
}
