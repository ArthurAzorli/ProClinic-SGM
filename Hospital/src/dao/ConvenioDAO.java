package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bin.Convenio;
import bin.Paciente;
import utils.Text;

public class ConvenioDAO {
    private Connection connection;
	
	public ConvenioDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Convenio c){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Convenio(convenio, descricao, valor_mensal) VALUES (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getConvenio());
            stmt.setString(2, c.getDescricao());
            stmt.setDouble(3, c.getValor_mensal());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}

    public Convenio get(Integer id){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Convenio WHERE id = ?";
		PreparedStatement stmt;
		Convenio c = new Convenio();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				c = new Convenio(rs.getInt("id"), rs.getString("convenio"), rs.getString("descricao"), rs.getDouble("valor_mensal"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return c;
	}	

	public ArrayList<Convenio> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Convenio";
		PreparedStatement stmt;
		Convenio c;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Convenio> convenios = new ArrayList<>();
			while (rs.next()) {
				c = new Convenio(rs.getInt("id"), rs.getString("convenio"), rs.getString("descricao"), rs.getDouble("valor_mensal"));
				convenios.add(c);
			}
			rs.close();
			stmt.close();
			return convenios;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return null;
	}	
	
	public int delete(Convenio c) {
		for (Paciente p :  new PacienteDAO().getOfConvenio(c.getId())) {
			p.setConvenio(get(1));
			new PacienteDAO().update(p);
		}
		
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "DELETE FROM Convenio WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, c.getId());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}

	public int update(Convenio c){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Convenio SET convenio = ?, descricao = ?, valor_mensal = ? WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getConvenio());
            stmt.setString(2, c.getDescricao());
            stmt.setDouble(3, c.getValor_mensal());
            stmt.setInt(4, c.getId());
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public ArrayList<Convenio> search(String convenio){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Convenio WHERE convenio LIKE CONCAT('%',?,'%') ORDER BY id";
		PreparedStatement stmt;
		ArrayList<Convenio> convenios = new ArrayList<Convenio>();
		Convenio c;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, convenio);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				c = new Convenio(rs.getInt("id"), rs.getString("convenio"), rs.getString("descricao"), rs.getDouble("valor_mensal"));
				convenios.add(c);
			}
			rs.close();
			stmt.close();
			return convenios;
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}
	
	public boolean existNome(Convenio c) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM convenio WHERE convenio = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getConvenio());
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
	
	public boolean exist(Convenio c) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT COUNT(*) FROM convenio WHERE id = ?";
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
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return false;
	}
	
	public ArrayList<Object> getRelatorio(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT c.convenio, ROUND(SUM(c.valor_mensal),2) as valor_total, COUNT(p.cpf) as quantidade FROM Convenio c JOIN Paciente p ON c.id = p.convenio GROUP BY convenio";
		PreparedStatement stmt;
		ArrayList<Object> dados = new ArrayList<>();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dados.add(new Object[] {rs.getString("convenio"), Text.toMoney(rs.getDouble("valor_total")),  rs.getString("quantidade")});
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return dados;
	
	} 
	
	public ArrayList<Object> searchRelatorio(String relatorio){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT c.convenio, ROUND(SUM(c.valor_mensal),2) as valor_total, COUNT(p.cpf) as quantidade FROM Convenio c JOIN Paciente p ON c.id = p.convenio"
				+ " WHERE c.convenio LIKE CONCAT('%',?,'%') GROUP BY c.convenio";
		PreparedStatement stmt;
		ArrayList<Object> dados = new ArrayList<>();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, relatorio);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dados.add(new Object[] {rs.getString("convenio"), Text.toMoney(rs.getDouble("valor_total")),  rs.getString("quantidade")});
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return dados;
	
	} 
}
