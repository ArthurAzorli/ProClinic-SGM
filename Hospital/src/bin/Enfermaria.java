package bin;

import java.sql.Date;

public class Enfermaria {
	protected String coren;
	protected String nome;
	protected Date dataInscricao;
	
	public Enfermaria(String coren, String nome, Date dataInscricao) {
		super();
		this.coren = coren;
		this.nome = nome;
		this.dataInscricao = dataInscricao;
	}

	public Enfermaria() {
		super();
	}

	public String getCoren() {
		return coren;
	}

	public void setCoren(String coren) {
		this.coren = coren;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@Override
	public String toString() {
		return "Enfermaria [coren=" + coren + ", nome=" + nome + ", dataInscricao=" + dataInscricao + "]";
	}

	
	
}
