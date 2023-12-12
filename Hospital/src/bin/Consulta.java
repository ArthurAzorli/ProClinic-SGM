package bin;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import dao.MedicoDAO;
import dao.PacienteDAO;
import utils.Data;

public class Consulta {
	private Integer id;
	private String id_medico;
	private String id_paciente;
	private Double valor;
	private Date dataConsulta;
	private Time horario;
	private String diagnostico;

	public Consulta(Integer id, String id_medico, String id_paciente, Double valor, Date dataConsulta, Time horario, String diagnostico) {
		this.id = id;
		this.id_medico = id_medico;
		this.id_paciente = id_paciente;
		this.valor = valor;
		this.dataConsulta = dataConsulta;
		this.horario = horario;
		this.diagnostico = diagnostico;
	}
	
	public Consulta(String id_medico, String id_paciente, Double valor, Date dataConsulta, Time horario, String diagnostico) {
		this.id_medico = id_medico;
		this.id_paciente = id_paciente;
		this.valor = valor;
		this.dataConsulta = dataConsulta;
		this.horario = horario;
		this.diagnostico = diagnostico;
	}


	public Consulta() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getId_medico() {
		return id_medico;
	}


	public void setId_medico(String id_medico) {
		this.id_medico = id_medico;
	}


	public String getId_paciente() {
		return id_paciente;
	}


	public void setId_paciente(String id_paciente) {
		this.id_paciente = id_paciente;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Date getDataConsulta() {
		return dataConsulta;
	}


	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}


	public Time getHorario() {
		return horario;
	}


	public void setHorario(Time horario) {
		this.horario = horario;
	}


	public String getDiagnostico() {
		return diagnostico;
	}


	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	public String getConsulta() {
		return "Consulta do(a) Paciente: " + new PacienteDAO().get(this.id_paciente).getNome() + " com o(a) Médico(a): "+ new MedicoDAO().get(this.id_medico)+" no Dia:"+Data.toDateBrFormat(dataConsulta);
	}
	

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", id_medico=" + id_medico + ", id_paciente=" + id_paciente + ", valor=" + valor
				+ ", dataConsulta=" + dataConsulta + ", horario=" + horario + ", diagnostico=" + diagnostico
				+ "]";
	}



	
	
	
	
	
	
	
	
}
