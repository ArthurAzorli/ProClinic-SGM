package bin;

import java.sql.Date;
import java.util.ArrayList;

public class Triagem {
	private Integer id;
	private Date dataTriagem;
	private Double peso;
	private String urgencia;
	private Double temperatura;
	private Pressao pressao;
	private String id_tecnico;
	private String id_paciente;
	private ArrayList <Sintomas> sintomas;
	
	public Triagem(Integer id, Date dataTriagem, Double peso, String urgencia, Double temperatura, Pressao pressao, String id_tecnico, String id_paciente) {
		this.id = id;
		this.dataTriagem = dataTriagem;
		this.peso = peso;
		this.urgencia = urgencia;
		this.temperatura = temperatura;
		this.pressao = pressao;
		this.id_tecnico = id_tecnico;
		this.id_paciente = id_paciente;
	}
	
	public Triagem(Date dataTriagem, Double peso, String urgencia, Double temperatura, Pressao pressao, String id_tecnico, String id_paciente) {
		this.dataTriagem = dataTriagem;
		this.peso = peso;
		this.urgencia = urgencia;
		this.temperatura = temperatura;
		this.pressao = pressao;
		this.id_tecnico = id_tecnico;
		this.id_paciente = id_paciente;
	}

	public Triagem() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataTriagem() {
		return dataTriagem;
	}

	public void setDataTriagem(Date dataTriagem) {
		this.dataTriagem = dataTriagem;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(String urgencia) {
		this.urgencia = urgencia;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Pressao getPressao() {
		return pressao;
	}

	public void setPressaoDia(Pressao pressao) {
		this.pressao = pressao;
	}

	public String getId_tecnico() {
		return id_tecnico;
	}

	public void setId_tecnico(String id_tecnico) {
		this.id_tecnico = id_tecnico;
	}

	public String getId_paciente() {
		return id_paciente;
	}

	public void setId_pacientes(String id_paciente) {
		this.id_paciente = id_paciente;
	}

	public ArrayList<Sintomas> getSintomas() {
		return sintomas;
	}

	public void setSintomas(ArrayList<Sintomas> sintomas){
		this.sintomas = sintomas;
	}

	public void addSintomas(Sintomas sintoma) {
		if(this.sintomas==null) {
			this.sintomas= new ArrayList <>();
		}
		this.sintomas.add(sintoma);
	}
	
	@Override
	public String toString() {
		return "Triagem [id=" + id + ", dataTriagem=" + dataTriagem + ", peso=" + peso + ", urgencia=" + urgencia
				+ ", temperatura=" + temperatura + ", pressaoDia=" + pressao.getPressaoDia() + ", pressaoSys=" + pressao.getPressaoSys()
				+ ", id_tecnicos=" + id_tecnico + ", id_pacientes=" + id_paciente + ", sintomas=" + sintomas + "]";
	}
	
}
