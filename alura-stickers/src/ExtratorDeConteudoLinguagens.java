import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExtratorDeConteudoLinguagens implements ExtratorDeConteudo {

    @Override
    public List<Conteudo> extrairConteudos(String json) {
        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JacksonJsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        // popular a lista de conteudos
        return listaDeAtributos.stream()
                .map(a -> {
                    String titulo = a.get("name");
                    String urlImagem = a.get("image");
                    return new Conteudo(titulo, urlImagem);
                })
                .collect(Collectors.toList());
    }
    
}
