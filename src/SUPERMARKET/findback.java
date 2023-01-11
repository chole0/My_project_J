package SUPERMARKET;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class findback extends JFrame implements ActionListener {
    JLabel labelone,label1, label2, label5;
    JTextArea text1, text2;
    JButton button1, button2;

    findback() {

        ImageIcon a = new ImageIcon("images/login_bg.jpg");//设置窗体界面的图片//


        labelone = new JLabel(a);
        labelone.setBounds(0, 0, a.getIconWidth(), a.getIconHeight());
        setTitle("找回密码");
        setLayout(null);
        setSize(350, 350);
        setLocationRelativeTo(null);//设置窗体的位置默认为整个布局的中央

        label1 = new JLabel();


        label2 = new JLabel("用 户 名:");
        label2.setBounds(10, 30, 70, 40);

        text1 = new JTextArea();
        text1.setBounds(70, 40, 150, 20);

        label5 = new JLabel("手  机  号:");
        label5.setBounds(10, 80, 70, 40);

        text2 = new JTextArea();
        text2.setBounds(70, 90, 150, 20);

        button1 = new JButton("确认");
        button1.addActionListener(this);
        button1.setBounds(70, 160, 60, 30);

        button2 = new JButton("重置");
        button2.addActionListener(this);
        button2.setBounds(160, 160, 60, 30);

        add(label2);
        add(text1);
        add(label5);
        add(text2);
        add(button1);
        add(button2);
        add(label1, Integer.valueOf(Integer.MIN_VALUE));
        add(labelone, Integer.valueOf(Integer.MIN_VALUE));
        add(labelone);
        add(label1);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!text1.getText().isEmpty() && !text2.getText().isEmpty()) {
            if (e.getActionCommand().equals("确认")) {
                try {
                    FileReader f = new FileReader("1.txt");//设立FileReader类的对象并创立相应的文档用于读取用户的信息
                    BufferedReader fr = new BufferedReader(f);//设置文件读取缓冲
                    String a;
                    try {
                        while ((a = fr.readLine()) != null) {
                            String[] d = a.split("/");//对读取的文件字符串信息流进行分割处理
                            if (text2.getText().equals(d[3])) {

                                JOptionPane.showMessageDialog(this, "你的密码为" + d[2]);//用户的账户信息和手机号正确时返回相应的密码
                                this.dispose();
                                new login_interface();
                                break;

                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
            if (e.getActionCommand().equals("重置"))//清空并重新输入
            {
                text1.setText(null);
                text2.setText(null);
            }
        }
    }
}
