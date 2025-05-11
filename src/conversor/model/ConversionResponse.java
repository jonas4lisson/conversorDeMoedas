package conversor.model;

public class ConversionResponse {
    private final String moedaOrigem;
    private final String moedaDestino;
    private final double valor;
    private final double resultado;

    public ConversionResponse(String moedaOrigem, String moedaDestino, double valor, double resultado) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valor = valor;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return String.format("\n%.2f %s equivalem a %.2f %s\n", valor, moedaOrigem, resultado, moedaDestino);
    }
}
