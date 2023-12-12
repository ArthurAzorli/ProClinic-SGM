package bin;

import java.sql.Date;

public class Medico {
	private String crm;
	private String nome;
	private Date dataInscricao;
	private String especializacao;
	
	public Medico(String crm, String nome, Date dataInscricao, String especializacao) {
		this.crm = crm;
		this.nome = nome;
		this.dataInscricao = dataInscricao;
		this.especializacao = especializacao;
	}

	public Medico() {
	}

	public String getCRM() {
		return crm;
	}

	public void setCRM(String crm) {
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	
	public String getMedico() {
		return this.crm + " | " + this.nome;
	}

	@Override
	public String toString() {
		return "Medico [crm=" + crm + ", nome=" + nome + ", dataInscricao=" + dataInscricao + ", especializacao="
				+ especializacao + "]";
	}

	
}
