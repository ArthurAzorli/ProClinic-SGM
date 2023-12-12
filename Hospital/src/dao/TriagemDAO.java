package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Paciente;
import bin.Pressao;
import bin.Sintomas;
import bin.Tecnico_de_Enfermagem;
import bin.Triagem;

public class TriagemDAO {
    private Connection connection;
	
	public TriagemDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Triagem t){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Triagem (dataTriagem, peso, urgencia, temperatura, pressaoDia, pressaoSys, id_tecnico, id_paciente) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setDate(1, t.getDataTriagem());
            stmt.setDouble(2, t.getPeso());
            stmt.setString(3, t.getUrgencia());
            stmt.setDouble(4, t.getTemperatura());
            stmt.setInt(5, t.getPressao().getPressaoDia());
            stmt.setInt(6, t.getPressao().getPressaoSys());
            stmt.setString(7, t.getId_tecnico());
            stmt.setString(8, t.getId_paciente());
			success = stmt.executeUpdate();	
			
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}

	public ArrayList<Triagem> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Triagem ORDER BY dataTriagem desc, urgencia";
        SintomasDAO s = new SintomasDAO();
        Pressao p = new Pressao();
		PreparedStatement stmt;
		Triagem t;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Triagem> triagens = new ArrayList<>();
			while (rs.next()) {
				p = new Pressao(rs.getInt("pressaoDia"), rs.getInt("pressaoSys"));
				t = new Triagem(rs.getInt("id"), rs.getDate("dataTriagem"), rs.getDouble("peso"), rs.getString("urgencia"), rs.getDouble("temperatura"), p, rs.getString("id_tecnico"), rs.getString("id_paciente"));
                t.setSintomas(s.getList(t));
				triagens.add(t);
			}
			rs.close();
			stmt.close();
			return triagens;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}	

    public ArrayList<Triagem> getPacienteList(String id_paciente){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Triagem WHERE id_paciente = ? ORDER BY dataTriagem desc, urgencia";
        SintomasDAO s = new SintomasDAO();
        Pressao p = new Pressao();
		PreparedStatement stmt;
		Triagem t = new Triagem();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.setString(1, id_paciente);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Triagem> triagens = new ArrayList<>();
			while (rs.next()) {
                p = new Pressao(rs.getInt("pressaoDia"), rs.getInt("pressaoSys"));
				t = new Triagem(rs.getInt("id"), rs.getDate("dataTriagem"), rs.getDouble("peso"), rs.getString("urgencia"), rs.getDouble("temperatura"), p, rs.getString("id_tecnico"), rs.getString("id_paciente"));
                t.setSintomas(s.getList(t));
                triagens.add(t);
			}
			rs.close();
			stmt.close();
			return triagens;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	} 

    public ArrayList<Triagem> getTecnicoEnfList(String id_tecnico){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Triagem WHERE id_tecnico = ? ORDER BY dataTriagem desc, urgencia";
        SintomasDAO s = new SintomasDAO();
        Pressao p = new Pressao();
		PreparedStatement stmt;
		Triagem t = new Triagem();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
            stmt.setString(1, id_tecnico);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Triagem> triagens = new ArrayList<>();
			while (rs.next()) {
                p = new Pressao(rs.getInt("pressaoDia"), rs.getInt("pressaoSys"));
				t = new Triagem(rs.getInt("id"), rs.getDate("dataTriagem"), rs.getDouble("peso"), rs.getString("urgencia"), rs.getDouble("temperatura"), p, rs.getString("id_tecnico"), rs.getString("id_paciente"));
                t.setSintomas(s.getList(t));
                triagens.add(t);
			}
			rs.close();
			stmt.close();
			return triagens;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
    
    public Triagem get(Integer id){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Triagem WHERE id = ? ORDER BY dataTriagem desc, urgencia";
		PreparedStatement stmt;
		SintomasDAO s = new SintomasDAO();
		Pressao p = new Pressao();
		Triagem t = new Triagem();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				 	p = new Pressao(rs.getInt("pressaoDia"), rs.getInt("pressaoSys"));
					t = new Triagem(rs.getInt("id"), rs.getDate("dataTriagem"), rs.getDouble("peso"), rs.getString("urgencia"), rs.getDouble("temperatura"), p, rs.getString("id_tecnico"), rs.getString("id_paciente"));
	                t.setSintomas(s.getList(t));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return t;
	}	
    
    public ArrayList<Triagem> search(String triagem){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Triagem t JOIN Paciente p on t.id_paciente = p.cpf JOIN Enfermaria tec on t.id_tecnico = tec.coren WHERE "
				+ "p.nome LIKE CONCAT('%',?,'%') OR p.cpf LIKE CONCAT('%',?,'%') OR tec.nome LIKE CONCAT('%',?,'%') OR tec.coren LIKE CONCAT('%',?,'%') OR t.urgencia LIKE CONCAT('%',?,'%')"
				+ "ORDER BY dataTriagem desc, urgencia";
        SintomasDAO s = new SintomasDAO();
        Pressao p = new Pressao();
		PreparedStatement stmt;
		Triagem t;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, triagem);
			stmt.setString(2, triagem);
			stmt.setString(3, triagem);
			stmt.setString(4, triagem);
			stmt.setString(5, triagem);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Triagem> triagens = new ArrayList<>();
			while (rs.next()) {
				p = new Pressao(rs.getInt("pressaoDia"), rs.getInt("pressaoSys"));
				t = new Triagem(rs.getInt("id"), rs.getDate("dataTriagem"), rs.getDouble("peso"), rs.getString("urgencia"), rs.getDouble("temperatura"), p, rs.getString("id_tecnico"), rs.getString("id_paciente"));
                t.setSintomas(s.getList(t));
				triagens.add(t);
			}
			rs.close();
			stmt.close();
			return triagens;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}	
    
	public int delete(Triagem t) {
		
		int success = 0;
		String sql = "DELETE FROM Triagem WHERE id = ?";
		PreparedStatement stmt;
		try {
			if (new SintomasDAO().have(t)) {for (Sintomas s: new SintomasDAO().getList(t)) {new SintomasDAO().delete(s, t);}}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, t.getId());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}

    public int update(Triagem t){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Triagem SET dataTriagem = ?, peso = ?, urgencia = ?, temperatura = ?, pressaoDia = ?, pressaoSys = ?, id_tecnico = ?, id_paciente = ? WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setDate(1, t.getDataTriagem());
            stmt.setDouble(2, t.getPeso());
            stmt.setString(3, t.getUrgencia());
            stmt.setDouble(4, t.getTemperatura());
            stmt.setInt(5, t.getPressao().getPressaoDia());
            stmt.setInt(6, t.getPressao().getPressaoSys());
            stmt.setString(7, t.getId_tecnico());
            stmt.setString(8, t.getId_paciente());
            stmt.setInt(9, t.getId());
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
		
		String sql = "SELECT COUNT(*) FROM Triagem WHERE id_paciente = ?";
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
    
    public boolean have(Tecnico_de_Enfermagem t) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM Triagem WHERE id_tecnico = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, t.getCoren());
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
    
    public Integer getIdOf(Triagem t) {
   		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
    	String sql = "SELECT id FROM Triagem WHERE dataTriagem = ? and peso = ? and urgencia = ? and temperatura = ? and pressaoDia = ? and pressaoSys = ? and id_tecnico = ? and id_paciente = ? "
    			+ "ORDER BY dataTriagem, urgencia";
		PreparedStatement stmt;
		Integer id = null;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setDate(1, t.getDataTriagem());
            stmt.setDouble(2, t.getPeso());
            stmt.setString(3, t.getUrgencia());
            stmt.setDouble(4, t.getTemperatura());
            stmt.setInt(5, t.getPressao().getPressaoDia());
            stmt.setInt(6, t.getPressao().getPressaoSys());
            stmt.setString(7, t.getId_tecnico());
            stmt.setString(8, t.getId_paciente());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
					id = rs.getInt("id");
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		System.out.println(id);
		return id;
    }
}
