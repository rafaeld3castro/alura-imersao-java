import java.io.InputStream;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        String apiKey = System.getenv("API_KEY");

        // fazer uma conexão HTTP e buscar os top 250 filmes
        var request = IMDbRequestBuilder.newBuilder(apiKey).forTop250Movies().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JacksonJsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        
        for (Map<String, String> filme : listaDeFilmes) {
            String titulo = filme.get("title");
            System.out.println(titulo);
            InputStream inputStream = new URL(filme.get("image")).openStream();
            geradora.cria(inputStream, titulo + ".png");
            System.out.println();
        }
    }
}
