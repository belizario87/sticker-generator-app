import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-16";
        var http = new ClienteHttp();
        String json = http.buscadados(url);

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(json);

        // criar o diretorio para armazenar as figurinhas
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        // exibir e manipular os dados
        var generate = new StickGenerator();
        for (Map<String, String> conteudo : listaDeConteudos) {

            String urlImagem =
                    conteudo.get("url")
                            .replaceAll("(@+)(.*).jpg$", "s1.jpg");

            String titulo = conteudo.get("title");
           // double classificacao = Double.parseDouble(filme.get("imDbRating"));

            String textoFigurinha = "TOPZERA";
//            if (classificacao >= 9.0) {

//            } else {
//                textoFigurinha = "HMMMMM...";
//            }

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo =  "figurinhas/" + titulo + ".png";

            generate.generateSticker(inputStream, nomeArquivo, textoFigurinha);

            System.out.println(titulo);
            System.out.println();
        }
    }
}