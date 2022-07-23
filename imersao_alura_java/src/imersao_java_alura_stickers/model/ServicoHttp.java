package imersao_java_alura_stickers.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ServicoHttp {
    private String url;
     
	public ServicoHttp(String url) {
		super();
		this.url = url;
	}

	public String retornaConteudoJson() {
		HttpClient cliente=HttpClient.newHttpClient();
	    URI uri=URI.create(url);
		HttpRequest requisicao=HttpRequest.newBuilder(uri).GET().build();
		try {
			 HttpResponse<String> json=cliente.send(requisicao,BodyHandlers.ofString());
			 return json.body();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
     
}
