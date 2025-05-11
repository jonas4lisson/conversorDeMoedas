import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conversor.api.ExchangeRateService;

import conversor.model.ConversionResponse;
import conversor.util.MoedaUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ExchangeRateService service = new ExchangeRateService();

        // Lista para armazenar as conversões
        List<ConversionResponse> conversoes = new ArrayList<>();

        while (true) {
            int origem = 0, destino = 0;
            double valor = -1;

            // Loop para escolher a moeda de origem
            while (!MoedaUtils.MOEDAS.containsKey(origem)) {
                System.out.println("Escolha a moeda de ORIGEM:");
                MoedaUtils.exibirMenu();
                System.out.print("Número: ");
                origem = leitura.hasNextInt() ? leitura.nextInt() : 0;
                leitura.nextLine(); // limpar buffer
            }

            // Loop para escolher a moeda de destino, garantindo que não seja a mesma moeda de origem
            while (!MoedaUtils.MOEDAS.containsKey(destino) || destino == origem) {
                System.out.println("Escolha a moeda de DESTINO:");
                MoedaUtils.exibirMenu();
                System.out.print("Número: ");
                destino = leitura.hasNextInt() ? leitura.nextInt() : 0;
                leitura.nextLine();
            }

            String moedaOrigem = MoedaUtils.MOEDAS.get(origem);
            String moedaDestino = MoedaUtils.MOEDAS.get(destino);

            // Loop para garantir que o valor inserido seja positivo
            while (valor < 0) {
                System.out.printf("Digite o valor em %s: ", moedaOrigem);
                valor = leitura.hasNextDouble() ? leitura.nextDouble() : -1;
                leitura.nextLine();
            }

            try {
                ConversionResponse conversao = service.converter(moedaOrigem, moedaDestino, valor);
                conversoes.add(conversao); // Armazenando a conversão na lista
                System.out.println(conversao);
            } catch (Exception e) {
                System.out.println("Erro ao converter moeda: " + e.getMessage());
            }

            System.out.print("Deseja converter outra moeda? (s/n): ");
            String cont = leitura.nextLine();
            if (!cont.equalsIgnoreCase("s")) break;
        }

        // Exibir no console
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // ✅ Agora com identação
        String jsonOutput = gson.toJson(conversoes);
        System.out.println("\nConversões realizadas:");
        System.out.println(jsonOutput);

        // Salvar em um arquivo JSON
        try {
            Files.write(Paths.get("conversoes.json"), jsonOutput.getBytes());
            System.out.println("Conversões salvas no arquivo 'conversoes.json'.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }

        System.out.println("Encerrando o conversor.");
    }
}