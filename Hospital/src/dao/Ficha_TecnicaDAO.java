package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bin.Alergias;
import bin.Ficha_Tecnica;
import bin.Paciente;

public class Ficha_TecnicaDAO {
	private Connection connection;
	
	public Ficha_TecnicaDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Ficha_Tecnica f){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Ficha_tecnica(id_paciente, historico_familiar, tipo_sanguineo) VALUES (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, f.getId_Paciente());
            stmt.setString(2, f.getHistorico_familiar());
            stmt.setString(3, f.getTipo_Sanguineo());
			success = stmt.executeUpdate();
			if (success == 1) {
				for (Alergias a: f.getAlergia()) {new AlergiasDAO().create(a, getOfPaciente(f.getId_Paciente()));}
			}
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	

	public Ficha_Tecnica getOfPaciente(String cpf){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM ficha_tecnica WHERE id_Paciente = ?"; 
		PreparedStatement stmt;
		Ficha_Tecnica f = new Ficha_Tecnica();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery(); 
			while (rs.next()) {
				f = new Ficha_Tecnica(rs.getInt("prontuario"), rs.getString("id_paciente"), rs.getString("historico_familiar"), rs.getString("tipo_sanguineo"));
				f.setAlergia(new AlergiasDAO().getList(f));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return f;
	}
	
	
	
	
	public int delete(Ficha_Tecnica f) {
		
		
		int success = 0;
		String sql = "DELETE FROM Ficha_tecnica WHERE prontuario = ?";
		PreparedStatement stmt;
		try {
			if (new AlergiasDAO().have(f)) {new AlergiasDAO().deleteAll(f);}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
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
	
	public int deleteOfPaciente(String cpf) {
		
		int success = 0;
		String sql = "DELETE FROM Ficha_tecnica WHERE id_Paciente = ?";
		PreparedStatement stmt;
		try {
			if (new AlergiasDAO().have(getOfPaciente(cpf))) {new AlergiasDAO().deleteAll(getOfPaciente(cpf));}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}
	
	public int update(Ficha_Tecnica f) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Ficha_tecnica SET  id_paciente= ?, historico_familiar=?, tipo_sanguineo=? WHERE prontuario = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, f.getId_Paciente());
            stmt.setString(2, f.getHistorico_familiar());
            stmt.setString(3, f.getTipo_Sanguineo());
			stmt.setInt(4, f.getProntuario());
			success = stmt.executeUpdate();
			if (success == 1) {
				new AlergiasDAO().deleteAll(f);
				for (Alergias a: f.getAlergia()) {new AlergiasDAO().create(a, f);}
			}
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public boolean exist(Ficha_Tecnica t) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM ficha_tecnica WHERE prontuario = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, t.getProntuario());
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
	
	public boolean have(Paciente p) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM ficha_tecnica WHERE id_paciente = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getCPF());
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
