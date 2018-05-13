package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection
{
	// 获取数据库的连接
	static Connection getConn()
	{
		Connection conn = null;
		String DRIVERNAME = "com.mysql.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/db_contact" + "?useUnicode=true&characterEncoding=utf8";
		
		// 建立连接
		try
		{
			// 加载jdbc驱动
			Class.forName(DRIVERNAME);
			// 获取连接
			conn = DriverManager.getConnection(DBURL, "root", "a");
		} catch (ClassNotFoundException | SQLException e1)
		{
			System.out.println(e1.getMessage());
		}
		return conn;
	}
}
