package bin;

public class Medicamentos {
	private String medicamento;

	public Medicamentos(String medicamento) {
		this.medicamento = medicamento;
	}

	public Medicamentos() {
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	@Override
	public String toString() {
		return "Medicamentos [medicamento=" + medicamento + "]";
	}

	
}
