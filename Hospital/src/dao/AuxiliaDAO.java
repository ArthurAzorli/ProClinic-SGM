package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Auxilia;
import bin.Consulta;
import bin.Enfermeiro;

public class AuxiliaDAO {
    private Connection connection;
	
	public AuxiliaDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Auxilia a){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		int success = 0;
		String sql = "INSERT INTO Auxilia(id_consulta, id_enfer) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, a.getId_consulta());
			stmt.setString(2,a.getId_enfer());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		
		return success;
	}
	
	public ArrayList<Auxilia> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT * FROM Auxilia";
		PreparedStatement stmt;
		Auxilia a;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Auxilia> auxilios = new ArrayList<>();
			while (rs.next()) {
				a = new Auxilia(rs.getInt("id_consulta"), rs.getString("id_enfer"));
				auxilios.add(a);
			}
			rs.close();
			stmt.close();
			return auxilios;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}
	
	public ArrayList<Auxilia> getConsultaList(Integer id_consulta){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT * FROM Auxilia WHERE id_consulta = ?";
		PreparedStatement stmt;
		Auxilia a;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, id_consulta);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Auxilia> auxilios = new ArrayList<>();
			while (rs.next()) {
				a = new Auxilia(rs.getInt("id_consulta"), rs.getString("id_enfer"));
				auxilios.add(a);
			}
			rs.close();
			stmt.close();
			return auxilios;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}
	
	public ArrayList<Auxilia> getEnfermeiroList(String id_enfer){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT * FROM Auxilia WHERE id_enfer = ?";
		PreparedStatement stmt;
		Auxilia a;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, id_enfer);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Auxilia> auxilios = new ArrayList<>();
			while (rs.next()) {
				a = new Auxilia(rs.getInt("id_consulta"), rs.getString("id_enfer"));
				auxilios.add(a);
			}
			rs.close();
			stmt.close();
			return auxilios;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}	
	
	public int delete(Auxilia a) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		int success = 0;
		String sql = "DELETE FROM Auxilia WHERE id_consulta = ? AND id_enfer = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, a.getId_consulta());
			stmt.setString(2, a.getId_enfer());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public boolean have(Consulta c) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT COUNT(*) FROM Auxilia WHERE id_consulta = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, c.getId());
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
	
	public boolean have(Enfermeiro enf) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT COUNT(*) FROM Auxilia WHERE id_enfer = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enf.getCoren());
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
