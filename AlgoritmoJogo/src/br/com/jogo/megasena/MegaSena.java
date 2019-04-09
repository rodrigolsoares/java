package br.com.jogo.megasena;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.jogo.Arquivo;
import br.com.jogo.Combinacao;
import br.com.jogo.VetorUtilities;

public class MegaSena {
	
	 public static void main(String[] args) throws IOException {
	    
		 System.out.println("Início");
			List<String> jogos = new ArrayList<String>();
			Long totalCombinacao = new Long(50);
			
			Integer[] vetor =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
		 	
			
			VetorUtilities vetorUtilities = new VetorUtilities();
	        Combinacao comb2 = new Combinacao(vetor, totalCombinacao);
	        
	        System.out.println("Gerando Jogos");
	        while ( comb2.hasNext() ) {
	        	
	        	Integer [] saida = comb2.next();
	        	Integer [] novoVetor = vetorUtilities.ordemCrescente(saida);
	            String valoresFormatados = "";
	            for ( Integer e : novoVetor ) {
	            	valoresFormatados += e + "," ;
	            }
	            
	            jogos.add(valoresFormatados);
	        }
	        
	        System.out.println("Gravando Jogos");
	        Arquivo arquivo = new Arquivo();
	        arquivo.gerar(jogos, "c:\\Jogos\\megaSena.txt");
	        
	        System.out.println("fim");
	        System.exit(0);
		 
	        
	
	 }
	 

}
