package ITEM;

import ITEM.Message;
import ITEM.Translate;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Login_Client extends JDialog {
    private JButton btnGetPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JCheckBox chkAutoLogin;
    private JCheckBox chkRemember;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel lblLogo;
    private JPasswordField txtPassword;
    private JTextField txtRemoteName;
    private JTextField txtRemotePort;
    private JTextField txtUserId;

    public Login_Client(Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
    }

    private void initComponents() {
        this.lblLogo = new JLabel();
        this.txtUserId = new JTextField();
        this.btnRegister = new JButton();
        this.btnGetPassword = new JButton();
        this.txtPassword = new JPasswordField();
        this.chkRemember = new JCheckBox();
        this.chkAutoLogin = new JCheckBox();
        this.btnLogin = new JButton();
        this.jLabel2 = new JLabel();
        this.txtRemoteName = new JTextField();
        this.jLabel3 = new JLabel();
        this.txtRemotePort = new JTextField();

        setDefaultCloseOperation(2);
        setTitle("8003119198-梁浪");
        setIconImage(null);


        this.txtUserId.setFont(new Font("宋体", Font.BOLD, 18));

        this.btnRegister.setFont(new Font("宋体", Font.PLAIN, 14));
        this.btnRegister.setText("注册");
        this.btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.btnGetPassword.setFont(new Font("宋体", Font.PLAIN, 14));
        this.btnGetPassword.setText("找到密码");
        this.btnGetPassword.setBorderPainted(false);
        this.btnGetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.txtPassword.setFont(new Font("宋体", Font.BOLD, 18));

        this.chkRemember.setText("自动登录");
        this.chkRemember.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.chkAutoLogin.setText("记住密码");
        this.chkAutoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.btnLogin.setBackground(new Color(153, 153, 255));
        this.btnLogin.setFont(new Font("宋体", Font.BOLD, 18));
        this.btnLogin.setText("登录");
        this.btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btnLogin.addActionListener(Login_Client.this::btnLoginActionPerformed);
        this.jLabel2.setText("l");

        this.txtRemoteName.setText("127.0.0.1");

        this.jLabel3.setText("l");

        this.txtRemotePort.setText("5000");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(this.lblLogo, -2, 127, -2)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(this.txtUserId, GroupLayout.Alignment.LEADING, -1, 195, 32767)
                                                                .addComponent(this.txtPassword, GroupLayout.Alignment.LEADING))
                                                        .addGap(0, 0, 32767))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(this.chkRemember, -1, 115, 32767)
                                                        .addGap(13, 13, 13)
                                                        .addComponent(this.chkAutoLogin, -2, 73, -2))
                                                .addComponent(this.btnLogin, -1, -1, 32767))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.btnRegister, -1, 95, 32767)
                                                .addComponent(this.btnGetPassword, -1, -1, 32767)))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(this.jLabel2)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtRemoteName, -2, 155, -2)
                                        .addGap(38, 38, 38)
                                        .addComponent(this.jLabel3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(this.txtRemotePort)))
                        .addContainerGap()));

        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.txtUserId, -2, -1, -2)
                                                .addComponent(this.btnRegister))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(this.txtPassword, -2, -1, -2)
                                                .addComponent(this.btnGetPassword))
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.chkRemember)
                                                .addComponent(this.chkAutoLogin)))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(this.lblLogo)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(this.btnLogin)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, 32767)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(this.jLabel2)
                                .addComponent(this.txtRemoteName, -2, -1, -2)
                                .addComponent(this.jLabel3)
                                .addComponent(this.txtRemotePort, -2, -1, -2))
                        .addContainerGap()));

        pack();
    }

private void btnLoginActionPerformed(ActionEvent evt)
    {
        try
        {
            String id = this.txtUserId.getText();
            String password = String.valueOf(this.txtPassword.getPassword());
            if ((id.equals("")) || (password.equals("")))
            {
                JOptionPane.showMessageDialog(null, "账号或密码不能为空", "错误提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String remoteName = this.txtRemoteName.getText();
            InetAddress remoteAddr = InetAddress.getByName(remoteName);
            int remotePort = Integer.parseInt(this.txtRemotePort.getText());

            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(3000);

            Message msg = new Message();
            msg.setUserId(id);
            msg.setPassword(password);
            msg.setType("M_LOGIN");
            msg.setToAddr(remoteAddr);
            msg.setToPort(remotePort);
            byte[] data = Translate.ObjectToByte(msg);

            DatagramPacket packet = new DatagramPacket(data, data.length, remoteAddr, remotePort);

            clientSocket.send(packet);

            DatagramPacket backPacket = new DatagramPacket(data, data.length);
            clientSocket.receive(backPacket);
            clientSocket.setSoTimeout(0);
            Message backMsg = (Message)Translate.ByteToObject(data);
            if (backMsg.getType().equalsIgnoreCase("M_SUCCESS"))
            {
                dispose();

            }
            else
            {
                JOptionPane.showMessageDialog(null, "l", "l", 0);
            }
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "l", 0);
        }
    }

    public static void main(String[] args)
    {


        EventQueue.invokeLater(() -> {
            Login_Client dialog = new Login_Client(new JFrame(), true);
            dialog.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
}

