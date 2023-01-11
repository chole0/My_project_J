package Java_chatwork;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChatResgin extends JFrame {

	private JPanel contentPane;
	private JTextField namenumber;
	private JPasswordField passwordnum;
	private JPasswordField repasswordnum;
	private JLabel tipstext,name,pwd,repwd;

	public ChatResgin() {
		setTitle("注册\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 250, 450, 300);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(new ImageIcon("C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\Java_chatwork\\images\\resign.jpg").getImage(), 0,0, getWidth(), getHeight(), null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		namenumber = new JTextField();
		namenumber.setBounds(180, 45, 150, 30);
		namenumber.setOpaque(false);
		namenumber.setForeground(Color.white);
		contentPane.add(namenumber);
		namenumber.setColumns(10);


		passwordnum = new JPasswordField();
		passwordnum.setEchoChar('*');
		passwordnum.setForeground(Color.white);
		passwordnum.setOpaque(false);
		passwordnum.setBounds(180, 105, 150, 30);
		contentPane.add(passwordnum);

		repasswordnum = new JPasswordField();
		repasswordnum.setBounds(180, 170, 150, 30);
		repasswordnum.setForeground(Color.white);
		repasswordnum.setOpaque(false);
		contentPane.add(repasswordnum);

		name = new JLabel("用户名");
		name.setBounds(80,20,80,80);
		name.setForeground(Color.black);
		name.setFont(new Font("宋体", Font.PLAIN, 25));
		contentPane.add(name);

		pwd = new JLabel("密  码");
		pwd.setBounds(80,70,100,100);
		pwd.setForeground(Color.black);
		pwd.setFont(new Font("宋体", Font.PLAIN, 25));
		contentPane.add(pwd);

		repwd = new JLabel("确认密码");
		repwd.setBounds(70,140,100,100);
		repwd.setForeground(Color.black);
		repwd.setFont(new Font("宋体", Font.PLAIN, 25));
		contentPane.add(repwd);

		//注册按钮
		final JButton ResignBUtton = new JButton("注册");
		ResignBUtton.setFont(new Font("宋体", Font.BOLD, 15));
		ResignBUtton.setBounds(320, 220, 90, 33);
		getRootPane().setDefaultButton(ResignBUtton);
		contentPane.add(ResignBUtton);

		//返回按钮
		final JButton BackBUtton = new JButton("返回登录");
		BackBUtton.setFont(new Font("宋体", Font.BOLD, 12));
		BackBUtton.setBounds(60, 220, 90, 33);
		getRootPane().setDefaultButton(BackBUtton);
		contentPane.add(BackBUtton);

		//提示信息
		tipstext = new JLabel();
		tipstext.setBounds(170, 220, 151, 21);
		tipstext.setForeground(Color.white);
		contentPane.add(tipstext);

		//返回按钮监听
		BackBUtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackBUtton.setEnabled(false);
				//返回登陆界面
				Chatlogin frame = new Chatlogin();
				frame.setVisible(true);
				setVisible(false);
			}
		});

		//注册按钮监听
		ResignBUtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Properties userPro = new Properties();
				File file = new File("Users.properties");
				ChatUtil.loadPro(userPro, file);

				String u_name = namenumber.getText();
				String u_pwd = new String(passwordnum.getPassword());
				String u_pwd_ag = new String(repasswordnum.getPassword());

				// 判断用户名是否在普通用户中已存在
				if (u_name.length() != 0) {

					if (userPro.containsKey(u_name)) {
						tipstext.setText("用户名已存在!");
					} else {
						isPassword(userPro, file, u_name, u_pwd, u_pwd_ag);
					}
				} else {
					tipstext.setText("用户名不能为空！");
				}
			}

			private void isPassword(Properties userPro,
									File file, String u_name, String u_pwd, String u_pwd_ag) {
				if (u_pwd.equals(u_pwd_ag)) {
					if (u_pwd.length() != 0) {
						userPro.setProperty(u_name, u_pwd_ag);
						try {
							userPro.store(new FileOutputStream(file),
									"***8003119198-梁浪***");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ResignBUtton.setEnabled(false);
						//返回登陆界面
						Chatlogin frame = new Chatlogin();
						frame.setVisible(true);
						setVisible(false);
					} else {
						tipstext.setText("密码为空！");
					}
				} else {
					tipstext.setText("密码不一致！");
				}
			}
		});
	}
}
