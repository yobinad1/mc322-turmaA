package lab04;

import java.util.*;

public class ClientePF extends Cliente {
	private String genero;
	private final String CPF;
	private String educacao;
	private String classeEconomica;
	private Date dataLicenca;
	private Date dataNascimento;
	
	// Construtora
	public ClientePF(String nome, String endereco, String genero, String CPF, String educacao, String classeEconomica, Date dataLicenca, Date dataNascimento, double valorSeguro) {
		// Herdeira da classe mãe Cliente
		super(nome, endereco, valorSeguro);
		this.genero = genero;
		this.CPF = CPF;
		this.educacao = educacao;
		this.classeEconomica = classeEconomica;	
		this.dataLicenca = dataLicenca;
		this.dataNascimento = dataNascimento;
	}
	
	// Getters e Setters
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public String getEducacao() {
		return educacao;
	}
	
	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}
	
	public String getClasseEconomica() {
		return classeEconomica;
	}
	
	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	public Date getDataLicenca() {
		return dataLicenca;
	}
	
	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	// Método que calcula a idade
	public int calcAge() {
		Date dataAtual = new Date();
		Date dataNascimento = this.getDataNascimento();
		int age = dataAtual.getYear() - dataNascimento.getYear();
		
		if (dataAtual.getMonth() == dataNascimento.getMonth() && dataAtual.getDate() < dataNascimento.getDate() ||
			dataAtual.getMonth() < dataNascimento.getMonth()) {
			age -= 1;
		}
		return age;
	}
	
	// Método que calcula o score
	public double calculaScore() {
		double score, age_factor;
		int age = calcAge();
		if (age >= 18 & age < 30) {
			age_factor = CalcSeguro.FATOR_18_30.getFator();
		}
		if (age >=30 & age < 60) {
			age_factor = CalcSeguro.FATOR_30_60.getFator();
		}
		if (age >=60 & age < 90) {
			age_factor = CalcSeguro.FATOR_60_90.getFator();
		}
		score = CalcSeguro.VALOR_BASE.getFator() * age_factor * this.getListaVeiculos().size();
		return score;
	}

	public String toString() {
		return "ClientePF [genero=" + genero + ", CPF=" + CPF + ", educacao=" + educacao + ", classeEconomica=" + classeEconomica + ", dataLicenca=" + dataLicenca + ", dataNascimento=" + dataNascimento + ", Veiculos=" + imprimeVeiculos()"]"; 
	}
}
