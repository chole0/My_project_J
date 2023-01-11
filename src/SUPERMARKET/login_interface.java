package SUPERMARKET;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//导入各种组件用于作为工具//


public class login_interface extends JFrame implements ActionListener {
    JButton buttonone, buttontwo, buttonthree;
    JLabel labelone, labeltwo, labelthree, labelfour,labelname;
    JTextArea textone, texttwo;

    login_interface() {
        setTitle("欢迎使用南大超市系统");
        setSize(450, 350);
        setLayout(null);
        setLocationRelativeTo(null);//设置窗口中心化//
        ImageIcon a = new ImageIcon("images/3.jpg");//设置窗体界面的图片//


        labelone = new JLabel(a);
        labelone.setBounds(0, 0, a.getIconWidth(), a.getIconHeight());

        labeltwo = new JLabel("用户名：");
        labeltwo.setBounds(10, 30, 70, 40);


        textone = new JTextArea();
        textone.setBounds(70, 40, 150, 20);


        labelthree = new JLabel("密码: ");
        labelthree.setBounds(10, 80, 70, 40);


        texttwo = new JTextArea();
        texttwo.setBounds(70, 90, 150, 20);


        labelfour = new JLabel("新用户请注册");
        labelfour.setBounds(10, 120, 200, 40);

        labelname = new JLabel("8003119198--196班--梁浪");
        labelname.setBounds(10,200,300,20);
        labelname.setFont(new java.awt.Font("Dialog",1,15));
        labelname.setForeground(Color.red);

        buttonone = new JButton("忘记密码");
        buttonone.addActionListener(this);
        buttonone.setBounds(120, 130, 100, 20);


        buttontwo = new JButton("登录");
        buttontwo.addActionListener(this);
        buttontwo.setBounds(70, 160, 60, 30);


        buttonthree = new JButton("注册");
        buttonthree.addActionListener(this);
        buttonthree.setBounds(160, 160, 60, 30);

        add(labeltwo);
        add(textone);
        add(labelthree);
        add(texttwo);
        add(labelfour);
        add(labelname);
        add(buttonone);
        add(buttontwo);
        add(buttonthree);

        add(labelone, Integer.valueOf(Integer.MIN_VALUE));
        add(labelone);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override// 重写函数使其拥有注册改写密码等功能//
    public void actionPerformed(ActionEvent e) {

        if (!textone.getText().isEmpty() && !texttwo.getText().isEmpty()) {

            if (e.getActionCommand().equals("登录")) {


                try {
                    FileReader f = new FileReader("1.txt");//创立相应文件读取用户的信息
                    BufferedReader fr = new BufferedReader(f);//缓冲区
                    String a;
                    try {
                        while ((a = fr.readLine()) != null) {//逐行读取文本信息

                            String[] d = a.split("/");//分割处理读取的文件字符串信息流进行

                            if (textone.getText().equals(d[0])) {
                                if (texttwo.getText().equals(d[1])) {

                                    JOptionPane.showMessageDialog(this, "登录成功");//注册登录成功
                                    this.dispose();//关闭登录窗口
                                    new Supermarket();//登录成功并进入超市系统
                                    break;
                                } else {

                                    JOptionPane.showMessageDialog(this, "密码错误");//登录账户存在密码错误显示密码错误提示框
                                    this.dispose();//关闭登录窗口
                                    new Registered_interface();//进入找回密码界面
                                    break;
                                }
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();

                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        }
        if (e.getActionCommand().equals("注册")) {

            try {
                this.dispose();
                new Registered_interface();//进入注册界面

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getActionCommand().equals("忘记密码")) {

            this.dispose();
            new findback();//进入找回密码界面
        }
    }

}
