package imersao_java_alura_stickers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)	
public class ImagensDaNasa implements ExtracaoDeConteudo {
    private String title;
    private String url;
    private String date;
    private String explanation;
    private String hrurl;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getHrurl() {
		return hrurl;
	}
	public void setHrurl(String hrurl) {
		this.hrurl = hrurl;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Title:"+this.title+"\n");
		sb.append("Poster:"+this.url+"\n");
		return sb.toString();
	}

    
}
