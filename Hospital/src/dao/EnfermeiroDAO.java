package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Auxilia;
import bin.Enfermeiro;

public class EnfermeiroDAO {
    private Connection connection;
	
	public EnfermeiroDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Enfermeiro enf){
        if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		EnfermariaDAO enfermaria = new EnfermariaDAO();
        int success = enfermaria.create(enf);

        if (success == 1){
            String sql = "INSERT INTO Enfermeiro(id_enfer, especializacao) VALUES (?, ?)";
            PreparedStatement stmt;
            try {
                stmt = (PreparedStatement) connection.prepareStatement(sql);
                stmt.setString(1, enf.getCoren());
				stmt.setString(2, enf.getEspecializacao());
                success = stmt.executeUpdate();
                stmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
				new Conexao().closeConncetion(connection);
			}
        }
		return success;
	}

	public Enfermeiro get(String coren){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria AS Enf INNER JOIN Enfermeiro AS E ON Enf.coren = E.id_enfer WHERE Enf.coren = ?";
		PreparedStatement stmt;
		Enfermeiro enf = new Enfermeiro();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, coren);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Enfermeiro> enfermeiros = new ArrayList<>();
			while (rs.next()) {
				enf = new Enfermeiro(rs.getString("coren"), rs.getString("nome"), rs.getString("especializacao"), rs.getDate("dataInscricao"));
				enfermeiros.add(enf);
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

	public ArrayList<Enfermeiro> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria AS Enf INNER JOIN Enfermeiro AS E ON Enf.coren = E.id_enfer";
		PreparedStatement stmt;
		Enfermeiro enf;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Enfermeiro> enfermeiros = new ArrayList<>();
			while (rs.next()) {
				enf = new Enfermeiro(rs.getString("coren"), rs.getString("nome"), rs.getString("especializacao"), rs.getDate("dataInscricao"));
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
	
	public ArrayList<Enfermeiro> search(String enfermeiro){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria AS Enf INNER JOIN Enfermeiro AS E ON Enf.coren = E.id_enfer WHERE nome LIKE CONCAT('%',?,'%') OR coren LIKE CONCAT('%',?,'%') OR especializacao LIKE CONCAT('%',?,'%') ORDER BY nome";
		PreparedStatement stmt;
		Enfermeiro enf;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enfermeiro);
			stmt.setString(2, enfermeiro);
			stmt.setString(3, enfermeiro);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Enfermeiro> enfermeiros = new ArrayList<>();
			while (rs.next()) {
				enf = new Enfermeiro(rs.getString("coren"), rs.getString("nome"), rs.getString("especializacao"), rs.getDate("dataInscricao"));
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
	
	public int delete(Enfermeiro enf) {
		
		
		int success = 0;
		String sql = "DELETE FROM Enfermeiro WHERE id_enfer = ?";
		PreparedStatement stmt;
		try {
			if(new AuxiliaDAO().have(enf)) {for (Auxilia a:new AuxiliaDAO().getEnfermeiroList(enf.getCoren())){new AuxiliaDAO().delete(a);}}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enf.getCoren());
			success = stmt.executeUpdate();
			if(success == 1) {new EnfermariaDAO().delete(enf);}
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}

	public int update(Enfermeiro enf){
        if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		EnfermariaDAO enfermaria = new EnfermariaDAO();
        int success = enfermaria.update(enf);

        if (success == 1){
            String sql = "UPDATE enfermeiro set especializacao = ? WHERE id_enfer = ?";
            PreparedStatement stmt;
            try {
                stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, enf.getEspecializacao());
                stmt.setString(2, enf.getCoren());
                success = stmt.executeUpdate();
                stmt.close();
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
				new Conexao().closeConncetion(connection);
			}
        }
		return success;
	}
	
	
}
