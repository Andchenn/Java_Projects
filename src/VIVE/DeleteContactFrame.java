package VIVE;

import DB.ContactDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DeleteContactFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField tf = new JTextField(15);
	private JScrollPane sp = new JScrollPane();
	private JLabel msg = new JLabel("", JLabel.CENTER);
	private JButton b_qry = new JButton("查 询");
	private JButton b_del = new JButton("删 除");
	
	//初始化界面
	DeleteContactFrame()
	{
		super("删除联系人");
		Container c = this.getContentPane();
		c.setLayout(null);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JLabel label = new JLabel("姓 名:", JLabel.CENTER);
		panel.add(label);
		panel.add(tf);
		panel.add(b_qry);
		panel.add(b_del);
		sp.setBorder(BorderFactory.createTitledBorder("欲删除联系人"));
		msg.setForeground(Color.RED);
		c.add(panel);
		c.add(sp);
		c.add(msg);
		
		
		panel.setBounds(0, 0, 500, 35);
		sp.setBounds(5, 40, 480, 100);
		msg.setBounds(5, 150, 480, 50);
		this.setBounds(350, 200, 500, 300);
		
		this.setResizable(false);
		this.setVisible(true);
		//关闭操作,撤销本窗口,但不退出应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//注册监听器
		b_qry.addActionListener(this);
		b_del.addActionListener(this);
		
		
	}
	
	//按钮事件处理
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		
		//查询
		if (src == b_qry)
		{
			//清空上一轮的反馈
			msg.setText("");
			
			//获取查询联系人姓名
			String name;
			if (tf.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "姓名不可为空", "警告", JOptionPane.ERROR_MESSAGE);
				return;
			} else
			{
				name = tf.getText().trim();
			}
			
			//查询欲更新的联系人信息
			ResultSet rs = ContactDao.getContactByName(name);
			ResultSetTableModel rstm = new ResultSetTableModel(rs);
			JTable tb = new JTable(rstm);
			
			sp.setViewportView(tb);
		}
		
		//删除
		
		if (src == b_del)
		{
			//获取欲删除联系人的姓名
			String name;
			if (tf.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "姓名不为空", "警告", JOptionPane.ERROR_MESSAGE);
				return;
			} else
			{
				name = tf.getText().trim();
			}
			
			//确认对话框
			int option = JOptionPane.showConfirmDialog(null, "确认要删除联系人?", "确认删除", JOptionPane.OK_CANCEL_OPTION);
			
			//在确认对话框选择了"是",则执行删除,是否不执行删除
			if (option == JOptionPane.OK_OPTION)
			{
				//确认删除
				int result = ContactDao.deleteContact(name);
				if (result == 1)
				{
					msg.setText("删除成功!");
					sp.setViewportView(null);
				} else
				{
					msg.setText("删除失败!");
				}
			}
		}
	}
}
