package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UFDAO {
	private Connection connection;	
	
	public UFDAO() {
		this.connection = new Conexao().getConnection();
	}
	
	public ArrayList<String> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM estado ORDER BY uf";
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> estados = new ArrayList<>();
			while (rs.next()) {
				String uf = rs.getString("uf");
				estados.add(uf);
			}
			rs.close();
			stmt.close();
			return estados;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}	
	
	
	public String getEstado(String uf){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM estado WHERE uf = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, uf);
			ResultSet rs = stmt.executeQuery();
			String estado = null;
			while (rs.next()) {
				estado = rs.getString("nome");
			}
			rs.close();
			stmt.close();
			return estado;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}	
	
	
	public boolean exist(String uf) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM estado WHERE uf = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, uf);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> estados = new ArrayList<>();
			while (rs.next()) {
				String UF = rs.getString("uf");
				estados.add(UF);
			}
			rs.close();
			stmt.close();
			if(estados.size()>=1) {
				return true;
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return false;
	}
}
