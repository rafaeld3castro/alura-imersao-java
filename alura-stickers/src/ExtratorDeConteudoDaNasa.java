import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

    @Override
    public List<Conteudo> extrairConteudos(String json) {

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new RegexJsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        // popular a lista de conteudos
        return listaDeAtributos.stream()
                .map(atributos -> {
                    String titulo = atributos.get("title");
                    String urlImagem = atributos.get("url");
                    return new Conteudo(titulo, urlImagem);
                })
                .collect(Collectors.toList());
    }
}
