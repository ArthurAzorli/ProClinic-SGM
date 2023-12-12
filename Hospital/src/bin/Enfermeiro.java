package bin;

import java.sql.Date;

public class Enfermeiro extends Enfermaria {
	private String especializacao;

	public Enfermeiro(String coren, String nome, String especializacao, Date dataInscricao) {
		super(coren, nome, dataInscricao);
		this.especializacao = especializacao;
	}
	
	public Enfermeiro() {
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	@Override
	public String toString() {
		return "Enfermeiro [especializacao=" + especializacao + ", coren=" + coren + ", nome=" + nome
				+ ", dataInscricao=" + dataInscricao + "]";
	}

	
	
}
