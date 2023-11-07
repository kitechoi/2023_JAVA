package ex;

import javax.swing.*;
import java.awt.*;

public class CountDownTest extends JFrame {

    private JLabel label;
    Thread t;

    class Counter extends Thread{
        public void run() {
            for(int i=0; i<=10; i++) {
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    return;
//                  e.printStackTrace(); 대신 return을 써야 스레드가 중지됨.
                }
                label.setText(i + "");
            }
        }
    }

    public CountDownTest() {
        setTitle("카운트다운");
        setSize(400,150);
        getContentPane().setLayout(null);

        label = new JLabel("0");
        label.setBounds(0,0,384,111);
        label.setFont(new Font("Serif",Font.BOLD,100));
        getContentPane().add(label);

        JButton btnNewButton = new JButton("카운터 중지");
        btnNewButton.setBounds(247,25,125,23);
        btnNewButton.addActionListener(e -> t.interrupt());
        getContentPane().add(btnNewButton);
        setVisible(true);

        JButton btnNewButton2 = new JButton("카운터 다시시작");
        btnNewButton2.setBounds(247,60,125,23);
        btnNewButton2.addActionListener(e -> {
                    t = new Counter();
                    t.start();
                });

        getContentPane().add(btnNewButton2);
        setVisible(true);

        t = new Counter();
        t.start();
    }
    public  static  void main(String[] args) {
        CountDownTest t = new CountDownTest();
    }
}
