package imersao_java_alura_stickers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import imersao_java_alura_stickers.model.ConversoraJson;
import imersao_java_alura_stickers.model.Filme;
import imersao_java_alura_stickers.model.GeradorDeFiguras;
import imersao_java_alura_stickers.model.ImagensDaNasa;
import imersao_java_alura_stickers.model.ServicoHttp;

public class App {
	//Lista responsável por armazenar os dados do tipo Json convertidos para objetos do tipo filme
	public static List<Filme> filmesList=new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException{
    	
    	String bodyImdb,bodyApiNasa;
    	//endereço da api responsável por trazer os dados
    	String enderecoApiImdb="https://api.mocki.io/v2/549a5d8b";
    	String enderecoApiNasa="https://api.nasa.gov/planetary/apod?api_key=X1497OMAVPQvRsjH8qsmVLlLYfaLJUU0VPQdm2Ph";
    	bodyImdb=new ServicoHttp(enderecoApiImdb).retornaConteudoJson();
    	bodyApiNasa=new ServicoHttp(enderecoApiNasa).retornaConteudoJson();

		ConversoraJson conversorJsonImdb=new ConversoraJson(bodyImdb);
		ConversoraJson conversorJsonNasa=new ConversoraJson(bodyApiNasa);
		
        filmesList=conversorJsonImdb.retornaConteudoImdb();
        ImagensDaNasa nasa=conversorJsonNasa.retornaConteudoNasa();
        
    	//exibir e manipular os dados
    	GeradorDeFiguras gerador=new GeradorDeFiguras();
    	for(Filme filme:filmesList) {
    		System.out.println(filme);
    		String titulo=filme.getTitle().replace(':',' ').trim()+".png";
    		String image=filme.getImage();
    		InputStream stream=new URL(image).openStream();
    		gerador.gerarFigura(stream,titulo);
    	}
        
        GeradorDeFiguras gerador1=new GeradorDeFiguras();
    		System.out.println(nasa);
    		String titulo=nasa.getTitle().replace(':',' ').trim()+".png";
    		String image=nasa.getUrl();
    		InputStream stream=new URL(image).openStream();
    		gerador1.gerarFigura(stream,titulo);
        
	}
    
}