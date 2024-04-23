import java.net.http.HttpRequest;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class Logica {
    private HttpCliente httpCliente;
    private HttpRequestBuilder requestBuilder;
    private HttpResponseHandler responseHandler;
    private Map<String, Double> exchangeRates; // Almacena las tasas de cambio obtenidas

    public Logica() {
        this.httpCliente = new HttpCliente();
        this.requestBuilder = new HttpRequestBuilder();
        this.responseHandler = new HttpResponseHandler();
        this.exchangeRates = new HashMap<>(); // Mapa para tasas de cambio
    }

    // Método para obtener tasas de cambio de la API
    public void fetchExchangeRates(String apiUrl) throws Exception {
        HttpRequest request = requestBuilder.buildGetRequest(apiUrl, null);
        JsonObject responseJson = httpCliente.send(request); // Enviar la solicitud y obtener respuesta
        JsonObject rates = responseJson.getAsJsonObject("rates"); // Obtener el objeto de tasas

        // Guardar las tasas de cambio en el mapa
        for (Map.Entry<String, com.google.gson.JsonElement> entry : rates.entrySet()) {
            this.exchangeRates.put(entry.getKey(), entry.getValue().getAsDouble());
        }
    }

    // Método para convertir una cantidad de una moneda a otra
    public double convert(String fromCurrency, String toCurrency, double amount) throws Exception {
        // Asegurarse de que las tasas están disponibles para las monedas requeridas
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new Exception("Las tasas de cambio para las monedas especificadas no están disponibles.");
        }

        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);

        // Convertir la cantidad a una tasa base (por ejemplo, a USD) y luego a la moneda deseada
        double baseAmount = amount / fromRate; // Convertir a base
        double convertedAmount = baseAmount * toRate; // Convertir a la moneda deseada

        return convertedAmount; // Retornar la cantidad convertida
    }
}

