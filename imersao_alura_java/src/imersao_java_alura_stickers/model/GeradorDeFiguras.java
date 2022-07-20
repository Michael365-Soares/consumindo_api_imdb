package imersao_java_alura_stickers.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GeradorDeFiguras {
    
	//private String url;
	//private Filme filme=App.filmesList.get(0);
	
	public void gerarFigura() throws IOException {
		//leitura da imagem
		BufferedImage imagemOriginal =ImageIO.read(new File("pasta_imagens/TopMovies_1.jpg"));
		//cria nova imagem em memória com transparência e com tamanho novo
		int largura=imagemOriginal.getWidth();
		int altura=imagemOriginal.getHeight();
		int novaAltura=altura+200;
		BufferedImage novaImagem=new BufferedImage(largura,novaAltura,BufferedImage.TRANSLUCENT);
		//copiar a imagem original para a nova imagem(em memória)
		Graphics2D grafico=(Graphics2D)novaImagem.getGraphics();
		grafico.drawImage ( imagemOriginal,0,0, null );
		//escrever uma frase na nova imagem
		//escrever a nova imagem em um arquivo
	ImageIO.write(novaImagem,"png",new File("saida/figurinha.png"));
	} 
	
	public static void main(String[] args) throws IOException {
		var gerador=new GeradorDeFiguras();
		gerador.gerarFigura();
	}
	
}
