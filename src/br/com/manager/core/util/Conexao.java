package br.com.manager.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/mysql";
		String user = "root";
		String password = "RafaLena1903";
		Class.forName( driver );
		Connection conn = DriverManager.getConnection( url, user, password);
		
		return conn;
	}
}