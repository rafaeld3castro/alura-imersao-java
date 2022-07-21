import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo, String texto) throws Exception {

        // leitura de imagem
        // InputStream inputStream = new
        // URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + (int) (altura * 0.2);
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        configurarFonte(graphics, texto, altura, largura, novaAltura - altura);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

    /**
     * Faz as configurações da fonte do texto
     * @param g
     * @param texto
     * @param textIni
     * @param largura
     * @param altura
     */
    private void configurarFonte(Graphics2D g, String texto, int yTextIni, int largura, int altura) {
        
        // configurações da fonte
        int fontSize = (int) (largura * 0.15);
        Font fonte = new Font("Impact", Font.PLAIN, fontSize);
        var corFonte = new Color(111, 1, 133);
        
        // definições para centralizar o texto
        FontMetrics metrics = g.getFontMetrics(fonte);
        int xFont = 0 + (largura - metrics.stringWidth(texto)) / 2;
        int yFont = yTextIni + ((altura - metrics.getHeight()) / 2) + metrics.getAscent();
        
        g.setFont(fonte);
        g.setColor(corFonte);

        // escrever uma frase na nova imagem
        g.drawString(texto, xFont, yFont);
    }
}
