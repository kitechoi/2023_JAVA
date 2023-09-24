package HW03;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MemberSave extends JFrame{

	public MemberSave() {
		setSize(300,200);

		JPanel panel = new JPanel();
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel(new GridLayout(0, 2));
		JPanel panel2 = new JPanel();
		
		JLabel label0 = new JLabel("회원 등록하기");	
	    label0.setFont(new Font(label0.getName(), Font.BOLD, 15));
		panel0.add(label0);
		
	
		panel1.add(new JLabel("이름    "));
		panel1.add(new JTextField(10)); 
		panel1.add(new JLabel("패스워드 "));
		panel1.add(new JPasswordField(10));
		panel1.add(new JLabel("이메일주소"));
		panel1.add(new JTextField(10));
		panel1.add(new JLabel("전화번호 "));
		panel1.add(new JTextField(10));
		
		JButton save = new JButton("등록하기");
		panel2.add(save);
		JButton cancel = new JButton("취소하기");
		panel2.add(cancel);
		
		panel.add(panel0);
		panel.add(panel1);
		panel.add(panel2);
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		MemberSave m = new MemberSave();
	}

}
