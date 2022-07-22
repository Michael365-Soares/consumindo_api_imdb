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
	//Lista respons�vel por armazenar os dados do tipo Json convertidos para objetos do tipo filme
	public static List<Filme> filmesList=new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException{
    	//Vri�vel respons�vel por armazenar o corpo da requisi��o vinda do request
    	String body;
    	//endere�o da api respons�vel por trazer os dados
    	String enderecoApi="https://api.mocki.io/v2/549a5d8b";
    	//Cria��o de um objeto respos�vel por fornecer os servi�os necess�rios para conex�o com a api
    	ServicoHttp servicoHttp=new ServicoHttp(enderecoApi);
    	//Pegando um objeto HttpClient por meio do m�todo getCliente() da classe ServivoHttp
    	HttpClient cliente=servicoHttp.getCliente();
    	/*Criando um objeto HttpRequest utilizando o m�todo getRequest da classe ServicoHttp que recebe
    	 * como par�metro uma URI fornecido pelo m�todo getUri() tamb�m fornecido pela classe*/
    	HttpRequest request=servicoHttp.getRequest(servicoHttp.getUri());
    	/*Pegando a resposta da requisi��o por meio do objeto cliente chamando o m�todo send()
    	 * e passando como par�metros o objeto request e a forma que ser� retornada a resposta*/
    	HttpResponse<String> resposta=cliente.send(request,BodyHandlers.ofString());
		body=resposta.body();
    	//pegar somente os dados necess�rios(t�tulo,poster e classifica��o)
		//Inst�ncia de um ObjectMapper para manipula��o de um Json
		ConversoraJson conversor=new ConversoraJson();
    	//Criando um TypeReference para extra��o de formato Json para um Collection do tipo Map
    	//Extraindo Json para a Collection Map
    	Map<Object,Object> filmes=conversor.getMapper().readValue(body,conversor.getMapa());
    	//Acessando o valor por meio da CHAVE=itens da colecao para acessar os valores em formato de Objetos Json e
    	// converte-los para uma String....
    	String listaArray=conversor.getMapper().writeValueAsString(filmes.get("items"));;
        //Convertendo a String listaArray em Objetos e adicionando a uma list
    	List<Object> listFilmes=conversor.getMapper().readValue(listaArray,new TypeReference<List<Object>>(){});
    	/*Cria��o de uma  lista para armazenar temporariamente objetos do tipo Object convertidos para String em
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