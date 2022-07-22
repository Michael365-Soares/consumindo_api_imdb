package imersao_java_alura_stickers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import imersao_java_alura_stickers.model.ConversoraJson;
import imersao_java_alura_stickers.model.Filme;
import imersao_java_alura_stickers.model.GeradorDeFiguras;
import imersao_java_alura_stickers.model.ServicoHttp;

public class App {
	//Lista responsável por armazenar os dados do tipo Json convertidos para objetos do tipo filme
	public static List<Filme> filmesList=new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException{
    	//Vriável responsável por armazenar o corpo da requisição vinda do request
    	String body;
    	//endereço da api responsável por trazer os dados
    	String enderecoApi="https://api.mocki.io/v2/549a5d8b";
    	//Criação de um objeto resposável por fornecer os serviços necessários para conexão com a api
    	ServicoHttp servicoHttp=new ServicoHttp(enderecoApi);
    	//Pegando um objeto HttpClient por meio do método getCliente() da classe ServivoHttp
    	HttpClient cliente=servicoHttp.getCliente();
    	/*Criando um objeto HttpRequest utilizando o método getRequest da classe ServicoHttp que recebe
    	 * como parâmetro uma URI fornecido pelo método getUri() também fornecido pela classe*/
    	HttpRequest request=servicoHttp.getRequest(servicoHttp.getUri());
    	/*Pegando a resposta da requisição por meio do objeto cliente chamando o método send()
    	 * e passando como parâmetros o objeto request e a forma que será retornada a resposta*/
    	HttpResponse<String> resposta=cliente.send(request,BodyHandlers.ofString());
		body=resposta.body();
    	//pegar somente os dados necessários(título,poster e classificação)
		//Instância de um ObjectMapper para manipulação de um Json
		ConversoraJson conversor=new ConversoraJson();
    	//Criando um TypeReference para extração de formato Json para um Collection do tipo Map
    	//Extraindo Json para a Collection Map
    	Map<Object,Object> filmes=conversor.getMapper().readValue(body,conversor.getMapa());
    	//Acessando o valor por meio da CHAVE=itens da colecao para acessar os valores em formato de Objetos Json e
    	// converte-los para uma String....
    	String listaArray=conversor.getMapper().writeValueAsString(filmes.get("items"));;
        //Convertendo a String listaArray em Objetos e adicionando a uma list
    	List<Object> listFilmes=conversor.getMapper().readValue(listaArray,new TypeReference<List<Object>>(){});
    	/*Criação de uma  lista para armazenar temporariamente objetos do tipo Object convertidos para String em
    	 * formato Json */
    	List<String> lista=new ArrayList<>();
    	for(Object objeto:listFilmes) {
    		//Convertendo um Object em String no formato Json
    		String json=conversor.getMapper().writerWithView(Object.class).writeValueAsString(objeto);
    		//Adicionando a String convertida a uma Lista
    		lista.add(json);
    	}
    	//Convertendo a String Json em Objetos do tipo filme
    	for(String json:lista) {
    		filmesList.add(conversor.getMapper().readerWithView(Object.class).forType(Filme.class).readValue(json));
    	}
    	//exibir e manipular os dados
    	GeradorDeFiguras gerador=new GeradorDeFiguras();
    	for(Filme filme:filmesList) {
    		System.out.println(filme);
    		String titulo=filme.getTitle().replace(':',' ').trim()+".png";
    		String url=filme.getImage();
    		InputStream stream=new URL(url).openStream();
    		gerador.gerarFigura(stream,titulo);
    	}
	}
}