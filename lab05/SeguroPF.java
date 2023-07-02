package lab05;
import java.util.*;

public class SeguroPF extends Seguro{
	private Veiculo veiculo;
	private ClientePF cliente;
	
	// Construtora
	public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
        calcularValor();
    }
	
	// Getters e setters
	public Veiculo getVeiculo() {
        return veiculo;
    }
	
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public ClientePF getCliente() {
        return cliente;
    }
    
    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }
	
    // Adiciona o condutor à listaCondutores
    public boolean autorizarCondutor(Condutor condutor) {
        boolean autorizou = super.autorizarCondutor(condutor);

        if (autorizou) {
            calcularValor();
        }

        return autorizou;
    }
    
    // Remove o condutor da listaCondutores
    public boolean desautorizarCondutor(Condutor condutor) {
        boolean desautorizou = super.desautorizarCondutor(condutor);

        if (desautorizou) {
            calcularValor();
        }

        return desautorizou;
    }
    
    // Gera um sinistro e adiciona à listaSinistros
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        boolean gerou = super.gerarSinistro(data, condutor, endereco);

        if (gerou) {
            calcularValor();
        }

        return gerou;
    }
    
    // Calcula o valor do seguro
    public void calcularValor() {
        if (getSeguradora() == null || cliente == null) {
            return;
        }

        int idade = Data.calcularIdade(cliente.getDataNasc());
        double fatorIdade;

        if (idade < 30) {
            fatorIdade = CalcSeguro.FATOR_30.getValor();
        } else if (idade <= 60) {
            fatorIdade = CalcSeguro.FATOR_30_60.getValor();
        } else {
            fatorIdade = CalcSeguro.FATOR_60.getValor();
        }

        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        int quantidadeVeiculos = getSeguradora().getVeiculosPorCliente(cliente).size();
        int quantidadeSinistrosCliente = getSeguradora().getSinistrosPorCliente(cliente).size();
        int quantidadeSinistrosCondutor = quantidadeSinistrosPorCondutor();

        double valor = valorBase * fatorIdade *
                (1 + 1. / (quantidadeVeiculos + 2)) *
                (2 + quantidadeSinistrosCliente / 10.) *
                (5 + quantidadeSinistrosCondutor / 10.);

        setValorMensal(valor);
    }
    
    // Remove um sinistro da listaSinistros
    public boolean removerSinistro(Sinistro sinistro) {
        boolean removeu = super.removerSinistro(sinistro);
        if (removeu) {
            calcularValor();
        }
        return removeu;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString().replaceFirst("Seguro", "SeguroPF"));
        sb.append("\n- Veiculo: ").append(veiculo.getPlaca()).append("\n- Cliente: ").append(cliente.strDocumento());
        return sb.toString();
    }
}
