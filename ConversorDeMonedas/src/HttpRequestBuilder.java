import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Map;

public class HttpRequestBuilder {
    // Método para construir una solicitud HTTP GET con encabezados personalizados
    public static HttpRequest buildGetRequest(String url, Map<String, String> headers) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET(); // Solicitud HTTP GET

        if (headers != null) {
            headers.forEach(builder::header); // Agregar encabezados personalizados
        }

        return builder.build(); // Retornar la solicitud construida
    }

    // Método para construir una solicitud HTTP POST con cuerpo JSON
    public static HttpRequest buildPostRequest(String url, String jsonBody, Map<String, String> headers) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json") // Establecer tipo de contenido
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody)); // Método POST con cuerpo

        if (headers != null) {
            headers.forEach(builder::header); // Agregar encabezados personalizados
        }

        return builder.build(); // Retornar la solicitud construida
    }
}
