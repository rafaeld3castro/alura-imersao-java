import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo, String avaliacao) throws Exception {

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
        String texto = "TOPZERA " + avaliacao + "/10";
        configurarFonte(graphics, texto, altura, largura, novaAltura - altura);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
    }

    /**
     * Faz as configurações da fonte do texto
     * @param g
     * @param texto
     * @param textIni
     * @param largura
     * @param altura
     */
    private void configurarFonte(Graphics2D g, String texto, int textIni, int largura, int altura) {
        
        // configurações da fonte
        int fontSize = (int) (largura * 0.15);
        Font fonte = new Font("Impact", Font.PLAIN, fontSize);
        var corFonte = new Color(111, 1, 133);
        
        // definições para centralizar o texto
        FontMetrics metrics = g.getFontMetrics(fonte);
        int xFont = 0 + (largura - metrics.stringWidth(texto)) / 2;
        int yFont = textIni + ((altura - metrics.getHeight()) / 2) + metrics.getAscent();
        
        g.setFont(fonte);
        g.setColor(corFonte);

        // escrever uma frase na nova imagem
        g.drawString(texto, xFont, yFont);

        // Não ficou legal o outline do texto =(
        /*FontRenderContext frc = g.getFontRenderContext();
        GlyphVector gv = fonte.createGlyphVector(frc, texto);
        Shape shape = gv.getOutline(xFont + 0.5f, yFont + 0.5f);
        g.setClip(shape);
        g.setStroke(new BasicStroke(fontSize * 0.05f));
        g.setColor(Color.BLACK);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.draw(shape);

        g.dispose();*/
    }
}
