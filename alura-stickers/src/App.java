import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // String apiKey = System.getenv("API_KEY");

        ExtratorEnum extratorEnum = ExtratorEnum.NASA;
        String url = extratorEnum.getUrl();
        ExtratorDeConteudo extrator = extratorEnum.getExtrator();

        String json = new ClientHttp().buscadados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem())
                    .openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo()
                    .replace(": ", " - ") + ".png";
            String texto = "TOPZERA 10/10";
            
            geradora.cria(inputStream, nomeArquivo, texto);

            System.out.println(conteudo.getTitulo());
        }
    }
}
