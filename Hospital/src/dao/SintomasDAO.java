package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Sintomas;
import bin.Triagem;

public class SintomasDAO {
    private Connection connection;
	
	public SintomasDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Sintomas s, Triagem t){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Sintomas(id_triagem, sintoma) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, t.getId());
			stmt.setString(2, s.getSintoma());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public ArrayList<Sintomas> getList(Triagem t){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Sintomas WHERE id_triagem = ?";
		PreparedStatement stmt;
		Sintomas s;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, t.getId());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Sintomas> sintomas = new ArrayList<>();
			while (rs.next()) {
				s = new Sintomas(rs.getString("sintoma"));
				sintomas.add(s);
			}
			rs.close();
			stmt.close();
			return sintomas;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public int delete(Sintomas s, Triagem t) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "DELETE FROM Sintomas WHERE id_Triagem = ? AND sintoma = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, t.getId());
			stmt.setString(2, s.getSintoma());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}
	
	public boolean have(Triagem t) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM Sintomas WHERE id_Triagem = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, t.getId());
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
