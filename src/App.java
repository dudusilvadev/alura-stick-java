import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> respons = client.send(request, BodyHandlers.ofString());
        String body = respons.body();
        //System.out.println(body);
        JsonParser parser = new JsonParser();

        List<Map<String, String>> conteudo = parser.parse(body);
        
        GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
        for (Map<String,String> filme : conteudo) {
           
            String urlImage = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nameArquivo = titulo.replace(" ", "_") + ".png";

            geradora.cria(inputStream, nameArquivo);
            System.out.println(titulo);
            System.out.println();

        }
        



    }
}
