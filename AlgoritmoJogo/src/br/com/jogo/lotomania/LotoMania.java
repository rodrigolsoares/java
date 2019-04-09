package br.com.jogo.lotomania;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.jogo.Arquivo;
import br.com.jogo.Combinacao;
import br.com.jogo.VetorUtilities;

public class LotoMania {
	
	private static final Locale BRAZIL = new Locale("pt","BR");
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	private static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);
	
	private static String jogoSorteado = "2,3,5,6,7,9,10,14,15,16,18,19,20,21,25";
	
	private static Long totalCombinacao = new Long(19);
	private static Integer[] vetorFixos = {99};
	/*private static Integer[] vetor =  { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,
		                                37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,
		                                70,71, 72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98};*/
	
	private static Integer[] vetor =  { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,27,28,29,30};
	//Jogos de fora 1, 24, 8, 11, 13, 22
	
	public static void main(String[] args) throws IOException {
	    
		System.out.println("Início");
		List<String> jogos = new ArrayList<String>();
		
		System.out.println("Gerando Jogos");
		montaJogos(jogos);
		
		System.out.println("Gravando Jogos");
		geraJogosArquivos(jogos);
		
		System.out.println("Realizando conferencia");
        confereJogo(jogos, jogoSorteado);
        
		System.out.println("fim");
		System.exit(0);
	 
	 }

	private static void montaJogos(List<String> jogos) throws IOException {
		
		VetorUtilities vetorUtilities = new VetorUtilities();
        Combinacao comb2 = new Combinacao(vetor, totalCombinacao);
        
        while ( comb2.hasNext() ) {
        	
        	Integer [] saida = comb2.next();
        	Integer [] novoVetor = vetorUtilities.ordemCrescente(vetorUtilities.merge(vetorFixos, saida));
            String valoresFormatados = "";
            for ( Integer e : novoVetor ) {
            	valoresFormatados += e + "," ;
            }
            
            jogos.add(valoresFormatados);
        }
        
        
	}

	private static void geraJogosArquivos(List<String> jogos)throws IOException {
        Arquivo arquivo = new Arquivo();
        arquivo.gerar(jogos, "c:\\Jogos\\jogoLotoMania.txt");
	}

	private static void confereJogo(List<String> jogos, String jogoSorteado) {
		
        ConfereLotoMania confere = new ConfereLotoMania();
        confere.confere(jogoSorteado, jogos);
        
        System.out.println("Total de jogos: " + confere.getTotalJogos());
        System.out.println("Valor gasto no jogos: " + totalGastoJogo(confere));
        System.out.println("Total jogos 11 acertos: " + confere.getTotalAcertos11());
        System.out.println("Total jogos 12 acertos: " + confere.getTotalAcertos12());
        System.out.println("Total jogos 13 acertos: " + confere.getTotalAcertos13());
        System.out.println("Total jogos 14 acertos: " + confere.getTotalAcertos14());
        System.out.println("Total jogos 15 acertos: " + confere.getTotalAcertos15());
        
        System.out.println("Valor premio 11 acertos: " + totalPremio11(confere));
        System.out.println("Valor premio 12 acertos: " + totalPremio12(confere));
        System.out.println("Valor premio 13 acertos: " + totalPremio13(confere));
        System.out.println("Valor premio 14 acertos: " + totalPremio14(confere));
        System.out.println("Valor premio 15 acertos: " + totalPremio15(confere));
        System.out.println("Valor total premio: " + totalPremio(confere));
        System.out.println("Lucro com o premio: " + totalLucro(confere));
	}
	 
	 public static String totalGastoJogo(ConfereLotoMania confere){  
	     double valor = confere.getTotalJogos() * 1.5;   
		 return mascaraDinheiro(valor);  
	 } 
	 
	 public static String totalPremio11(ConfereLotoMania confere){  
	     double valor = confere.getTotalAcertos11() * 4;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio12(ConfereLotoMania confere){  
	     double valor = confere.getTotalAcertos12() * 8;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio13(ConfereLotoMania confere){  
	     double valor = confere.getTotalAcertos13() * 20;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio14(ConfereLotoMania confere){  
	     double valor = confere.getTotalAcertos14() * 1000;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio15(ConfereLotoMania confere){  
	     double valor = confere.getTotalAcertos15() * 100000;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio(ConfereLotoMania confere){  
	     double valor = (confere.getTotalAcertos15() * 100000)
	    		        + (confere.getTotalAcertos14() * 1000) 
	    		        + (confere.getTotalAcertos13() * 20) 
	    		        + (confere.getTotalAcertos12() * 8) 
	    		        + (confere.getTotalAcertos11() * 4);   
		 return mascaraDinheiro(valor);  
	 }
	 
	 
	 public static String totalLucro(ConfereLotoMania confere){  

	     double valor = (confere.getTotalAcertos15() * 100000)
 		        + (confere.getTotalAcertos14() * 1000) 
 		        + (confere.getTotalAcertos13() * 20) 
 		        + (confere.getTotalAcertos12() * 8) 
 		        + (confere.getTotalAcertos11() * 4)
 		         - (confere.getTotalJogos() * 2);
	     
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String mascaraDinheiro(double valor){  
	        return DINHEIRO_REAL.format(valor);  
	 }  
	 

}
