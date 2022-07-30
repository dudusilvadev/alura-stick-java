import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    
    public void cria(InputStream inputStream, String nameArquivo) throws IOException {

        //leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BN2M4YTA4ZTEtN2EyNy00YTlmLWE4YzYtYjYyYjRkMWM4ZDM0XkEyXkFqcGdeQXVyMjUzOTY1NTc@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        //criar nova imagem
        int width = imagemOriginal.getWidth();
        int height = imagemOriginal.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);


        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar font
        Font font = new Font(Font.SANS_SERIF,Font.BOLD,width/12);
        graphics.setFont(font);
        graphics.setColor(Color.ORANGE);


        //escrever um texto
        graphics.drawString("SUAVE", width/3, newHeight - 25);
        graphics.drawString("CRIADO EM JAVA :)", width/11, newHeight - 100);

        File newDiretorio = new File("saida");
        //Files.createDirectories(Paths.get("saida"));
       /*  if (novoDiretorio.exists()) {
            System.out.println("Diretorio já existe no sistema");
        } else {
            novoDiretorio.mkdirs();            
        } */
        //se o diretorio nao existe é criado um com mkdir
         newDiretorio.mkdir();

        //ImageIO.write(newImage, "png", new File(novoDiretorio,"figurinha.png"));
        ImageIO.write(newImage, "png", new File(newDiretorio, nameArquivo));

        
    }
}
