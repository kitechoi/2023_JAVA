package HW03;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CarMoving extends JFrame{
	
	Image img;
	int img_x=190, img_y=150;

	
	public CarMoving() {
		ImageIcon icon = new ImageIcon("car1.jpeg");
		img = icon.getImage();
		setSize(500,300);
		add(new MyPanel(), BorderLayout.CENTER);
		JPanel panel = new JPanel();
		Button b1 = new Button("LEFT");
		Button b2 = new Button("RIGHT");
		b1.addActionListener(e -> {
			img_x -= 10;
			repaint();
		});
		b2.addActionListener(e -> {
			img_x += 10;
			repaint();
		});
		panel.add(b1);
		panel.add(b2);
		add(panel, BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, img_x, img_y, this);
		}
	}
	
	public static void main(String[] args) {
		CarMoving c = new CarMoving();
	}
}
