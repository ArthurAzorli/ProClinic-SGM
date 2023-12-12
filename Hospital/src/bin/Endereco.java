package bin;

public class Endereco {
	private String rua;
	private String nro;
	private String bairro;
	private String cidade;
	private String uf;
	
	public Endereco(String rua, String nro, String bairro, String cidade, String UF) {
		this.rua = rua;
		this.nro = nro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = UF;
	}
	
	public Endereco() {
	}
	
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUF() {
		return uf;
	}
	public void setUF(String UF) {
		this.uf = UF;
	}
	
	@Override
	public String toString() {
		return cidade+"-"+uf+", "+bairro+", "+rua +", "+nro;
	}
	
}
