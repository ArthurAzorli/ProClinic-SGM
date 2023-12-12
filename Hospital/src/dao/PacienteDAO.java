package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import bin.Consulta;
import bin.Convenio;
import bin.Endereco;
import bin.Paciente;
import bin.Telefones;
import bin.Triagem;

public class PacienteDAO {
    private Connection connection;
	
	public PacienteDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Paciente p){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Paciente(cpf, nome, dataNasc, convenio, rua, nro, bairro, cidade, uf) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getCPF());
			stmt.setString(2, p.getNome());
            stmt.setDate(3, p.getDataNasc());
			stmt.setInt(4, p.getConvenio().getId());
			stmt.setString(5, p.getEndereco().getRua());
			stmt.setString(6, p.getEndereco().getNro());
			stmt.setString(7, p.getEndereco().getBairro());
			stmt.setString(8, p.getEndereco().getCidade());
			stmt.setString(9, p.getEndereco().getUF());
			success = stmt.executeUpdate();
			if (success == 1) {
				for (Telefones tel: p.getTelefone()) {new TelefonesDAO().create(tel, p);}
			}
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public ArrayList<Paciente> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Paciente ORDER BY nome";
		PreparedStatement stmt;
		Paciente p;
		Endereco end;
		Convenio conv;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Paciente> Pacientes = new ArrayList<>();
			while (rs.next()) {
				end = new Endereco(rs.getString("rua"), rs.getString("nro"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("uf"));
				conv = new ConvenioDAO().get(rs.getInt("convenio"));
				p = new Paciente(rs.getString("cpf"), rs.getString("nome"), rs.getDate("dataNasc"), conv, end);
				p.setTelefone(new TelefonesDAO().getList(p));
				Pacientes.add(p);
			}
			rs.close();
			stmt.close();
			return Pacientes;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	public ArrayList<Paciente> search(String paciente){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Paciente WHERE nome LIKE CONCAT('%',?,'%') OR cpf LIKE CONCAT('%',?,'%') ORDER BY nome";
		PreparedStatement stmt;
		Paciente p;
		Endereco end;
		Convenio conv;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, paciente);
			stmt.setString(2, paciente);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Paciente> Pacientes = new ArrayList<>();
			
			while (rs.next()) {
				end = new Endereco(rs.getString("rua"), rs.getString("nro"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("uf"));
				conv = new ConvenioDAO().get(rs.getInt("convenio"));
				p = new Paciente(rs.getString("cpf"), rs.getString("nome"), rs.getDate("dataNasc"), conv, end);
				p.setTelefone(new TelefonesDAO().getList(p));
				Pacientes.add(p);
			}
			rs.close();
			stmt.close();
			return Pacientes;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}

	public Paciente get(String cpf){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Paciente WHERE cpf = ?";
		PreparedStatement stmt;
		Paciente p = new Paciente();
		Endereco end;
		Convenio conv;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				end = new Endereco(rs.getString("rua"), rs.getString("nro"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("uf"));
				conv = new ConvenioDAO().get(rs.getInt("convenio"));
				p = new Paciente(rs.getString("cpf"), rs.getString("nome"), rs.getDate("dataNasc"), conv, end);
				p.setTelefone(new TelefonesDAO().getList(p));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return p;
	}
	
	public int delete(Paciente p) {
		
		int success = 0;
		String sql = "DELETE FROM Paciente WHERE cpf = ?";
		PreparedStatement stmt;
		try {
			if (new TelefonesDAO().have(p)) {new TelefonesDAO().deleteAll(p);}
			if (new Ficha_TecnicaDAO().have(p)) {new Ficha_TecnicaDAO().deleteOfPaciente(p.getCPF());}
			if (new ConsultaDAO().have(p)) {for (Consulta c: new ConsultaDAO().getPacienteList(p.getCPF())) {new ConsultaDAO().delete(c);}}
			if (new TriagemDAO().have(p)) {for (Triagem t: new TriagemDAO().getPacienteList(p.getCPF())) {new TriagemDAO().delete(t);}}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
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
	
	public int update(Paciente p) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Paciente SET nome = ?, dataNasc = ?, convenio = ?, rua = ?, nro = ?, bairro = ?, cidade = ?, uf = ? WHERE cpf = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, p.getNome());
            stmt.setDate(2, p.getDataNasc());
			stmt.setInt(3, p.getConvenio().getId());
			stmt.setString(4, p.getEndereco().getRua());
			stmt.setString(5, p.getEndereco().getNro());
			stmt.setString(6, p.getEndereco().getBairro());
			stmt.setString(7, p.getEndereco().getCidade());
			stmt.setString(8, p.getEndereco().getUF());
			stmt.setString(9, p.getCPF());
			success = stmt.executeUpdate();
			if (success==1) {
				new TelefonesDAO().deleteAll(p);
				for (Telefones tel: p.getTelefone()) {
					new TelefonesDAO().create(tel, p);
				}
			}
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public boolean exist(Paciente p) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM paciente WHERE cpf = ?";
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
	
	public ArrayList<Paciente> getOfConvenio(Integer convenio){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Paciente WHERE convenio = ? ORDER BY nome";
		PreparedStatement stmt;
		Paciente p;
		Endereco end;
		Convenio conv;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, convenio);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Paciente> Pacientes = new ArrayList<>();
			while (rs.next()) {
				end = new Endereco(rs.getString("rua"), rs.getString("nro"), rs.getString("bairro"), rs.getString("cidade"), rs.getString("uf"));
				conv = new ConvenioDAO().get(rs.getInt("convenio"));
				p = new Paciente(rs.getString("cpf"), rs.getString("nome"), rs.getDate("dataNasc"), conv, end);
				p.setTelefone(new TelefonesDAO().getList(p));
				Pacientes.add(p);
			}
			rs.close();
			stmt.close();
			return Pacientes;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}
	
	
}
