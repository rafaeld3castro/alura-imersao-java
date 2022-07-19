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
        this.url = "https://imdb-api.com/en/API/Top250Movies";
        return this;
    }

    public IMDbRequestBuilder forMostPopularMovies() {
        this.url = "https://imdb-api.com/en/API/MostPopularMovies";
        return this;
    }

    public HttpRequest build() {
        String fullUrl = this.url + "/" + this.apiKey;
        URI endereco = URI.create(fullUrl);
        return HttpRequest.newBuilder(endereco).GET().build();
    }
}
