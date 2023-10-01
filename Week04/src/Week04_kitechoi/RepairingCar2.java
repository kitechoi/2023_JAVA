package Week04_kitechoi;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RepairingCar2 extends JPanel implements ItemListener {
    JCheckBox[] buttons = new JCheckBox[4];
    String[] items = {"엔진오일 교환", "자동변속기오일교환", "에어컨필터교환", "타이어 교환"};
    int[] prices = {45000, 80000, 30000, 100000};
    int money = 0; // 선택된 항목의 가격 합계
    JLabel label;

    public RepairingCar2() {
        super();

        // 체크박스와 레이블 생성..
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JCheckBox(items[i]);
            buttons[i].addItemListener(this);
            add(buttons[i]);
        }
        
        Font font = new Font("Serif", Font.ITALIC+Font.BOLD, 25); // 폰트 이름, 스타일, 크기
        label = new JLabel("현재까지의 가격은 0원입니다");
        label.setFont(font); // 폰트 설정
        add(label);
    }

    public void itemStateChanged(ItemEvent e) {
        money = 0; // 가격 합계 초기화
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected()) {
                money += prices[i]; // 선택된 체크박스의 가격 합산
            }
        }
        label.setText("현재까지의 가격은 " + money + "원입니다");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new RepairingCar2();
        frame.setContentPane(newContentPane);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }
}

