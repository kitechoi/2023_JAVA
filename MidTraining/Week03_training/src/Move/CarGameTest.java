package Move;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 소스를 입력하고 Ctrl+Shift+O를 눌러서 필요한 파일을 포함


public class CarGameTest extends JFrame {
    BufferedImage img = null;
    int img_x = 70, img_y = 50;

    class MyPanel extends JPanel {

        public MyPanel() {
            try {
                img = ImageIO.read(new File("car.png"));
//
//                img newWidth = 100;
//                img newHeight = 50;
            } catch (IOException e) {
                System.out.println("no image");
                System.exit(1);
            }
            addKeyListener(new KeyListener() {
                public void keyPressed(KeyEvent e) {
                    int keycode = e.getKeyCode();
                    switch (keycode) {
                        case KeyEvent.VK_UP:	img_y -= 10;	break;
                        case KeyEvent.VK_DOWN:	img_y += 10;	break;
                        case KeyEvent.VK_LEFT:	img_x -= 10;	break;
                        case KeyEvent.VK_RIGHT:	img_x += 10;	break;
                    }
                    repaint();
                }
                public void keyReleased(KeyEvent arg0) {		}
                public void keyTyped(KeyEvent arg0) {			}

            });

            this.requestFocus();
            setFocusable(true);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            double num1 = getWidth()/img.getHeight();
            double num2 = getHeight() / img.getWidth();
            // Calculate the scale factor required to fit the image inside the component.
            double scaleFactor = min(0.2, num1, num2);

            // Draw the scaled image at the current position.
            g.drawImage(img, img_x, img_y, (int) (img.getWidth() * scaleFactor), (int) (img.getHeight() * scaleFactor), null);
        }

        private double min(double v, double num1, double num2) {
            return v;
        }

    }


    public CarGameTest() {
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MyPanel());
        JPanel p = new JPanel();
        JButton b1 = new JButton("LEFT");
        b1.addActionListener(e->{
            img_x -= 10;
            repaint(); //강제로 컴포넌트를 다시 그리게함
        });
        JButton b2 = new JButton("RIGHT");
        b2.addActionListener(e->{
            img_x += 10;
            repaint();
        });
        p.add(b1);
        p.add(b2);
        add(p, "South");
        setVisible(true);
    }

    public static void main(String[] args) {
        CarGameTest s = new CarGameTest();
    }
}