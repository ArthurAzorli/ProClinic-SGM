package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Consulta;
import bin.Medico;

public class MedicoDAO {
    private Connection connection;
	
	public MedicoDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Medico m){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Medico(crm, nome, dataInscricao, especializacao) VALUES (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, m.getCRM());
			stmt.setString(2, m.getNome());
			stmt.setDate(3, m.getDataInscricao());
            stmt.setString(4, m.getEspecializacao());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public ArrayList<Medico> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Medico ORDER BY nome";
		PreparedStatement stmt;
		Medico m;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Medico> medicos = new ArrayList<>();
			while (rs.next()) {
				m = new Medico(rs.getString("crm"), rs.getString("nome"), rs.getDate("dataInscricao"), rs.getString("especializacao"));
				medicos.add(m);
			}
			rs.close();
			stmt.close();
			return medicos;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public ArrayList<Medico> search(String medico){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Medico WHERE nome LIKE CONCAT('%',?,'%') OR crm LIKE CONCAT('%',?,'%') OR especializacao LIKE CONCAT('%',?,'%') ORDER BY nome";
		PreparedStatement stmt;
		Medico m;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, medico);
			stmt.setString(2, medico);
			stmt.setString(3, medico);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Medico> medicos = new ArrayList<>();
			while (rs.next()) {
				m = new Medico(rs.getString("crm"), rs.getString("nome"), rs.getDate("dataInscricao"), rs.getString("especializacao"));
				medicos.add(m);
			}
			rs.close();
			stmt.close();
			return medicos;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}

	public Medico get(String crm){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Medico WHERE crm = ?";
		PreparedStatement stmt;
		Medico m = new Medico();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, crm);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				m = new Medico(rs.getString("crm"), rs.getString("nome"), rs.getDate("dataInscricao"), rs.getString("especializacao"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return m;
	}
	
	public int delete(Medico m) {
		
		int success = 0;
		String sql = "DELETE FROM Medico WHERE crm = ?";
		PreparedStatement stmt;
		try {
			if (new ConsultaDAO().have(m)) {for (Consulta c: new ConsultaDAO().getMedicoList(m.getCRM())){new ConsultaDAO().delete(c);}}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, m.getCRM());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}
	
	public int update(Medico m) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Medico SET nome = ?, especializacao=?, dataInscricao=? WHERE crm = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEspecializacao());
            stmt.setDate(3, m.getDataInscricao());
			stmt.setString(4, m.getCRM());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public boolean exist(Medico m) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM medico WHERE crm = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, m.getCRM());
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

}
