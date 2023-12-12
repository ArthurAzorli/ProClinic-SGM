package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Alergias;
import bin.Ficha_Tecnica;

public class AlergiasDAO {
	private Connection connection;
	
	public AlergiasDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Alergias a, Ficha_Tecnica f){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		int success = 0;
		String sql = "INSERT INTO Alergias(id_ficha, alergia) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, f.getProntuario());
			stmt.setString(2,a.getAlergia());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public ArrayList<Alergias> getList(Ficha_Tecnica f){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT * FROM Alergias WHERE id_ficha = ?";
		PreparedStatement stmt;
		Alergias a;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, f.getProntuario());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Alergias> alergias = new ArrayList<>();
			while (rs.next()) {
				a = new Alergias(rs.getString("alergia"));
				alergias.add(a);
			}
			rs.close();
			stmt.close();
			return alergias;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public int deleteAll(Ficha_Tecnica f) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		int success = 0;
		String sql = "DELETE FROM Alergias WHERE id_ficha = ? ";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, f.getProntuario());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}
	
	public boolean have(Ficha_Tecnica f) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT COUNT(*) FROM Alergias WHERE id_ficha = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, f.getProntuario());
			ResultSet rs = stmt.executeQuery();
			Integer qnt = 0;
			while (rs.next()) {
				qnt = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			if(qnt>=1) {
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
