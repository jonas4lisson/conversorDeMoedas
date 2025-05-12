package conversor.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import conversor.model.ConversionResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ExchangeRateService {
    private static final String API_KEY = Config.get("API_KEY");
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public ConversionResponse converter(String moedaOrigem, String moedaDestino, double valor) throws Exception {
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + moedaOrigem;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na conexão. Código: " + response.statusCode());
        }

        JsonObject json = gson.fromJson(response.body(), JsonObject.class);

        if (!json.get("result").getAsString().equals("success")) {
            throw new RuntimeException("Erro da API: " + json.get("error-type").getAsString());
        }

        JsonObject taxas = json.getAsJsonObject("conversion_rates");
        double taxa = taxas.get(moedaDestino).getAsDouble();
        double resultado = valor * taxa;

        return new ConversionResponse(moedaOrigem, moedaDestino, valor, resultado);
    }
}
