package br.com.jogo.lotofacil;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import br.com.jogo.Arquivo;
import br.com.jogo.Combinacao;
import br.com.jogo.RegraPares;
import br.com.jogo.RemoveSequencial;
import br.com.jogo.VetorUtilities;

public class LotoFacil {
	
	private static final Locale BRAZIL = new Locale("pt","BR");
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	private static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);
	
	private static final String jogoSorteado = "6, 8, 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 23, 24, 25";
	
	private static final Long totalCombinacao = new Long(3);
	
	private static final int maximoSequencial = 7;
	
	private static final int maximoPares = 8;
	
	
	private static final int qtdeJogos = 0;
	
	private static final Integer[] vetorFixos = {1,	3,	5, 24,	22,	20, 2, 4, 6, 23, 21, 19}; 
	private static final Integer[] vetor =  { 7, 9,	11, 18,	16,	14, 8, 10, 12, 17, 15, 13, 25}; 
	
	
	//private static final Integer[] vetorFixos = {};
	//private static final Integer[] vetor =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }; 
	
	public static void main(String[] args) throws IOException {
	    
		System.out.println("Início");
		List<String> jogos = new ArrayList<String>();
		
		System.out.println("Gerando Jogos");
		montaJogos(jogos);
		
		System.out.println("Filtro sequenciais");
		List<String> jogosFiltrado = filtraNumerosSequenciais(jogos);
		
		System.out.println("Filtro maximo números pares");
		List<String> jogosParesFiltrados = filtraNumerosPares(jogosFiltrado);
		
		
		
		System.out.println("Embaralhando jogo");
		for(int i = 0; i <= 100; i++){
			Collections.sort(jogosParesFiltrados);
		}
		
		System.out.println("Limitando quantidade de jogos");
		List<String> jogosResumido = addJogosResumidos(jogosParesFiltrados);
		
		System.out.println("Gravando Jogos");
		geraJogosArquivos(jogosResumido);
		
		System.out.println("Realizando conferencia");
        confereJogo(jogosResumido, jogoSorteado);
        
		System.out.println("fim");
		System.exit(0);
	 
	 }

	private static List<String> addJogosResumidos(List<String> jogosFiltrado) {
		
		if(qtdeJogos > 0){
			
			List<String> jogosResumido = new ArrayList<String>(); 
			for(int i = 0; i < qtdeJogos; i++){
				jogosResumido.add(jogosFiltrado.get(i));
			}
			
			return jogosResumido;
			
		}else{
		
			return jogosFiltrado;
		}
	}
	
	private static List<String> filtraNumerosSequenciais(List<String> jogos) throws IOException {
		
		RemoveSequencial removeSequencial = new RemoveSequencial();
		return removeSequencial.enxuga(jogos, maximoSequencial);

	}
	
	private static List<String> filtraNumerosPares(List<String> jogos) throws IOException {
		
		RegraPares regraPares = new RegraPares();
		return regraPares.enxuga(jogos, maximoPares);

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
        arquivo.gerar(jogos, "c:\\Jogos\\jogoLotoFacil.txt");
	}

	private static void confereJogo(List<String> jogos, String jogoSorteado) {
		
        ConfereLotoFacil confere = new ConfereLotoFacil();
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
	 
	 public static String totalGastoJogo(ConfereLotoFacil confere){  
	     double valor = confere.getTotalJogos() * 2;   
		 return mascaraDinheiro(valor);  
	 } 
	 
	 public static String totalPremio11(ConfereLotoFacil confere){  
	     double valor = confere.getTotalAcertos11() * 4;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio12(ConfereLotoFacil confere){  
	     double valor = confere.getTotalAcertos12() * 8;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio13(ConfereLotoFacil confere){  
	     double valor = confere.getTotalAcertos13() * 20;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio14(ConfereLotoFacil confere){  
	     double valor = confere.getTotalAcertos14() * 1500;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio15(ConfereLotoFacil confere){  
	     double valor = confere.getTotalAcertos15() * 400000;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio(ConfereLotoFacil confere){  
	     double valor = (confere.getTotalAcertos15() * 400000)
	    		        + (confere.getTotalAcertos14() * 1500) 
	    		        + (confere.getTotalAcertos13() * 20) 
	    		        + (confere.getTotalAcertos12() * 8) 
	    		        + (confere.getTotalAcertos11() * 4);   
		 return mascaraDinheiro(valor);  
	 }
	 
	 
	 public static String totalLucro(ConfereLotoFacil confere){  

	     double valor = (confere.getTotalAcertos15() * 400000)
 		        + (confere.getTotalAcertos14() * 1500) 
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
