package conversor.util;

import java.util.Map;

public class MoedaUtils {
    public static final Map<Integer, String> MOEDAS = Map.ofEntries(
            Map.entry(1, "ARS"),
            Map.entry(2, "AUD"),
            Map.entry(3, "BOB"),
            Map.entry(4, "BRL"),
            Map.entry(5, "CAD"),
            Map.entry(6, "CHF"),
            Map.entry(7, "CLP"),
            Map.entry(8, "CNY"),
            Map.entry(9, "COP"),
            Map.entry(10, "EUR"),
            Map.entry(11, "GBP"),
            Map.entry(12, "JPY"),
            Map.entry(13, "USD")
    );

    public static final Map<String, String> NOMES_MOEDAS = Map.ofEntries(
            Map.entry("ARS", "Peso Argentino"),
            Map.entry("AUD", "Dólar Australiano"),
            Map.entry("BOB", "Boliviano Boliviano"),
            Map.entry("BRL", "Real Brasileiro"),
            Map.entry("CAD", "Dólar Canadense"),
            Map.entry("CHF", "Franco Suíço"),
            Map.entry("CLP", "Peso Chileno"),
            Map.entry("CNY", "Yuan Chinês"),
            Map.entry("COP", "Peso Colombiano"),
            Map.entry("EUR", "Euro"),
            Map.entry("GBP", "Libra Esterlina"),
            Map.entry("JPY", "Iene Japonês"),
            Map.entry("USD", "Dólar Americano")
    );

    public static void exibirMenu() {
        MOEDAS.forEach((k, v) -> {
            String nome = NOMES_MOEDAS.getOrDefault(v, "");
            System.out.println(k + " - " + v + " (" + nome + ")");
        });
    }
}
