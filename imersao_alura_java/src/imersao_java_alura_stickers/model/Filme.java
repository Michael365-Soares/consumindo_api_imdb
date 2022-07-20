package imersao_java_alura_stickers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)	 	 	 
public class Filme {
	
    private String title;
    private String image;
    private String imDbRating;
    
    public Filme() {}

	public Filme(String title, String image, String imDbRating) {
		super();
		this.title = title;
		this.image = image;
		this.imDbRating = imDbRating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImDbRating() {
		return imDbRating;
	}

	public void setImDbRating(String imDbRating) {
		this.imDbRating = imDbRating;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Title:"+this.title+"\n");
		sb.append("Poster:"+this.image+"\n");
		sb.append("Nota:"+this.imDbRating+"\n");
		return sb.toString();
	}

	

}
