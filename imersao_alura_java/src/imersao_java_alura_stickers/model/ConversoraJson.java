package imersao_java_alura_stickers.model;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversoraJson {
    private ObjectMapper mapper=new ObjectMapper();
    private String json;
    
    public ConversoraJson() {}
    
	public ConversoraJson(String json) {
		super();
		this.json = json;
	}
    
    public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public TypeReference<Map<Object,Object>> getMapa(){
    	return new TypeReference<Map<Object,Object>>() {};
    }
    
}
