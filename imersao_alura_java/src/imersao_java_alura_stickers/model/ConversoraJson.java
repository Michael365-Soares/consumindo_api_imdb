package imersao_java_alura_stickers.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversoraJson {
    private String json;
    
    public ConversoraJson(String corpoDaResposta) {
    	this.json=corpoDaResposta;
    }

     public <T extends ExtracaoDeConteudo> List<T> retornaConteudoImdb() throws JsonParseException,
     JsonMappingException, IOException{
    	 ObjectMapper mapper=new ObjectMapper();
    	 TypeReference<Map<Object,Object>> mapa=new TypeReference<>() {};
    	 Map<Object,Object> conteudoDoJson=mapper.readValue(json,mapa);
    	 String conteudoEmString;
		 conteudoEmString=mapper.writeValueAsString(conteudoDoJson.get("items"));
		 List<Object> listaDeObjeto=mapper.readValue(conteudoEmString,new TypeReference<List<Object>>() {});
		 List<String> listaDeString=new ArrayList<>();
		 for(Object obj:listaDeObjeto) {
			 String json=mapper.writerWithView(Object.class).writeValueAsString(obj);
			 listaDeString.add(json);
		 }
		 List<T> filmes=new ArrayList<>();
		 for(String jsonString:listaDeString) {
			 filmes.add(mapper.readerWithView(Object.class).forType(Filme.class).readValue(jsonString));
		 }
		 return filmes;
     }
     
     public ImagensDaNasa retornaConteudoNasa() throws JsonParseException, 
     JsonMappingException, IOException{
    	 ObjectMapper mapper=new ObjectMapper();
    	 String conteudoEmString;
    	 conteudoEmString=mapper.writeValueAsString(json);
		 String objeto=mapper.readValue(conteudoEmString,new TypeReference<String>() {});
		 ImagensDaNasa nasa=(mapper.readerWithView(Object.class).forType(ImagensDaNasa.class).readValue(objeto));
		 return nasa;
     }
    
}
