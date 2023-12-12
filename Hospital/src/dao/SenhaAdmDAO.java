package dao;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SenhaAdmDAO {
	private Connection connection;
	
	public SenhaAdmDAO() {
		this.connection = new Conexao().getConnection();
	}
	
	public int create(String senha){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		// Criptografia baseada de https://gist.github.com/joaomosantos/ce7cb50475e34680d000
		try {
	        MessageDigest m = MessageDigest.getInstance("MD5");
	        m.update(senha.getBytes(), 0, senha.length());
	        senha = new BigInteger(1, m.digest()).toString(16);
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null, "Erro ao Criptografar a Senha!");
	        return -1;
	    }
		int success = 0;
		String sql = "INSERT INTO senhaADM(senha) VALUES (?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, senha);
			success = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		
		return success;
	}
	
	public boolean exist() {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		String sql = "SELECT COUNT(*) FROM senhaADM";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
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
	
	public boolean validSenha(String senha) {
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}

		// Criptografia baseada de https://gist.github.com/joaomosantos/ce7cb50475e34680d000
		try {
	        MessageDigest m = MessageDigest.getInstance("MD5");
	        m.update(senha.getBytes(), 0, senha.length());
	        senha = new BigInteger(1, m.digest()).toString(16);
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Erro ao Criptografar a Senha!");
	    }
		
		String sql = "SELECT COUNT(*) FROM senhaADM WHERE senha = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, senha);
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
