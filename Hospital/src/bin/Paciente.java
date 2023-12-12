package bin;

import java.sql.Date;
import java.util.ArrayList;

public class Paciente {
	private String cpf;
	private String nome;
	private Date dataNasc;
	private Convenio convenio;
	private Endereco endereco;
	private ArrayList <Telefones> telefone;
	
	
	public Paciente(String CPF, String nome, Date dataNasc, Convenio convenio, Endereco endereco) {
		this.cpf = CPF;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.convenio = convenio;
		this.endereco = endereco;
	}


	public Paciente() {
	}


	public String getCPF() {
		return cpf;
	}


	public void setCPF(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public Convenio getConvenio() {
		return convenio;
	}


	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public ArrayList<Telefones> getTelefone() {
		return telefone;
	}

	public void setTelefone(ArrayList<Telefones> telefones){
		this.telefone = telefones;
	}

	public void addTelefones(Telefones telefone) {
		if(this.telefone==null) {
			this.telefone= new ArrayList <>();
		}
		this.telefone.add(telefone);
	}
	
	public String getPaciente() {
		return this.cpf + " | " + this.nome ;
	}

	@Override
	public String toString() {
		return "Paciente [cpf=" + cpf + ", nome=" + nome + ", dataNasc=" + dataNasc + ", convenio=" + convenio
				+ ", endereco=" + endereco + ", telefone=" + telefone + "]";
	}		
}
