package bin;

import java.sql.Date;

public class Tecnico_de_Enfermagem extends Enfermaria {

	public Tecnico_de_Enfermagem(String coren, String nome, Date dataInscricao) {
		super(coren, nome, dataInscricao);
	}
	
	public Tecnico_de_Enfermagem() {
	}
	
	public String getTecnico(){
		return this.coren + " | " + this.nome;
	}
	
	@Override
	public String toString() {
		return "Tecnico_de_Enfermagem [coren=" + coren + ", nome=" + nome + "]";
	}

}
