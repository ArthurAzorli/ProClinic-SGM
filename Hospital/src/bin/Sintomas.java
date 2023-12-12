package bin;

public class Sintomas {
	private String sintoma;

	public Sintomas(String sintoma) {
		this.sintoma = sintoma;
	}

	public Sintomas() {
		super();
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	@Override
	public String toString() {
		return "Sintomas [sintoma=" + sintoma + "]";
	}
	
}
