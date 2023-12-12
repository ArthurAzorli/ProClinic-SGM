package bin;

public class Convenio {
	private Integer id;
	private String convenio;
	private String descricao;
	private Double valor_mensal;
	
	public Convenio(Integer id, String convenio, String descricao, Double valor_mensal) {
		this.id = id;
		this.convenio = convenio;
		this.descricao = descricao;
		this.valor_mensal = valor_mensal;
	}
	
	public Convenio(String convenio, String descricao, Double valor_mensal) {
		this.convenio = convenio;
		this.descricao = descricao;
		this.valor_mensal = valor_mensal;
	}
	
	public Convenio() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor_mensal() {
		return valor_mensal;
	}

	public void setValor_mensal(Double valor_mensal) {
		this.valor_mensal = valor_mensal;
	}

	@Override
	public String toString() {
		return "Convenio [id=" + id + ", convenio=" + convenio + ", descricao=" + descricao + ", valor_mensal="
				+ valor_mensal + "]";
	}
	
}
