package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Telefones;
import bin.Paciente;

public class TelefonesDAO {
    private Connection connection;
	
	public TelefonesDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Telefones t, Paciente p){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Telefones(id_paciente, telefone) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getCPF());
			stmt.setString(2, t.getTelefone());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public ArrayList<Telefones> getList(Paciente p){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Telefones WHERE id_paciente = ?";
		PreparedStatement stmt;
		Telefones t;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getCPF());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Telefones> Telefones = new ArrayList<>();
			while (rs.next()) {
				t = new Telefones(rs.getString("telefone"));
				Telefones.add(t);
			}
			rs.close();
			stmt.close();
			return Telefones;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public int delete(Telefones t, Paciente p) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "DELETE FROM Telefones WHERE id_paciente = ? AND telefone = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getCPF());
			stmt.setString(2, t.getTelefone());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}
	
	public int deleteAll(Paciente p) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "DELETE FROM Telefones WHERE id_paciente = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getCPF());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	} 
	
	public boolean have(Paciente p) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM telefones WHERE id_paciente = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getCPF());
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
