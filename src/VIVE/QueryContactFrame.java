package VIVE;

import DB.ContactDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class QueryContactFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField tf = new JTextField(15);
	private JButton btn_qry = new JButton("查询");
	private JButton btn_all = new JButton("全部");
	private JScrollPane sp = new JScrollPane();
	
	QueryContactFrame()
	{
		super("查询联系人");
		Container c = this.getContentPane();
		c.setLayout(null);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JLabel label = new JLabel("姓名");
		panel.add(label);
		panel.add(tf);
		panel.add(btn_qry);
		panel.add(btn_all);
		c.add(panel);
		c.add(sp);
		
		panel.setBounds(0, 0, 600, 35);
		sp.setBounds(5, 40, 580, 320);
		
		sp.setBorder(BorderFactory.createTitledBorder("联系人列表"));
		this.setBounds(350, 200, 600, 400);
		//大小不可改变
		this.setResizable(false);
		this.setVisible(true);
		
		//关闭操作:撤销本窗口,但不退应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//注册监听器
		btn_all.addActionListener(this);
		btn_qry.addActionListener(this);
	}
	
	//按钮事件处理
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		//所有联系人
		if (src == btn_all)
		{
			//清空上一轮的检索关键词
			tf.setText("");
			ResultSet rs = ContactDao.getAllContacts();
			ResultSetTableModel rstm = new ResultSetTableModel(rs);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}
		
		//按姓名索引
		if (src == btn_qry)
		{
			if (tf.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "查询关键词不能为空", "警告", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			ResultSet rt = ContactDao.getContact(tf.getText().trim());
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}
	}
}
