package bin;

public class Auxilia {
	private Integer id_consulta;
	private String id_enfer;
	
	public Auxilia(Integer id_consulta, String id_enfer) {
		this.id_consulta = id_consulta;
		this.id_enfer = id_enfer;
	}

	public Auxilia() {
	}

	public Integer getId_consulta() {
		return id_consulta;
	}

	public void setId_consulta(Integer id_consulta) {
		this.id_consulta = id_consulta;
	}

	public String getId_enfer() {
		return id_enfer;
	}

	public void setId_enfer(String id_enfer) {
		this.id_enfer = id_enfer;
	}

	@Override
	public String toString() {
		return "Auxilia [id_consulta=" + id_consulta + ", id_enfer=" + id_enfer + "]";
	}

}
