import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {

    public String buscarDados(String url) {

        try {
            URI endereco = URI.create(url);
            var request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }
}
