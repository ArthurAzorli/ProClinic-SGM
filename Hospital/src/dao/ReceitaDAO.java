package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bin.Consulta;
import bin.Medicamentos;
import bin.Receita;


public class ReceitaDAO {
    private Connection connection;
	
	public ReceitaDAO() {
		this.connection = new Conexao().getConnection();
	}

	public int create(Receita r){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "INSERT INTO Receita (id_consulta, descricao) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, r.getId_consulta());
			stmt.setString(2, r.getDescricao());
			success = stmt.executeUpdate();
			stmt.close();
			if (success == 1) {
				for (Medicamentos m: r.getMedicamentos()) {new MedicamentosDAO().create(m, getOfConsulta(r.getId_consulta()));}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}

	public ArrayList<Receita> getAll(){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Receita";
        MedicamentosDAO m = new MedicamentosDAO();
		PreparedStatement stmt;
		Receita r;
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Receita> receitas = new ArrayList<>();
			while (rs.next()) {
				r = new Receita(rs.getInt("id"), rs.getInt("id_consulta"), rs.getString("descricao"));
                r.setMedicamentos(m.getList(r));
				receitas.add(r);
			}
			rs.close();
			stmt.close();
			return receitas;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return null;
	}	

    public Receita getOfConsulta(Integer id_consulta){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		String sql = "SELECT * FROM Receita WHERE id_consulta = ?";
        MedicamentosDAO m = new MedicamentosDAO();
		PreparedStatement stmt;
		Receita r = new Receita();
		
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, id_consulta);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				r = new Receita(rs.getInt("id"), rs.getInt("id_consulta"), rs.getString("descricao"));
                r.setMedicamentos(m.getList(r));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return r;
	}
	
	public int delete(Receita r) {
		
		int success = 0;
		String sql = "DELETE FROM Receita WHERE id = ?";
		PreparedStatement stmt;
		try {
			if (new MedicamentosDAO().have(r))for (Medicamentos m: r.getMedicamentos()) {new MedicamentosDAO().delete(m, r);}
			
			if (new Conexao().isClosedConncetion(this.connection)){
				this.connection = new Conexao().getConnection();
			}
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, r.getId());
			success = stmt.executeUpdate();
			
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			new Conexao().closeConncetion(connection);
		}
		return success;
	}

    public int update(Receita r){
		if (new Conexao().isClosedConncetion(this.connection)){
			this.connection = new Conexao().getConnection();
		}
		
		int success = 0;
		String sql = "UPDATE Receita SET id_consulta = ?, descricao = ? WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, r.getId_consulta());
			stmt.setString(2, r.getDescricao());
            stmt.setInt(3, r.getId());
			success = stmt.executeUpdate();
			if (success == 1) {
				for (Medicamentos m: new MedicamentosDAO().getList(r)) {new MedicamentosDAO().delete(m, r);}
				for (Medicamentos m: r.getMedicamentos()) {new MedicamentosDAO().create(m, getOfConsulta(r.getId_consulta()));}
			}
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
		
		String sql = "SELECT COUNT(*) FROM Receita WHERE id_consulta = ?";
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
}
