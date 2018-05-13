package VIVE;

import DB.ContactDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JTextField tName = new JTextField(15);
	private JPasswordField tPw = new JPasswordField(15);
	
	private JButton btnOk = new JButton("登 录");
	private JButton btnExit = new JButton("退 出");
	
	
	// 初始化界面
	private Login()
	{
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 8));
		// 设置密码不可见
		tPw.setEchoChar('*');
		JLabel lName = new JLabel("登录账号");
		c.add(lName);
		c.add(tName);
		JLabel lPw = new JLabel("登录密码");
		c.add(lPw);
		c.add(tPw);
		c.add(btnOk);
		c.add(btnExit);
		
		// 2个按钮注册监听器
		btnOk.addActionListener(this);
		btnExit.addActionListener(this);
		
		
		this.setTitle("身份认证");
		this.setBounds(400, 300, 260, 200);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			System.out.println("异常:" + e.getMessage());
		}
		
		new Login();
	}
	
	// 按钮事件处理
	public void actionPerformed(ActionEvent e)
	{
		// 退出按钮
		if (e.getSource() == btnExit)
		{
			System.exit(0);
		}
		
		// 登录按钮
		if (e.getSource() == btnOk)
		{
			String name = tName.getText();
			char[] cpw = tPw.getPassword();
			String pw = new String(cpw);
			
			boolean flag = ContactDao.checkUser(name, pw);
			if (flag)
			{
				this.dispose();
				// 验证成功,调用主界面
				new MainFrame();
			} else
			{
				JOptionPane.showInternalMessageDialog(this, "很遗憾,账号或密码错误!", "出错提示", JOptionPane.ERROR_MESSAGE);
			}
			
			// 清空用户之前的输入
			tName.setText("");
			tPw.setText("");
			// 获得焦点
			tName.grabFocus();
		}
	}
}
