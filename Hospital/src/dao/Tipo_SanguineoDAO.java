package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tipo_SanguineoDAO {
	private Connection connection;

	public Tipo_SanguineoDAO() {
		this.connection = new Conexao().getConnection();
	}
	
	
	public ArrayList<String> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM tipo_sanguineo";
		PreparedStatement stmt;
		String t;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> tipos = new ArrayList<>();
			while (rs.next()) {
				t =  rs.getString("tipo");
				tipos.add(t);
			}
			rs.close();
			stmt.close();
			return tipos;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}	
}
