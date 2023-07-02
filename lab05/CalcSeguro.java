package lab05;

public enum CalcSeguro {
	VALOR_BASE (10.),
    FATOR_30 (1.25),
    FATOR_30_60 (1.),
    FATOR_60 (1.5);

    private final double valor;

    CalcSeguro (double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }
}
