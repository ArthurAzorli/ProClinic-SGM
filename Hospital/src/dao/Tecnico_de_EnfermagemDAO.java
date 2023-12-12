package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Tecnico_de_Enfermagem;
import bin.Triagem;

public class Tecnico_de_EnfermagemDAO {
    private Connection connection;
	
	public Tecnico_de_EnfermagemDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Tecnico_de_Enfermagem enf){
        if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		EnfermariaDAO enfermaria = new EnfermariaDAO();
        int success = enfermaria.create(enf);

        if (success == 1){
            String sql = "INSERT INTO Tecnico_de_Enfermagem(id_tecEnfer) VALUES (?)";
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
        }
		return success;
	}

	public Tecnico_de_Enfermagem get(String coren){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria AS Enf INNER JOIN Tecnico_de_Enfermagem AS T ON Enf.coren = T.id_tecEnfer WHERE Enf.coren = ?";
		PreparedStatement stmt;
		Tecnico_de_Enfermagem enf = new Tecnico_de_Enfermagem();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, coren);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Tecnico_de_Enfermagem> Tecnicos_de_Enfermagem = new ArrayList<>();
			while (rs.next()) {
				enf = new Tecnico_de_Enfermagem(rs.getString("coren"), rs.getString("nome"), rs.getDate("dataInscricao"));
				Tecnicos_de_Enfermagem.add(enf);
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

	public ArrayList<Tecnico_de_Enfermagem> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria AS Enf INNER JOIN Tecnico_de_Enfermagem AS E ON Enf.coren = E.id_tecEnfer ORDER BY nome";
		PreparedStatement stmt;
		Tecnico_de_Enfermagem enf;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Tecnico_de_Enfermagem> Tecnicos_de_Enfermagem = new ArrayList<>();
			while (rs.next()) {
				enf = new Tecnico_de_Enfermagem(rs.getString("coren"), rs.getString("nome"), rs.getDate("dataInscricao"));
				Tecnicos_de_Enfermagem.add(enf);
			}
			rs.close();
			stmt.close();
			return Tecnicos_de_Enfermagem;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}	
	
	public ArrayList<Tecnico_de_Enfermagem> search(String tecnico){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Enfermaria AS Enf INNER JOIN Tecnico_de_Enfermagem AS E ON Enf.coren = E.id_tecEnfer WHERE nome LIKE CONCAT('%',?,'%') OR coren LIKE CONCAT('%',?,'%') ORDER BY nome";
		PreparedStatement stmt;
		Tecnico_de_Enfermagem enf;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, tecnico);
			stmt.setString(2, tecnico);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Tecnico_de_Enfermagem> Tecnicos_de_Enfermagem = new ArrayList<>();
			while (rs.next()) {
				enf = new Tecnico_de_Enfermagem(rs.getString("coren"), rs.getString("nome"), rs.getDate("dataInscricao"));
				Tecnicos_de_Enfermagem.add(enf);
			}
			rs.close();
			stmt.close();
			return Tecnicos_de_Enfermagem;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public int delete(Tecnico_de_Enfermagem enf) {
		
		int success = 0;
		String sql = "DELETE FROM Tecnico_de_Enfermagem WHERE id_tecEnfer = ?";
		PreparedStatement stmt;
		try {
			if (new TriagemDAO().have(enf)) {for (Triagem t : new TriagemDAO().getTecnicoEnfList(enf.getCoren())){new TriagemDAO().delete(t);}}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, enf.getCoren());
			success = stmt.executeUpdate();
			stmt.close();
			if(success == 1) {new EnfermariaDAO().delete(enf);}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}

	public int update(Tecnico_de_Enfermagem enf){
		EnfermariaDAO enfermaria = new EnfermariaDAO();
        int success = enfermaria.update(enf);
		return success;
	}
}
