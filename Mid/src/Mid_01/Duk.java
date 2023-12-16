package Mid_01;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;




class Person {
    String name;
    String num;
    String score;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public Person(String name, String tel,  String address) {
        super();
        this.name = name;
        this.num = tel;
        this.score = address;
    }
}
public class Duk extends JFrame {
    ArrayList<Person> list = new ArrayList<>();
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Duk frame = new Duk();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public Duk() {
        setTitle("덕성여대 화이팅");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 360, 252);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel0 = new JLabel("학생등록하기");
        lblNewLabel0.setBounds(12, 10, 100, 15);
        contentPane.add(lblNewLabel0);


        JLabel lblNewLabel = new JLabel("이름");
        lblNewLabel.setBounds(12, 30, 57, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("학번");
        lblNewLabel_1.setBounds(12, 60, 57, 15);
        contentPane.add(lblNewLabel_1);

//        새로!!!
        JLabel lblNewLabel_2 = new JLabel("성적");
        lblNewLabel_2.setBounds(12, 90, 57, 15);
        contentPane.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(81, 27, 243, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(81, 59, 243, 21);
        contentPane.add(textField_1);
//새로!!
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(81, 92, 243, 21);
        contentPane.add(textField_2);



//
//        JLabel lblNewLabel_2 = new JLabel("주소");
//        lblNewLabel_2.setBounds(12, 79, 57, 15);
//        contentPane.add(lblNewLabel_2);
//
//        JTextArea textArea = new JTextArea();
//        textArea.setBounds(12, 104, 312, 67);
//        contentPane.add(textArea);

//        JButton btnNewButton = new JButton("저장");
//        btnNewButton.setBounds(12, 181, 97, 23);
//        contentPane.add(btnNewButton);
//        btnNewButton.addActionListener(e->{
//            String name = textField.getText();
//            String tel = textField_1.getText();
//            String grade = textField_2.getText();
//            list.add(new Person(name, tel, grade));
//        });

        JButton btnNewButton_1 = new JButton("등록하기");
        btnNewButton_1.setBounds(117, 181, 97, 23);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.addActionListener(e->{
            String name = textField.getText();
            for (Person p : list) {
                if (p.getName().equals(name)) {
                    textField_1.setText(p.getNum());
                    textField_2.setText(p.getScore());
                }
            }
        });

        JButton btnNewButton_1_1 = new JButton("취소");
        btnNewButton_1_1.setBounds(227, 181, 97, 23);
        contentPane.add(btnNewButton_1_1);
        btnNewButton_1_1.addActionListener(e->{
            System.exit(0);
        });

    }
}
