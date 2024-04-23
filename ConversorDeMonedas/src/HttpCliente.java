import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class HttpCliente {
    private HttpClient client;
    private Gson gson;

    public HttpCliente() {
        // Configurar HttpClient con tiempo de espera personalizado
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)) // Tiempo de espera de conexión
                .build();

        // Instancia de Gson para deserializar respuestas JSON
        this.gson = new Gson();
    }

    // Método para enviar solicitudes HTTP y obtener respuesta como JsonObject
    public JsonObject send(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) { // Verificar si la respuesta es exitosa
            return gson.fromJson(response.body(), JsonObject.class); // Deserializar respuesta a JSON
        } else {
            throw new Exception("Error en la solicitud HTTP: " + response.statusCode());
        }
    }
}

