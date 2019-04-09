package br.com.jogo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Arquivo {
	
	public void gerar(List<String> jogos, String caminhoArquivo) throws IOException{
		

		BufferedWriter bf = new BufferedWriter(new FileWriter(caminhoArquivo));

		for (String jogo: jogos) {
			bf.write(jogo);
			bf.write("\n");
		}
		
		bf.flush();  
		bf.close();
		
	}
}
