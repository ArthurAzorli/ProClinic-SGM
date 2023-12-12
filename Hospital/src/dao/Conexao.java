package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public Connection getConnection() {
		try {
			return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useSSL=false", "root", "");
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConncetion(Connection c){
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isClosedConncetion(Connection c){
		try {
			return c.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
		
}
