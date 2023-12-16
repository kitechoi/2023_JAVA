package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class Myframe extends JFrame {
    JButton button;
    JTextField t1;
    JTextField t2;

    public Myframe() {
        setSize(450, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MileCount.Mile->Km");

        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("마일을 입력하세요");
        t1 = new JTextField(10);
        t2 = new JTextField(10);
        button = new JButton("변환");
        button.addActionListener(new MyListener());

        t1.setText("1");

        t2.setEditable(false);
        panel1.add(label);
        panel1.add(t1);
        panel1.add(new JLabel("->"));
        panel1.add(t2);
        panel1.add(button);
        this.add(panel1);

        setVisible(true);
    }

    private class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                int t = Integer.parseInt(t1.getText());
                double k = t * 1.609344;
                t2.setText(k + " km");

                // 마일 변환값 k를 파일에 저장
                saveToFile(t, k);
            }
        }
        //파일에 저장
        private void saveToFile(int miles, double kilometers) {
            try (FileWriter writer = new FileWriter("mile_conversion.txt", true)) {
                writer.write(miles + " miles -> " + kilometers + " km\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}

public class Mile_file {
    public static void main(String args[]) {
        Myframe f = new Myframe();
    }
}
