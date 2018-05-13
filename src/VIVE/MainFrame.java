package VIVE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenu m_contact = new JMenu("联系人管理");
	private JMenu m_system = new JMenu("系统管理");
	private JMenuItem mi_query = new JMenuItem("查找联系人");
	private JMenuItem mi_insert = new JMenuItem("添加联系人");
	private JMenuItem mi_delete = new JMenuItem("删除联系人");
	private JMenuItem mi_exit = new JMenuItem("退出系统");
	private JToolBar toolBar = new JToolBar();
	
	private JButton b_qry = new JButton("查询");
	private JButton b_ins = new JButton("编辑");
	private JButton b_upd = new JButton("更新");
	private JButton b_del = new JButton("删除");
	private JButton b_exi = new JButton("退出");
	
	/*private JButton b_qry = new JButton(new ImageIcon("//home//feng//IdeaProjects//HomeWork1//src//imgs//search.png"));
	private JButton b_ins = new JButton(new ImageIcon("//home//feng//IdeaProjects//HomeWork1//src//imgs//edit.png"));
	private JButton b_upd = new JButton(new ImageIcon("//home//feng//IdeaProjects//HomeWork1//src//imgs//update.png"));
	private JButton b_del = new JButton(new ImageIcon("//home//feng//IdeaProjects//HomeWork1//src//imgs//delete.png"));
	private JButton b_exi = new JButton(new ImageIcon("//home//feng//IdeaProjects//HomeWork1//src//imgs//exit.png"));*/
	//private JLabel l_img=new JLabel(new ImageIcon("imgs/fj.jpg"));
	private JMenuItem mi_modify = new JMenuItem("编辑联系人");
	private JMenuBar menuBar = new JMenuBar();
	
	
	
	//初始化界面
	MainFrame()
	{
		super("个人通讯录");
		// 创建菜单栏
		this.buildMenuBar();
		this.setJMenuBar(menuBar);
		// 创建工具栏
		this.buildToolBar();
		
		Container c = this.getContentPane();
		c.add(toolBar, BorderLayout.NORTH);
		//c.add(l_img,BorderLayout.CENTER);
		this.setBounds(200, 200, 500, 400);
		this.setVisible(true);
		//Resizable 可调大小为false
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//设置菜单项快捷键
		//Accelerator 加速器
		mi_query.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK, false));
		mi_insert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK, false));
		mi_modify.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK, false));
		mi_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK, false));
		mi_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK, false));
		
		//注册监视器
		mi_query.addActionListener(this);
		mi_insert.addActionListener(this);
		mi_modify.addActionListener(this);
		mi_delete.addActionListener(this);
		mi_exit.addActionListener(this);
		
		b_qry.addActionListener(this);
		b_ins.addActionListener(this);
		b_upd.addActionListener(this);
		b_del.addActionListener(this);
		b_exi.addActionListener(this);
	}
	
	//创建工具栏
	private void buildToolBar()
	{
		//不可浮动
		toolBar.setFloatable(false);
		toolBar.add(b_qry);
		//addSeparator 分隔符
		toolBar.addSeparator();
		toolBar.add(b_ins);
		toolBar.add(b_upd);
		toolBar.add(b_del);
		//addSeparator 分隔符
		toolBar.addSeparator();
		toolBar.add(b_exi);
		b_qry.setToolTipText("检索联系人");
		b_ins.setToolTipText("添加联系人");
		b_upd.setToolTipText("修改联系人");
		b_del.setToolTipText("删除联系人");
		b_exi.setToolTipText("退出系统");
	}
	
	
	// 创建菜单栏
	private void buildMenuBar()
	{
		menuBar.add(m_contact);
		menuBar.add(m_system);
		m_contact.add(mi_query);
		m_contact.add(mi_insert);
		m_contact.add(mi_modify);
		m_contact.add(mi_delete);
		m_contact.add(mi_exit);
	}
	
	//事件处理
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		
		//退出
		if (src == mi_exit || src == b_exi)
		{
			System.exit(0);
		}
		
		//检索
		if (src == mi_query || src == b_qry)
		{
			new QueryContactFrame();
		}
		
		//添加
		if (src == mi_insert || src == b_ins)
		{
			new InsetContactFrame();
		}
		
		//修改
		if (src == mi_modify || src == b_upd)
		{
			new ModifyContactFrame();
		}
		
		//删除
		if (src == mi_delete || src == b_del)
		{
			new DeleteContactFrame();
		}
	}
}
