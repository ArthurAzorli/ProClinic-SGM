package bin;

public class Pressao {
	private Integer pressaoDia;
	private Integer pressaoSys;
	
	public Pressao(Integer pressaoDia, Integer pressaoSys) {
		this.pressaoDia = pressaoDia;
		this.pressaoSys = pressaoSys;
	}
	
	public Pressao() {}

	public Integer getPressaoDia() {
		return pressaoDia;
	}

	public void setPressaoDia(Integer pressaoDia) {
		this.pressaoDia = pressaoDia;
	}

	public Integer getPressaoSys() {
		return pressaoSys;
	}

	public void setPressaoSys(Integer pressaoSys) {
		this.pressaoSys = pressaoSys;
	}
	
	public String getPressaoNomeada() {
		return (pressaoSys/10) + "x" + (pressaoDia/10)+" mmHg";
	}
	
	public String getCategoria() {
		if(this.pressaoSys<120 && this.pressaoDia<80) {
			return "Normal";
		}else if(this.pressaoSys>=120 && this.pressaoSys<=129 && this.pressaoDia<80){
			return "Elevada";
		}else if((this.pressaoSys>=130 && this.pressaoSys<=139) || (this.pressaoDia>=80 && this.pressaoDia<=89)) {
			return "Hipertensão estágio 1";
		}else if((this.pressaoSys>=140 && this.pressaoSys<=179) || (this.pressaoDia>=90 && this.pressaoDia<=119)) {
			return "Hipertensão estágio 2";
		}else{
			return "Crise hipertensiva";
		}
	}
	
	
	
}
