import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickGenerator {

    public void generateSticker(InputStream inputStream, String fileName, String textoFigurinha) throws Exception {
        //leitura da imagem
        //InputStream inputStream = new FileInputStream("assets/filme_1.jpg");
//        InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();

         BufferedImage imagemOriginal = ImageIO.read(inputStream);
        //criar nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //formatar o texto escrito
        var fonte = new Font("Impact", Font.BOLD, 100);
        graphics.setFont(fonte);
        graphics.setColor(Color.ORANGE);



        //escrever uma frase na imagem
        String texto = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics );
        int largurTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - largurTexto) / 2;
        int posicaoTextoY = novaAltura - 80;



        graphics.drawString(texto,posicaoTextoX, posicaoTextoY );

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(texto, fonte, fontRenderContext);
        Shape outline = textLayout.getOutline(null);

        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.black);
        graphics.draw(outline);
        graphics.setClip(outline);
        //escrever a nova imagem em um arquivo

        ImageIO.write(novaImagem, "png", new File(fileName));



    }


}
