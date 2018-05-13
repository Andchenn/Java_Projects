package VIVE;

import DB.Contact;
import DB.ContactDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsetContactFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JTextField tf1 = new JTextField(15);
	private JTextField tf2 = new JTextField(15);
	private JTextField tf3 = new JTextField(15);
	private JTextField tf4 = new JTextField(15);
	private JTextField tf5 = new JTextField(15);
	
	private JLabel jl2 = new JLabel();
	
	private JButton btn1 = new JButton("添加");
	private JButton btn2 = new JButton("重置");
	
	//初始化界面
	InsetContactFrame()
	{
		super("添加联系人");
		Container c = this.getContentPane();
		c.setLayout(null);
		JPanel p1 = new JPanel(new GridLayout(1, 2));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
		
		JLabel jl1 = new JLabel("  注意: 带 * 的必填!");
		p1.add(jl1);
		p1.add(jl2);
		
		JLabel l1 = new JLabel("姓名 *");
		p2.add(l1);
		p2.add(tf1);
		JLabel l2 = new JLabel("性 别");
		p2.add(l2);
		p2.add(tf2);
		JLabel l3 = new JLabel("年 龄");
		p2.add(l3);
		p2.add(tf3);
		JLabel l4 = new JLabel("电 话");
		p2.add(l4);
		p2.add(tf4);
		JLabel l5 = new JLabel("Email");
		p2.add(l5);
		p2.add(tf5);
		
		p3.add(btn1);
		p3.add(btn2);
		
		c.add(p1);
		c.add(p2);
		c.add(p3);
		
		p1.setBounds(0, 0, 360, 30);
		p2.setBounds(0, 30, 200, 270);
		p3.setBounds(270, 130, 100, 150);
		jl2.setForeground(Color.blue);
		
		this.setBounds(350, 200, 450, 350);
		this.setResizable(false);
		this.setVisible(true);
		
		//修改默认关闭操作为DISPOSE_ON_CLOSE
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//按钮注册监听器
		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}
	
	//按钮事件处理
	public void actionPerformed(ActionEvent e)
	{
		JButton src = (JButton) e.getSource();
		
		//添加
		if (src == btn1)
		{
			//清空上一轮添加的反馈信息
			/*
			 * 取联系人信息,封装成Contact对象
			 * 将Contact对象插入到数据库
			 * */
			jl2.setText("");
			
			Contact aFriend = new Contact();
			//姓名不可以为空
			String name;
			if (tf1.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "带*的不可以为空!", "警告", JOptionPane.ERROR_MESSAGE);
				return;
			} else
			{
				name = tf1.getText().trim();
				
			}
			
			//性别只能用男,女,M,F,表示
			String sex;
			if (!tf2.getText().equals("") && !tf2.getText().matches("[男|女|M|F]"))
			{
				JOptionPane.showMessageDialog(this, "性别必须是:男或女,M或F,请重新输入", "警告", JOptionPane.ERROR_MESSAGE);
				tf2.setText("");
				return;
			} else
			{
				sex = tf2.getText();
			}
			
			//年龄必须是整数
			int age=0;
			if (!tf3.getText().equals("") && !tf3.getText().matches("[0-9]{1,2}"))
			{
				JOptionPane.showMessageDialog(this, "年龄必须是数值,请重新输入", "警告", JOptionPane.ERROR_MESSAGE);
				tf3.setText("");
				return;
			} else
			{
				if (tf3.getText().equals(""))
				{
					age = 0;
				} else
				{
					age = Integer.parseInt(tf3.getText());
				}
			}
			
			
			//电话号码必须是数字,且共8-11位
			String phone;
			if (!tf4.getText().equals("") && !tf4.getText().matches("[0-9]{8,11}"))
			{
				JOptionPane.showMessageDialog(this, "电话号码必须是8-11位数字,请重新输入", "警告", JOptionPane.ERROR_MESSAGE);
				tf4.setText("");
				return;
			} else
			{
				phone = tf4.getText();
			}
			
			
			String email = tf5.getText();
			//根据用户输入的联系人信息,得到1个具体的联系人实例
			aFriend.setName(name);
			aFriend.setSex(sex);
			aFriend.setAge(age);
			aFriend.setPhone(phone);
			aFriend.setEmail(email);
			
			//准备insert语句
			//调用ContactDao的insertContact()方法实现记录联系人
			String sql = "insert into contact" + "(name,sex,age,phone,email)" + "values(?,?,?,?,?)";
			int result = ContactDao.insertContact(sql, aFriend);
			//根据insertContact()方法的执行结果,在界面上显示相应的结果信息
			if (result == 1)
			{
				jl2.setText("添加联系人成功!");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				
			} else
			{
				jl2.setText("添加联系人失败!!!");
			}
			
		}
		
		//重置
		if (src == btn2)
		{
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
			
			jl2.setText("");
		}
	}
}
