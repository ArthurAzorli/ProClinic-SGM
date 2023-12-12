package bin;

public class Telefones {
	private String telefone;

	public Telefones(String telefone) {
		this.telefone = telefone;
	}

	public Telefones() {
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Telefones [telefone=" + telefone + "]";
	}
	
}
