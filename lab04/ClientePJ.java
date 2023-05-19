package lab04;

import java.util.*;

public class ClientePJ extends Cliente {
	private final String CNPJ;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	
	// Construtora
	public ClientePJ(String nome, String endereco, String CNPJ, Date dataFundacao, int qtdeFuncionarios, double valorSeguro) {
		// Herdeira da classe mãe Cliente
		super(nome, endereco, valorSeguro);
		this.CNPJ = CNPJ;
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	// Getters e Setters
	public String getCNPJ() {
		return CNPJ;
	}
	
	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}
	
	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	public double calculaScore() {
		double score;
		score = CalcSeguro.VALOR_BASE * (1 + (qtdeFuncionarios) / 100) * this.getListaVeiculos().size();
		return score;
	}
	
	public String toString() {
		return "ClientePJ [CNPJ=" + CNPJ + ", dataFundacao=" + dataFundacao + ", Veiculos=" + imprimeVeiculos() + ", Valor Seguro=" + getValorSeguro() + "]"; 
	}
}