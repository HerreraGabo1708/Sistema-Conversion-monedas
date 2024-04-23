import java.net.http.HttpResponse;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class HttpResponseHandler {
    private Gson gson;

    public HttpResponseHandler() {
        // Instancia de Gson para trabajar con respuestas JSON
        this.gson = new Gson();
    }

    // Método para obtener el código de estado de la respuesta HTTP
    public int getStatusCode(HttpResponse<String> response) {
        return response.statusCode(); // Devolver el código de estado
    }

    // Método para obtener los encabezados de la respuesta
    public Map<String, java.util.List<String>> getHeaders(HttpResponse<String> response) {
        return response.headers().map(); // Retornar el mapa de encabezados
    }

    // Método para obtener el cuerpo de la respuesta como JsonObject
    public JsonObject getBodyAsJson(HttpResponse<String> response) throws Exception {
        if (response.statusCode() == 200) { // Verificar si el estado es exitoso
            return gson.fromJson(response.body(), JsonObject.class); // Deserializar cuerpo a JSON
        } else {
            throw new Exception("Error al obtener el cuerpo: " + response.statusCode());
        }
    }
}

