package imersao_java_alura_stickers;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import imersao_java_alura_stickers.model.Filme;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException{
        String body;
		//imdb-api.com/en/API/Top250Movies/K_0ojt0yvm
    	//realizar uma conexão http e buscar os top 250 filmes imdb
    	String enderecoApi="https://api.mocki.io/v2/549a5d8b";
    	//http://www.omdbapi.com/?t=Game%20of%20Thrones&Season=1&Episode=1
    	URI createURI=URI.create(enderecoApi);
    	HttpClient cliente=HttpClient.newHttpClient();
    	HttpRequest request=HttpRequest.newBuilder(createURI).GET().build();
    	
		HttpResponse<String> resposta=cliente.send(request,BodyHandlers.ofString());
		body=resposta.body();
	    //System.out.println(body);
			
    	//pegar somente os dados necessários(título,poster e classificação)
		//Instância de um ObjectMapper para manipulação de um Json
    	ObjectMapper obj=new ObjectMapper();
    	//Criando um TypeReference para extração de formato Json para um Collection do tipo Map
    	TypeReference<Map<Object,Object>> typeRef=new TypeReference<>() {};
    	//Extraindo Json para a Collection Map
    	Map<Object,Object> filmes=obj.readValue(body,typeRef);
    	//System.out.println(filmes.get("items"));
    	//Acessando o valor por meio da CHAVE=itens da colecao para acessar os valores em formato de Objetos Json e
    	// converte-los para uma String....
    	String listaArray=obj.writeValueAsString(filmes.get("items"));
        //Convertendo a String listaArray em Objetos e adicionando a uma list
    	List<Object> listFilmes=obj.readValue(listaArray,new TypeReference<List<Object>>() {});
    	List<String> lista=new ArrayList<>();
    	for(Object objeto:listFilmes) {
    		String json=obj.writerWithView(Object.class).writeValueAsString(objeto);
    		lista.add(json);
    	}
    	List<Filme> filmesList=new ArrayList<>();
    	for(String json:lista) {
    		filmesList.add(obj.readerWithView(Object.class).forType(Filme.class).readValue(json));
    	}
    	//exibir e manipular os dados
    	filmesList.forEach(System.out::println);
	}
}