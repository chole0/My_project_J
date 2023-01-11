package Java_chatwork;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Chatlogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel name,pwd;
	public static HashMap<String, ClientBean> onlines;


	public Chatlogin() {
		setTitle("登陆\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 250, 450, 300);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics background) {
				super.paintComponent(background);
				background.drawImage(new ImageIcon(
								"C:\\Users\\ASUS\\IdeaProjects\\My_project_J\\src\\Java_chatwork\\images\\login.jpg").getImage(), 0,
						0, getWidth(), getHeight(), null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField = new JTextField();
		textField.setForeground(Color.black);
		textField.setBounds(180, 60, 150, 30);
		textField.setOpaque(false);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.black);
		passwordField.setEchoChar('*');
		passwordField.setOpaque(false);
		passwordField.setBounds(180, 150, 150, 30);
		contentPane.add(passwordField);

		name = new JLabel("用户名");
		name.setBounds(80,35,80,80);
		name.setForeground(Color.black);
		name.setFont(new Font("宋体", Font.PLAIN, 25));
		contentPane.add(name);

		pwd = new JLabel("密  码");
		pwd.setBounds(80,112,100,100);
		pwd.setForeground(Color.black);
		pwd.setFont(new Font("宋体", Font.PLAIN, 25));
		contentPane.add(pwd);

		final JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 15));
		btnNewButton.setBounds(60, 220, 70, 30);
		getRootPane().setDefaultButton(btnNewButton);
		contentPane.add(btnNewButton);

		final JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 15));
		btnNewButton_1.setBounds(320, 220, 70, 30);
		contentPane.add(btnNewButton_1);

		// 提示信息
		final JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(170, 220, 151, 21);
		lblNewLabel.setForeground(Color.black);
		getContentPane().add(lblNewLabel);

		// 监听登陆按钮
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Properties userPro = new Properties();
				File file = new File("Users.properties");
				ChatUtil.loadPro(userPro, file);
				String u_name = textField.getText();
				if (file.length() != 0) {

					if (userPro.containsKey(u_name)) {
						String u_pwd = new String(passwordField.getPassword());
						if (u_pwd.equals(userPro.getProperty(u_name))) {

							try {
								Socket client = new Socket("localhost", 8000);

								btnNewButton.setEnabled(false);
								ChatThreadRoom frame = new ChatThreadRoom(u_name,
										client);
								frame.setVisible(true);// 显示聊天界面
								setVisible(false);// 隐藏掉登陆界面

							} catch (UnknownHostException e1) {
								errorTip("未能连接到服务器，请重新连接...");
							} catch (IOException e1) {
								errorTip("未能连接到服务器，请重新连接...");
							}

						} else {
							lblNewLabel.setText("您输入的密码有误！");
							textField.setText("");
							passwordField.setText("");
							textField.requestFocus();
						}
					} else {
						lblNewLabel.setText("您输入昵称不存在！");
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();
					}
				} else {
					lblNewLabel.setText("您输入昵称不存在！");
					textField.setText("");
					passwordField.setText("");
					textField.requestFocus();
				}
			}
		});

		//注册按钮监听
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setEnabled(false);
				ChatResgin frame = new ChatResgin();
				frame.setVisible(true);// 显示注册界面
				setVisible(false);// 隐藏掉登陆界面
			}
		});
	}

	protected void errorTip(String str) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(contentPane, str, "Error Message",
				JOptionPane.ERROR_MESSAGE);
		textField.setText("");
		passwordField.setText("");
		textField.requestFocus();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 启动登陆界面
					Chatlogin frame = new Chatlogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}