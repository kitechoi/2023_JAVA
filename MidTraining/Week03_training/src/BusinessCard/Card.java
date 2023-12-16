package BusinessCard;

import javax.swing.*;
import java.awt.*;

//working directory는 상단에 돌아가고 있는 파일이 current상태x, 고정되어있어야 비로소 점3개에 edit이 떠서 설정 가능하다
//왼쪽 프로젝트 목록에서 파일에 오른쪽 마우스키로 run이나 debug 돌리기 가능.

class MyFrame extends JFrame {
    Image img;
    int pos_x = 20, pos_y = 3;
    public MyFrame() {
        ImageIcon icon = new ImageIcon("yeon.jpeg");
        img = icon.getImage();
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BusinessCard");

        add(new BusinessCard());
//        add(new JLabel("dd"));
        setVisible(true);
    }

    class BusinessCard extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img,pos_x,pos_y,100,100,this);
            g.drawString("김덕성",180,30);
            g.drawString("프로젝트 매니저",180,50);
            g.drawString("덕성주식회사",180,70);

            // 텍스트 필드 추가
            JTextField textField = new JTextField();
            textField.setBounds(180, 100, 200, 30);
            add(textField);

            // 버튼 추가
            JButton button = new JButton("확인");
            button.setBounds(180, 150, 200, 30);
            add(button);

        }
    }
}
public class Card {
    public static void main(String[] args) {
        MyFrame F = new MyFrame();
    }
}
