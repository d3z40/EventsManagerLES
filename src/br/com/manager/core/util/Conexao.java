package br.com.manager.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/LES";
		String user = "root";
		String password = "RafaLena1903";
//		String driver = "org.hsqldb.jdbc.JDBCDriver";
//		String url = "jdbc:hsqldb:hsql://localhost:59999/LES";
//		String user = "SA";
//		String password = "";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
}