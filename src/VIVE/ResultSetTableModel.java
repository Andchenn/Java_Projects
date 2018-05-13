package VIVE;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetTableModel extends AbstractTableModel
{
	
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private ResultSetMetaData rsmd = null;
	
	
	//构造函数
	ResultSetTableModel(ResultSet rs)
	{
		this.rs = rs;
		try
		{
			rsmd = rs.getMetaData();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//必须重写
	public int getRowCount()
	{
		int rowCount;
		try
		{
			rs.last();
			rowCount = rs.getRow();
			return rowCount;
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return -1;
		}
		
	}
	
	//必须重写
	public int getColumnCount()
	{
		try
		{
			return rsmd.getColumnCount();
			
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	//必须重写
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		try
		{
			rs.absolute(rowIndex + 1);
			return rs.getObject(columnIndex + 1);
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//可选,若不重写这个方法,则表格模型按默认的ABCD依次命名各个字段
	public String getColumnName(int column)
	{
		try
		{
			return rsmd.getColumnName(column + 1);
		} catch (SQLException e)
		{
			return String.valueOf(column + 1);
		}
	}
}
