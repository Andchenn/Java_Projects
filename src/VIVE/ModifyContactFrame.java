package VIVE;

import DB.Contact;
import DB.ContactDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyContactFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JTextField tf_name = new JTextField(15);
	private JTextField tf_sex = new JTextField(15);
	private JTextField tf_age = new JTextField(15);
	private JTextField tf_phone = new JTextField(15);
	private JTextField tf_email = new JTextField(15);
	
	private JButton b_qry = new JButton("查 询");
	private JButton b_upd = new JButton("更 新");
	private JButton b_res = new JButton("重 置");
	
	private JLabel msg = new JLabel("", JLabel.CENTER);
	
	
	private String newEmail;
	private String newSex;
	private int newAge;
	private String newPhone;
	private String name;
	
	
	//初始化界面
	ModifyContactFrame()
	{
		super("编辑联系人");
		Container c = this.getContentPane();
		c.setLayout(null);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JLabel l1 = new JLabel("姓  名:", JLabel.CENTER);
		p1.add(l1);
		p1.add(tf_name);
		p1.add(b_qry);
		
		JPanel p2 = new JPanel(new GridLayout(4, 2, 10, 3));
		JLabel l2 = new JLabel("更新性别:", JLabel.RIGHT);
		p2.add(l2);
		p2.add(tf_sex);
		JLabel l3 = new JLabel("更新年龄", JLabel.RIGHT);
		p2.add(l3);
		p2.add(tf_age);
		JLabel l4 = new JLabel("更新电话", JLabel.RIGHT);
		p2.add(l4);
		p2.add(tf_phone);
		JLabel l5 = new JLabel("更新Email", JLabel.RIGHT);
		p2.add(l5);
		p2.add(tf_email);
		
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p3.add(b_upd);
		p3.add(b_res);
		msg.setForeground(Color.RED);
		c.add(p1);
		c.add(p2);
		c.add(p3);
		c.add(msg);
		
		p1.setBounds(0, 0, 500, 35);
		p2.setBounds(50, 50, 300, 120);
		p3.setBounds(5, 250, 500, 400);
		msg.setBounds(5, 170, 480, 50);
		this.setBounds(300, 200, 500, 400);
		
		this.setResizable(false);
		this.setVisible(true);
		
		//关闭操作:撤销本窗口,但不退出应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//注册监听器
		b_qry.addActionListener(this);
		b_upd.addActionListener(this);
		
		//重置按钮事件处理
		b_res.addActionListener(e ->
		{
			tf_name.setText("");
			tf_sex.setText("");
			tf_age.setText("");
			tf_phone.setText("");
			tf_email.setText("");
		});
	}
	
	//查询,更新按钮事件处理
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		
		// 查询
		if (src == b_qry)
		{
			tf_sex.setText("");
			tf_age.setText("");
			tf_phone.setText("");
			tf_email.setText("");
			
			//获取查询联系人姓名
			String name;
			if (tf_name.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "姓名不可为空", "警告", JOptionPane.ERROR_MESSAGE);
				return;
			} else
			{
				name = tf_name.getText().trim();
			}
			
			//查询更新的联系人信息
			ResultSet rt = ContactDao.getContactByName(name);
			try
			{
				if (rt.next())
				{
					tf_sex.setText(rt.getString("sex"));
					tf_age.setText(String.valueOf(rt.getInt("age")));
					tf_phone.setText(rt.getString("phone"));
					tf_email.setText(rt.getString("email"));
				}
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		
		//更新
		if (src == b_upd)
		{
			if (tf_name.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "姓名不可为空", "警告", JOptionPane.ERROR_MESSAGE);
				return;
			} else
			{
				name = tf_name.getText().trim();
			}
			
			//性别
			if (!tf_name.getText().equals(""))
			{
				if (!tf_sex.getText().matches("[男|女|M|F]"))
				{
					JOptionPane.showMessageDialog(this, "性别必须是:男或女,M或F,请重新输入", "警告", JOptionPane.ERROR_MESSAGE);
					tf_sex.setText("");
				} else
				{
					newSex = tf_sex.getText();
				}
			}
			
			
			//年龄
			if (!tf_age.getText().equals(""))
			{
				if (!tf_age.getText().matches("^([1-9][0-9]?|100)$"))
				{
					JOptionPane.showMessageDialog(this, "年龄必须是数值,请重新输入", "警告", JOptionPane.ERROR_MESSAGE);
					tf_age.setText("");
				} else
				{
					if (tf_age.getText().equals(""))
					{
						newAge = 0;
					} else
					{
						newAge = Integer.parseInt(tf_age.getText());
					}
				}
			}
			
			
			//电话
			if (!tf_phone.getText().equals(""))
			{
				if (!tf_phone.getText().matches("[0-9]{8,11}"))
				{
					JOptionPane.showMessageDialog(this, "电话号码必须是8-11位数字,请重新输入", "警告", JOptionPane.ERROR_MESSAGE);
					tf_phone.setText("");
				} else
				{
					newPhone = tf_phone.getText();
				}
			}
			
			// email
			if (!tf_email.getText().equals(""))
			{
				newEmail = tf_email.getText();
			}
			
			Contact aFriend = new Contact();
			aFriend.setName(name);
			aFriend.setSex(newSex);
			aFriend.setAge(newAge);
			aFriend.setPhone(newPhone);
			aFriend.setEmail(newEmail);
			
			int result = ContactDao.updateContact(aFriend);
			if (result == 1)
			{
				msg.setText("更新成功!");
			} else
			{
				msg.setText("更新失败!");
			}
		}
	}
}
