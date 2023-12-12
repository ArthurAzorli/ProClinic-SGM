package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UrgenciaDAO {
private Connection connection;	
	
	public UrgenciaDAO() {
		this.connection = new Conexao().getConnection();
	}
	
	public ArrayList<String> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT * FROM urgencia ORDER BY tempo";
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> urgencias = new ArrayList<>();
			while (rs.next()) { 
				urgencias.add(rs.getString("cor"));
			}
			rs.close();
			stmt.close();
			return urgencias;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public ArrayList<String> getAllWithClass(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT * FROM urgencia ORDER BY tempo";
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> urgencias = new ArrayList<>();
			while (rs.next()) { 
				urgencias.add(rs.getString("cor")+" | "+rs.getString("classificacao"));
			}
			rs.close();
			stmt.close();
			return urgencias;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	

}
