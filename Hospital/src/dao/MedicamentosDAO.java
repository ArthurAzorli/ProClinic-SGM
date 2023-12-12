package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Medicamentos;
import bin.Receita;


public class MedicamentosDAO {
    private Connection connection;
	
	public MedicamentosDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Medicamentos m, Receita r){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Medicamentos(id_receita, medicamento) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, r.getId());
			stmt.setString(2, m.getMedicamento());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public ArrayList<Medicamentos> getList(Receita r){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Medicamentos WHERE id_receita = ?";
		PreparedStatement stmt;
		Medicamentos m;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, r.getId());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Medicamentos> Medicamentos = new ArrayList<>();
			while (rs.next()) {
				m = new Medicamentos(rs.getString("medicamento"));
				Medicamentos.add(m);
			}
			rs.close();
			stmt.close();
			return Medicamentos;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public int delete(Medicamentos m, Receita r) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "DELETE FROM Medicamentos WHERE id_receita = ? AND medicamento = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, r.getId());
			stmt.setString(2, m.getMedicamento());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}
	
	public boolean have(Receita r) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM Medicamentos WHERE id_receita = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, r.getId());
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
