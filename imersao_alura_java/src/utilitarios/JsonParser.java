package utilitarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final Pattern REGEX_ITENS=Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON=Pattern.compile("\"(.+?)\":\"(.*?)\"");
    
    public List<Map<String,String>> parse(String json){
    	Matcher matcher=REGEX_ITENS.matcher(json);
    	if(!matcher.find()) {
    		throw new IllegalArgumentException("Json está vazio");
    	}
    	
    	String[] itens=matcher.group(1).split("\\},\\{");
    	
    	List<Map<String,String>> dados=new ArrayList<>();
    	
    	for(String item:itens) {
    		Map<String,String> atributoItem=new HashMap<>();
    		
    		Matcher matcherAtributoJson=REGEX_ATRIBUTOS_JSON.matcher(item);
    		
    		while(matcherAtributoJson.find()) {
    			String atributo=matcherAtributoJson.group(1);
    			String valor=matcherAtributoJson.group(2);
    			atributoItem.put(atributo, valor);
    		}
    		
    		dados.add(atributoItem);
    	}
    	return dados;	
    }
    
}
