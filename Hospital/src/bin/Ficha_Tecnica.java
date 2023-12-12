package bin;

import java.util.ArrayList;

public class Ficha_Tecnica {
	private Integer prontuario;
	private String id_Paciente;
	private String historico_familiar;
	private String tipo_Sanguineo;
	private ArrayList <Alergias> alergia;
	
	public Ficha_Tecnica(Integer prontuario, String id_Paciente, String historico_familiar, String tipo_Sanguineo) {
		this.prontuario = prontuario;
		this.id_Paciente = id_Paciente;
		this.historico_familiar = historico_familiar;
		this.tipo_Sanguineo = tipo_Sanguineo;
	}
	
	public Ficha_Tecnica(String id_Paciente, String historico_familiar, String tipo_Sanguineo) {
		this.id_Paciente = id_Paciente;
		this.historico_familiar = historico_familiar;
		this.tipo_Sanguineo = tipo_Sanguineo;
	}

	public Ficha_Tecnica() {
	}

	public Integer getProntuario() {
		return prontuario;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	public String getId_Paciente() {
		return id_Paciente;
	}

	public void setId_Paciente(String id_Paciente) {
		this.id_Paciente = id_Paciente;
	}

	public String getHistorico_familiar() {
		return historico_familiar;
	}

	public void setHistorico_familiar(String historico_familiar) {
		this.historico_familiar = historico_familiar;
	}

	public String getTipo_Sanguineo() {
		return tipo_Sanguineo;
	}

	public void setTipo_Sanguineo(String tipo_Sanguineo) {
		this.tipo_Sanguineo = tipo_Sanguineo;
	}
	
	public ArrayList<Alergias> getAlergia() {
		return alergia;
	}
	
	public void setAlergia(ArrayList<Alergias> alergias) {
		this.alergia = alergias;
	}
	
	@Override
	public String toString() {
		return "Ficha_Tecnica [prontuario=" + prontuario + ", id_Paciente=" + id_Paciente + ", historico_familiar="
				+ historico_familiar + ", tipo_Sanguineo=" + tipo_Sanguineo + ", alergia=" + alergia + "]";
	}

	public void addAlergias(Alergias alergia) {
		if(this.alergia==null) {
			this.alergia= new ArrayList <>();
		}
		this.alergia.add(alergia);
	}
	
}
