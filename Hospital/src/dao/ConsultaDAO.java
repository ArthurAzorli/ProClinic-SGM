package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bin.Auxilia;
import bin.Consulta;
import bin.Medico;
import bin.Paciente;

public class ConsultaDAO {
    private Connection connection;
	
	public ConsultaDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Consulta c){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Consulta(id_medico, id_paciente, valor, dataConsulta, horario, diagnostico) VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getId_medico());
			stmt.setString(2, c.getId_paciente());
            stmt.setDouble(3, c.getValor());
            stmt.setDate(4, c.getDataConsulta());
            stmt.setTime(5, c.getHorario());
            stmt.setString(6, c.getDiagnostico());
			success = stmt.executeUpdate();
			
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	public ArrayList<Consulta> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Consulta ORDER BY dataConsulta desc, horario desc";
		PreparedStatement stmt;
		Consulta c;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Consulta> consultas = new ArrayList<>();
			while (rs.next()) {
				c = new Consulta(rs.getInt("id"), rs.getString("id_medico"), rs.getString("id_paciente"), rs.getDouble("valor"), rs.getDate("dataConsulta"), rs.getTime("horario"), rs.getString("diagnostico"));
				consultas.add(c);
			}
			rs.close();
			stmt.close();
			return consultas;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	

	public ArrayList<Consulta> getPacienteList(String id_paciente){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Consulta WHERE id_paciente = ? ORDER BY dataConsulta desc, horario desc";
		PreparedStatement stmt;
		Consulta c;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, id_paciente);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Consulta> consultas = new ArrayList<>();
			while (rs.next()) {
				c = new Consulta(rs.getInt("id"), rs.getString("id_medico"), rs.getString("id_paciente"), rs.getDouble("valor"), rs.getDate("dataConsulta"), rs.getTime("horario"), rs.getString("diagnostico"));
				consultas.add(c);
			}
			rs.close();
			stmt.close();
			return consultas;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Consulta> getMedicoList(String id_medico){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Consulta WHERE id_medico = ? ORDER BY dataConsulta desc, horario desc";
		PreparedStatement stmt;
		Consulta c;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, id_medico);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Consulta> consultas = new ArrayList<>();
			while (rs.next()) {
				c = new Consulta(rs.getInt("id"), rs.getString("id_medico"), rs.getString("id_paciente"), rs.getDouble("valor"), rs.getDate("dataConsulta"), rs.getTime("horario"), rs.getString("diagnostico"));
				consultas.add(c);
			}
			rs.close();
			stmt.close();
			return consultas;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Consulta> getListAtDate(Date dataConsulta){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Consulta WHERE dataConsulta = ? ORDER BY dataConsulta desc, horario desc";
		PreparedStatement stmt;
		Consulta c;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setDate(1, dataConsulta);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Consulta> consultas = new ArrayList<>();
			while (rs.next()) {
				c = new Consulta(rs.getInt("id"), rs.getString("id_medico"), rs.getString("id_paciente"), rs.getDouble("valor"), rs.getDate("dataConsulta"), rs.getTime("horario"), rs.getString("diagnostico"));
				consultas.add(c);
			}
			rs.close();
			stmt.close();
			return consultas;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Consulta get(Integer id){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Consulta WHERE id = ?";
		PreparedStatement stmt;
		Consulta c = new Consulta();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				c = new Consulta(rs.getInt("id"), rs.getString("id_medico"), rs.getString("id_paciente"), rs.getDouble("valor"), rs.getDate("dataConsulta"), rs.getTime("horario"), rs.getString("diagnostico"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public Integer getID(Consulta c){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT id FROM Consulta WHERE id_medico = ? && id_paciente = ? && valor = ? && dataConsulta = ? && horario = ? && diagnostico = ? ORDER BY id";
		PreparedStatement stmt;
		Integer id = null;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getId_medico());
			stmt.setString(2, c.getId_paciente());
            stmt.setDouble(3, c.getValor());
            stmt.setDate(4, c.getDataConsulta());
            stmt.setTime(5, c.getHorario());
            stmt.setString(6, c.getDiagnostico());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				id= rs.getInt("id");
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	
	public ArrayList<Consulta> search(String consulta){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Consulta c JOIN Paciente p ON c.id_paciente = p.cpf JOIN Medico m ON c.id_medico = m.crm WHERE p.cpf LIKE CONCAT('%',?,'%') OR  p.nome LIKE CONCAT('%',?,'%') OR  m.crm LIKE CONCAT('%',?,'%') OR m.nome LIKE CONCAT('%',?,'%') OR  m.especializacao LIKE CONCAT('%',?,'%') ORDER BY dataConsulta desc, horario desc";
		PreparedStatement stmt;
		Consulta c;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, consulta);
			stmt.setString(2, consulta);
			stmt.setString(3, consulta);
			stmt.setString(4, consulta);
			stmt.setString(5, consulta);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Consulta> consultas = new ArrayList<>();
			
			while (rs.next()) {
				c = new Consulta(rs.getInt("id"), rs.getString("id_medico"), rs.getString("id_paciente"), rs.getDouble("valor"), rs.getDate("dataConsulta"), rs.getTime("horario"), rs.getString("diagnostico"));
				consultas.add(c);
			}
			rs.close();
			stmt.close();
			return consultas;
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}
	
	public int delete(Consulta c) {
		
		int success = 0;
		String sql = "DELETE FROM Consulta WHERE id = ?";
		PreparedStatement stmt;
		try {
			if (new AuxiliaDAO().have(c)) {for (Auxilia a: new AuxiliaDAO().getConsultaList(c.getId())) {new AuxiliaDAO().delete(a);}}
			if (new ReceitaDAO().have(c)) {new ReceitaDAO().delete(new ReceitaDAO().getOfConsulta(c.getId()));}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, c.getId());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	public int update(Consulta c){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Consulta SET id_medico = ?, id_paciente = ?, valor = ?, dataConsulta = ?, horario = ?, diagnostico = ? WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getId_medico());
			stmt.setString(2, c.getId_paciente());
            stmt.setDouble(3, c.getValor());
            stmt.setDate(4, c.getDataConsulta());
            stmt.setTime(5, c.getHorario());
            stmt.setString(6, c.getDiagnostico());
			stmt.setInt(7, c.getId());
			success = stmt.executeUpdate();
			if (success == 1) {
				for (Auxilia a: new AuxiliaDAO().getConsultaList(c.getId())) {new AuxiliaDAO().delete(a);}
			}
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean exist(Consulta c) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM consulta WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, c.getId());
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
		}
		return false;
	}
	
	public boolean have(Paciente p) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM Consulta WHERE id_paciente = ?";
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
		}
		return false;
	}
	
	public boolean have(Medico m) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM Consulta WHERE id_medico = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, m.getCRM());
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
		}
		return false;
	}

}
