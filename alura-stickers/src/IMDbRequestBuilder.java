import java.net.URI;
import java.net.http.HttpRequest;

public class IMDbRequestBuilder {

    private String apiKey;
    private String url = "https://imdb-api.com/en/API/Top250Movies";
    
    public static IMDbRequestBuilder newBuilder(String apiKey) {
        return new IMDbRequestBuilder(apiKey);
    }

    private IMDbRequestBuilder(String apiKey){
        this.apiKey = apiKey;
    }

    public IMDbRequestBuilder forTop250Movies() {
        // this.url = "https://imdb-api.com/en/API/Top250Movies + "/" + this.apiKey";
        this.url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        return this;
    }

    public IMDbRequestBuilder forMostPopularMovies() {
        this.url = "https://imdb-api.com/en/API/MostPopularMovies" + "/" + this.apiKey;
        return this;
    }

    public HttpRequest build() {
        URI endereco = URI.create(url);
        return HttpRequest.newBuilder(endereco).GET().build();
    }
}
