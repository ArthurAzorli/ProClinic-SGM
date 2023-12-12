package bin;

public class Alergias {
	private String alergia;

	public Alergias(String alergia) {
		this.alergia = alergia;
	}

	public Alergias() {
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	@Override
	public String toString() {
		return "Alergias [alergia=" + alergia + "]";
	}

	
}
