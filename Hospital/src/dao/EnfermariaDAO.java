package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Enfermaria;


public class EnfermariaDAO {
	private Connection connection;
	
	public EnfermariaDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Enfermaria enf){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Enfermaria(coren, nome, dataInscricao) VALUES (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enf.getCoren());
			stmt.setString(2, enf.getNome());
			stmt.setDate(3, enf.getDataInscricao());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}

	public ArrayList<Enfermaria> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria";
		PreparedStatement stmt;
		Enfermaria enf;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Enfermaria> enfermeiros = new ArrayList<>();
			while (rs.next()) {
				enf = new Enfermaria(rs.getString("coren"), rs.getString("nome"), rs.getDate("dataInscricao"));
				enfermeiros.add(enf);
			}
			rs.close();
			stmt.close();
			return enfermeiros;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}
	
	public Enfermaria get(String coren){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria WHERE coren = ?";
		PreparedStatement stmt;
		Enfermaria enf = new Enfermaria();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, coren);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				enf = new Enfermaria(rs.getString("coren"), rs.getString("nome"), rs.getDate("dataInscricao"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return enf;
	}
	
	public int delete(Enfermaria enf) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "DELETE FROM Enfermaria WHERE coren = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enf.getCoren());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}

	public int update(Enfermaria enf){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Enfermaria SET nome = ?, dataInscricao = ? WHERE coren = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enf.getNome());
			stmt.setDate(2, enf.getDataInscricao());
			stmt.setString(3, enf.getCoren());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public boolean exist(Enfermaria enf) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM enfermaria WHERE coren = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enf.getCoren());
			ResultSet rs = stmt.executeQuery();
	
			Integer qnt = 0;
			while (rs.next()) {qnt = rs.getInt(1);}
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

	public ArrayList<Object> getRelatorio(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT e.coren, e.nome, e.dataInscricao, coalesce(especializacao, \"Não possui\") as esp FROM enfermaria e LEFT OUTER JOIN enfermeiro ON coren = id_enfer ORDER BY e.nome";
		PreparedStatement stmt;
		Object r;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Object> relatorio = new ArrayList<>();
			while (rs.next()) {
				r = new Object[] {rs.getString("coren"), rs.getString("nome"), rs.getDate("dataInscricao"), rs.getString("esp")}; 
				relatorio.add(r);
			}
			rs.close();
			stmt.close();
			return relatorio;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}
	
	public ArrayList<Object> searchRelatorio(String string){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT e.coren, e.nome, e.dataInscricao, coalesce(especializacao, \"Não possui\") as esp FROM enfermaria e LEFT OUTER JOIN enfermeiro ON coren = id_enfer WHERE e.nome LIKE CONCAT('%',?,'%') OR e.coren LIKE CONCAT('%',?,'%') OR especializacao LIKE CONCAT('%',?,'%') ORDER BY nome";
		PreparedStatement stmt;
		Object r;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, string);
			stmt.setString(2, string);
			stmt.setString(3, string);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Object> relatorio = new ArrayList<>();
			while (rs.next()) {
				r = new Object[] {rs.getString("coren"), rs.getString("nome"), rs.getDate("dataInscricao"), rs.getString("esp")}; 
				relatorio.add(r);
			}
			rs.close();
			stmt.close();
			return relatorio;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}
	
}
