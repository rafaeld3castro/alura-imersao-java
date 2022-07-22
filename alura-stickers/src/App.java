import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // String apiKey = System.getenv("API_KEY");

        ExtratorEnum extratorEnum = ExtratorEnum.LINGUAGENS;

        String json = new ClientHttp().buscarDados(extratorEnum.url());
        
        // exibir e manipular os dados
        List<Conteudo> conteudos = extratorEnum.extrator().extrairConteudos(json);

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        Path saida = Paths.get("saida");
        Files.createDirectories(saida);
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
