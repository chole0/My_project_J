package SUPERMARKET;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Registered_interface extends JFrame implements ActionListener {
    JButton buttonone, buttontwo;
    JTextArea textone, texttwo, textthree, textfour;
    JLabel labelone, labeltwo, labelthree, labelfour, labelfive;

    Registered_interface() throws IOException {
        setTitle("注册");
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);//页面布局在正中央//
        ImageIcon a = new ImageIcon("images/formBg.png");//把照片加入窗口//
        labelone = new JLabel(a);
        labelone.setBounds(0, 0, a.getIconWidth(), a.getIconHeight());

        labeltwo = new JLabel("用户名");
        labeltwo.setBounds(30, 20, 70, 40);

        textone = new JTextArea();
        textone.setBounds(90, 30, 180, 20);

        labelthree = new JLabel("密码");
        labelthree.setBounds(30, 60, 70, 40);

        texttwo = new JTextArea();
        texttwo.setBounds(90, 70, 180, 20);

        labelfour = new JLabel("确认密码");
        labelfour.setBounds(30, 100, 70, 40);

        textthree = new JTextArea();
        textthree.setBounds(90, 110, 180, 20);

        labelfive = new JLabel("手机号");
        labelfive.setBounds(30, 140, 70, 40);

        textfour = new JTextArea();
        textfour.setBounds(90, 150, 180, 20);

        buttonone = new JButton("注册");
        buttonone.addActionListener(this);
        buttonone.setBounds(190, 180, 80, 30);


        buttontwo = new JButton("重新输入");
        buttontwo.addActionListener(this);
        buttontwo.setBounds(190, 180, 80, 30);
        add(labeltwo);
        add(textone);
        add(labelthree);
        add(texttwo);
        add(labelfour);
        add(textthree);
        add(labelfive);
        add(textfour);
        add(buttonone);
        add(buttontwo);
        add(labelone, Integer.valueOf(Integer.MIN_VALUE));
        add(labelone);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置窗体关闭时的状态
    }

    @Override
    public void actionPerformed(ActionEvent e) {//复写实现接口的对应方法
        if (!textone.getText().isEmpty() && !texttwo.getText().isEmpty() && !textthree.getText().isEmpty() && !textfour.getText().isEmpty()) {

            if (e.getActionCommand().equals("注册")) {

                if (texttwo.getText().equals(textthree.getText()) && !textfour.getText().isEmpty()) {

                    File f = new File("1.txt");//使用File类对象创立录入信息的文本

                    try {
                        FileWriter fw = new FileWriter(f, true);//使用FileWriter类设置用户基本信息的录入
                        fw.write(textone.getText());
                        fw.write("/");   //将用户基本信息写入相应的文本框
                        fw.write(texttwo.getText());
                        fw.write("/");
                        fw.write(textthree.getText());
                        fw.write("/");
                        fw.write(textfour.getText());
                        fw.write("\n");
                        fw.flush();//读入信息缓冲设置
                        fw.close();//关闭读入写入信息流
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this, "注册成功");//注册成功弹出注册成功提示框
                    textone.setText(null);
                    texttwo.setText(null);
                    textthree.setText(null);
                    this.dispose();
                    new login_interface();
                } else {
                    JOptionPane.showMessageDialog(this, "两次输入密码不一致");//两次密码输入不一致弹出相应的提示框
                }
            }
            if (e.getActionCommand().equals("重置"))//设置按钮事件监听时的处理
            {
                textone.setText(null);
                texttwo.setText(null);
                textthree.setText(null);

            }
        }
    }


}






