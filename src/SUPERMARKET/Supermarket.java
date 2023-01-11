package SUPERMARKET;

//导入各种组件用于作为工具//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Supermarket extends JFrame implements ActionListener {
    JButton bone, btwo, bthree, bfour, bfive, bsix, bseven, beight, bten, btelve;
    JLabel label, label1, label2, label3, label4, label5, label6;
    JTextArea text;
    JDialog dialog, dialog1, dialog2, dialog3, dialog4, dialog5, dialog6, dialog7;
    JPanel panel;

    Supermarket() {
        setTitle("南大平价超市");
        setLayout(null);
        setSize(720, 710);
        setLocationRelativeTo(null);
        text = new JTextArea("南大平价超市欢迎你!");
        Font font = new Font("楷体", Font.BOLD, 40);
        text.setFont(font);
        text.setForeground(Color.red);
        text.setBounds(170, 0, 400, 80);
        add(text);

        bone = new JButton("零食区");
        bone.addActionListener(this);
        bone.setBounds(0, 80, 80, 40);
        add(bone);

        btwo = new JButton("蔬菜区");
        btwo.addActionListener(this);
        btwo.setBounds(0, 290, 80, 40);
        add(btwo);

        bthree = new JButton("水果区");
        bthree.addActionListener(this);
        bthree.setBounds(0, 460, 80, 40);
        add(bthree);

        bfour = new JButton("奶茶区");
        bfour.addActionListener(this);
        bfour.setBounds(620, 80, 80, 40);
        add(bfour);

        bfive = new JButton("牛奶区");
        bfive.addActionListener(this);
        bfive.setBounds(620, 290, 80, 40);
        add(bfive);

        bsix = new JButton("酒区");
        bsix.addActionListener(this);
        bsix.setBounds(620, 460, 80, 40);
        add(bsix);

        bseven = new JButton("服装区");
        bseven.addActionListener(this);
        bseven.setBounds(310, 80, 100, 40);
        add(bseven);

        beight = new JButton("***南大超市***");
        beight.addActionListener(this);
        beight.setBounds(270, 260, 200, 100);
        beight.setBackground(Color.black);
        beight.setForeground(Color.white);
        add(beight);

        bten = new JButton("熟食区");
        bten.addActionListener(this);
        bten.setBounds(310, 460, 100, 40);
        add(bten);

        btelve = new JButton("结账");
        btelve.addActionListener(this);
        btelve.setBounds(310, 630, 100, 40);
        btelve.setBackground(Color.orange);
        btelve.setForeground(Color.red);
        add(btelve);

        ImageIcon img = new ImageIcon("images/2.png");
        label = new JLabel(img);
        getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        label.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        add(label);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("结账")) {
            new BUY();//进入结账界面
        }
        if (e.getActionCommand().equals("零食区")) {
            dialog = new JDialog();//监听按钮，当事件出现时弹对话框
            dialog.setBounds(0, 0, 400, 350);//设置对话框的位置和大小
            dialog.setLocationRelativeTo(null);
            panel = new JPanel();//创建面板
            panel.setLayout(null);//为面板设置布局，布局为空即不设置默认为其位于整个面板中央
            bone = new JButton("卫龙");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("9 ￥/kg");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));//设置标签字体大小，形式，粗细
            label2.setForeground(Color.red);//设置标签字体颜色
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("面包");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("6 ￥/kg");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.red);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("瓜子");
            bthree.setBounds(268, 0, 80, 40);
            label4 = new JLabel("8 ￥/kg");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("猪脯肉");
            bfour.setBounds(268, 80, 80, 40);
            label5 = new JLabel("100￥/kg");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("蛋糕");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("8￥/斤");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);
            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog.add(panel);
            dialog.setVisible(true);
            dialog.setResizable(false);
        }


        if (e.getActionCommand().equals("蔬菜区")) {
            dialog1 = new JDialog();
            dialog1.setBounds(0, 0, 400, 350);
            dialog1.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(null);
            bone = new JButton("白菜");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("10￥/斤");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));
            label2.setForeground(Color.RED);
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("土豆");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("8￥/斤");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.RED);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("莲藕");
            bthree.setBounds(268, 0, 120, 40);
            label4 = new JLabel("14￥/斤");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("黄瓜");
            bfour.setBounds(268, 80, 120, 40);
            label5 = new JLabel("5￥/斤");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("卷心菜");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("8￥/斤");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);
            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog1.add(panel);
            dialog1.setVisible(true);
            dialog1.setResizable(false);
        }

        if (e.getActionCommand().equals("水果区")) {
            dialog2 = new JDialog();
            dialog2.setBounds(0, 0, 400, 350);
            dialog2.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(null);
            bone = new JButton("橘子");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("10￥/个");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));
            label2.setForeground(Color.RED);
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("苹果");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("8￥/个");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.RED);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("红柚子");
            bthree.setBounds(268, 0, 120, 40);
            label4 = new JLabel("14￥/个");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("哈密瓜");
            bfour.setBounds(268, 80, 120, 40);
            label5 = new JLabel("5￥/个");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("榴莲");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("800￥/个");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);
            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog2.add(panel);
            dialog2.setVisible(true);
            dialog2.setResizable(false);
        }


        if (e.getActionCommand().equals("熟食区")) {
            dialog3 = new JDialog();
            dialog3.setBounds(0, 0, 400, 350);
            dialog3.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(null);
            bone = new JButton("熟牛肉");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("10￥/斤");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));
            label2.setForeground(Color.RED);
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("牙签肉");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("8￥/斤");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.RED);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("烤羊肉");
            bthree.setBounds(268, 0, 120, 40);
            label4 = new JLabel("14￥/斤");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("炸鸡柳");
            bfour.setBounds(268, 80, 120, 40);
            label5 = new JLabel("5￥/斤");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("骨肉相连");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("8￥/斤");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);
            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog3.add(panel);
            dialog3.setVisible(true);
            dialog3.setResizable(false);
        }


        if (e.getActionCommand().equals("服装区")) {
            dialog4 = new JDialog();
            dialog4.setBounds(0, 0, 400, 350);
            dialog4.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(null);
            bone = new JButton("夹克衫");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("10￥/件");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));
            label2.setForeground(Color.RED);
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("牛仔裤");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("8￥/件");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.RED);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("羽绒服");
            bthree.setBounds(268, 0, 120, 40);
            label4 = new JLabel("14￥/件");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("毛衣");
            bfour.setBounds(268, 80, 120, 40);
            label5 = new JLabel("5￥/件");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("工装裤");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("8￥/件");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);

            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog4.add(panel);
            dialog4.setVisible(true);
            dialog4.setResizable(false);
        }


        if (e.getActionCommand().equals("奶茶区")) {
            dialog5 = new JDialog();
            dialog5.setBounds(0, 0, 400, 350);
            dialog5.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(null);
            bone = new JButton("珍珠奶茶");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("10￥/杯");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));
            label2.setForeground(Color.RED);
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("烧仙草");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("8￥/杯");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.RED);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("双响百香果");
            bthree.setBounds(268, 0, 120, 40);
            label4 = new JLabel("14￥/杯");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("西瓜汁");
            bfour.setBounds(268, 80, 120, 40);
            label5 = new JLabel("5￥/杯");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("柠檬茶");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("8￥/杯");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);
            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog5.add(panel);
            dialog5.setVisible(true);
            dialog5.setResizable(false);

        }


        if (e.getActionCommand().equals("牛奶区")) {
            dialog6 = new JDialog();
            dialog6.setBounds(0, 0, 400, 350);
            dialog6.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(null);
            bone = new JButton("蒙牛纯牛奶");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("30￥/箱");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));
            label2.setForeground(Color.RED);
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("伊利纯牛奶");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("40￥/箱");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.RED);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("光明酸奶");
            bthree.setBounds(268, 0, 120, 40);
            label4 = new JLabel("45￥/箱");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("纯甄酸牛奶");
            bfour.setBounds(268, 80, 120, 40);
            label5 = new JLabel("65￥/箱");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("安慕希");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("70￥/箱");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);
            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog6.add(panel);
            dialog6.setVisible(true);
            dialog6.setResizable(false);
        }


        if (e.getActionCommand().equals("酒区")) {
            dialog7 = new JDialog();
            dialog7.setBounds(0, 0, 400, 350);
            dialog7.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(null);
            bone = new JButton("雪津啤酒");
            bone.setBounds(0, 0, 120, 40);
            label2 = new JLabel("4￥/瓶");
            label2.setFont(new Font("Dialog", Font.BOLD, 20));
            label2.setForeground(Color.RED);
            label2.setBounds(20, 40, 80, 40);
            btwo = new JButton("红酒");
            btwo.setBounds(0, 80, 120, 40);
            label3 = new JLabel("40￥/瓶");
            label3.setFont(new Font("Dialog", Font.BOLD, 20));
            label3.setForeground(Color.RED);
            label3.setBounds(20, 120, 80, 40);
            bthree = new JButton("葡萄酒");
            bthree.setBounds(268, 0, 120, 40);
            label4 = new JLabel("35￥/瓶");
            label4.setFont(new Font("Dialog", Font.BOLD, 20));
            label4.setForeground(Color.RED);
            label4.setBounds(290, 40, 80, 40);
            bfour = new JButton("南昌八度");
            bfour.setBounds(268, 80, 120, 40);
            label5 = new JLabel("7￥/瓶");
            label5.setFont(new Font("Dialog", Font.BOLD, 20));
            label5.setForeground(Color.RED);
            label5.setBounds(290, 120, 80, 40);
            bfive = new JButton("百威");
            bfive.setBounds(135, 150, 120, 40);
            label6 = new JLabel("3￥/瓶");
            label6.setFont(new Font("Dialog", Font.BOLD, 20));
            label6.setForeground(Color.RED);
            label6.setBounds(160, 190, 80, 40);
            panel.add(bone);
            panel.add(label2);
            panel.add(btwo);
            panel.add(label3);
            panel.add(bthree);
            panel.add(label4);
            panel.add(bfour);
            panel.add(label5);
            panel.add(bfive);
            panel.add(label6);
            ImageIcon img = new ImageIcon("images/2.png");
            label1 = new JLabel(img);
            label1.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
            panel.add(label1, Integer.valueOf(Integer.MAX_VALUE));
            dialog7.add(panel);
            dialog7.setVisible(true);
            dialog7.setResizable(false);
        }
    }
}