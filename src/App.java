import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ExtratorConteudo extrator = new ExtratorConteudoIMDB();
        
        //String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var geradora = new GeradorDeFigurinhas();
        for (int i = 0; i < 3; i++ ) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImage()).openStream();
            String nameArquivo = conteudo.titulo().replaceAll(" ", "_") + ".png";

            geradora.cria(inputStream, nameArquivo);
            System.out.println(conteudo.titulo());
            System.out.println();

        }
    }
}
