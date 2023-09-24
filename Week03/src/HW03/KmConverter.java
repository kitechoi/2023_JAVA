package HW03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KmConverter extends JFrame{

	public KmConverter() {
		JPanel panel= new JPanel();
		add(panel);
		
		JLabel label1 = new JLabel("마일을 입력하시오");
		JTextField field1 = new JTextField(15);
		JLabel label2 = new JLabel("->");
		JButton button = new JButton("변환");
		JTextField resultText = new JTextField(15); 
		resultText.setEditable(false);
		

        panel.add(label1);
        panel.add(field1);
        panel.add(label2);
      
        panel.add(resultText);  
        panel.add(button);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double miles = Double.parseDouble(field1.getText());
                    double kilometers = miles * 1.60934;
                    resultText.setText(kilometers + " km");
                } catch (NumberFormatException ex) {
                    resultText.setText("잘못된 입력");
                }
            }
        });

		setSize(500, 150);
		setTitle("Mile->Km");
		setVisible(true);
		
	}
	public static void main(String argv[]) {
		KmConverter m = new KmConverter();
	}
}
