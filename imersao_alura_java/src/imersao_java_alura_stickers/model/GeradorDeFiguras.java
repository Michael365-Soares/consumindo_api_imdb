package imersao_java_alura_stickers.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
public class GeradorDeFiguras {
    
	//private String url;
	//private Filme filme=App.filmesList.get(0);
	
	public void gerarFigura(InputStream arquivStream,String nomeArquivo) throws IOException {
		//leitura da imagem
		//InputStream stream;//new FileInputStream(new File("pasta_imagens/TopMovies_1.jpg"));
		//stream=new URL(arquivStream).openStream();
		BufferedImage imagemOriginal =ImageIO.read(arquivStream);
		//cria nova imagem em memória com transparência e com tamanho novo
		int largura=imagemOriginal.getWidth();
		int altura=imagemOriginal.getHeight();
		int novaAltura=altura-300;
		BufferedImage novaImagem=new BufferedImage(largura,novaAltura,BufferedImage.TRANSLUCENT);
		//copiar a imagem original para a nova imagem(em memória)
		Graphics2D grafico=(Graphics2D)novaImagem.getGraphics();
		grafico.drawImage ( imagemOriginal,0,0, null );
		//CONFIGURAR FONTE DO TEXTO DA IMAGEM
		Font fonte=new Font(Font.SANS_SERIF,Font.BOLD,42);
		grafico.setFont(fonte);
		grafico.setColor(Color.YELLOW);
		//escrever uma frase na nova imagem
		grafico.drawString("TOPZERA",0,400);
		//escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem,"png",new File("G:\\Code\\figuras/"+nomeArquivo));
	} 
}
