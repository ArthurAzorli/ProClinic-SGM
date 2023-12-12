package bin;

import java.util.ArrayList;

public class Receita {
	private Integer id;
	private Integer id_consulta;
	private String descricao;
	private ArrayList <Medicamentos> medicamentos;
	
	public Receita(Integer id, Integer id_consulta, String descricao) {
		this.id = id;
		this.id_consulta = id_consulta;
		this.descricao = descricao;
	}
	
	public Receita(Integer id_consulta, String descricao) {
		this.id_consulta = id_consulta;
		this.descricao = descricao;
	}

	public Receita() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(Integer id_consulta) {
		this.id_consulta = id_consulta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Medicamentos> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(ArrayList<Medicamentos> medicamentos){
		this.medicamentos = medicamentos;
	}

	@Override
	public String toString() {
		return "Receita [id=" + id + ", id_consulta=" + id_consulta + ", descricao=" + descricao + ", medicamentos="
				+ medicamentos + "]";
	}

	public void addMedicamento(Medicamentos medicamento) {
		if(this.medicamentos==null) {
			this.medicamentos= new ArrayList <>();
		}
		this.medicamentos.add(medicamento);
	}
	
}
