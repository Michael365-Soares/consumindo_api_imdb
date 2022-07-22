package imersao_java_alura_stickers.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class ServicoHttp {
     private String url;
     private String corpoDaResposta;
     private HttpClient cliente=HttpClient.newHttpClient();
     
     public ServicoHttp() {}

	public ServicoHttp(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCorpoDaResposta() {
		return corpoDaResposta;
	}

	public void setCorpoDaResposta(String corpoDaResposta) {
		this.corpoDaResposta = corpoDaResposta;
	}

	public HttpClient getCliente() {
		return cliente;
	}
	
	public URI getUri() {
	   return URI.create(url);
	}
	
	public HttpRequest getRequest(URI uri) {
		return HttpRequest.newBuilder(uri).GET().build();
	}
     
}
