package br.com.jogo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MontaJogosPrimarios {
	
	
	private static Long totalCombinacao = new Long(17);
	//private static Integer[] vetor =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
	//private static Integer[] vetor =  {6,	11,	9,	5,	3,	17,	13,	23,	25,	10};
	
	//private static Integer[] vetor =  {10,	13,	25,	6,	17,	9,	18,	5,	4,	21,	3,	11,	7,	24};

	private static Integer[] vetor =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
	
	public static void main(String[] args) throws IOException {
	    
		System.out.println("Início");
		List<String> jogos = new ArrayList<String>();
		
		System.out.println("Gerando Jogos");
		montaJogos(jogos);
		
		System.out.println("Gravando Jogos");
		geraJogosArquivos(jogos);
		
		System.out.println("fim");
		System.exit(0);
	 
	 }

	private static void montaJogos(List<String> jogos)
			throws IOException {
		
		VetorUtilities vetorUtilities = new VetorUtilities();
        Combinacao comb2 = new Combinacao(vetor, totalCombinacao);
        
        while ( comb2.hasNext() ) {
        	
        	Integer [] saida = comb2.next();
        	Integer [] novoVetor = vetorUtilities.ordemCrescente(saida);
            String valoresFormatados = "";
            for ( Integer e : novoVetor ) {
            	valoresFormatados += e + "," ;
            }
            
            jogos.add(valoresFormatados);
        }
        
        
	}

	private static void geraJogosArquivos(List<String> jogos)throws IOException {
        Arquivo arquivo = new Arquivo();
        arquivo.gerar(jogos, "c:\\Jogos\\jogosPrimariosLotoFacil.txt");
	}

}
