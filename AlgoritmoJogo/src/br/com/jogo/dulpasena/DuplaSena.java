package br.com.jogo.dulpasena;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import br.com.jogo.Arquivo;
import br.com.jogo.Combinacao;
import br.com.jogo.RemoveSequencial;
import br.com.jogo.VetorUtilities;

public class DuplaSena {
	
	private static final Locale BRAZIL = new Locale("pt","BR");
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	private static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);
	
	private static final String jogoSorteado1 = "4, 19, 20, 26,41, 49";
	
	private static final String jogoSorteado2 = "2, 8, 10, 12, 25, 41";
	
	private static final Long totalCombinacao = new Long(6);
	
	private static final int maximoSequencial = 2;

	private static final int qtdeJogos = 0;
	
	private static final Integer[] vetorFixos = {};
	//private static final Integer[] vetor =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 19, 20, 22, 24, 25, 26, 28, 30, 32, 35, 36, 38, 39, 41, 43, 46, 48, 49, 50};
	
	private static final Integer[] vetor =  {45, 8,	38,	26,	43,	19,	34,	35};
	
	
	public static void main(String[] args) throws IOException {
	    
		System.out.println("Início");
		List<String> jogos = new ArrayList<String>();
		
		System.out.println("Gerando Jogos");
		montaJogos(jogos);
		
		System.out.println("Filtro sequenciais");
		List<String> jogosFiltrado = filtraNumerosSequenciais(jogos);
		
		System.out.println("Embaralhando jogo");
		Collections.sort(jogosFiltrado);
		
		System.out.println("Limitando quantidade de jogos");
		List<String> jogosResumido = addJogosResumidos(jogosFiltrado);
		
		System.out.println("Gravando Jogos");
		geraJogosArquivos(jogosResumido);
		
		System.out.println("Realizando conferencia Jogo 1");
        confereJogo(jogosResumido, jogoSorteado1);
        
        System.out.println(" ");
        
        System.out.println("Realizando conferencia Jogo 2");
        confereJogo(jogosResumido, jogoSorteado2);
        
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
        arquivo.gerar(jogos, "c:\\Jogos\\jogoDupla.txt");
	}

	private static void confereJogo(List<String> jogos, String jogoSorteado) {
		
        ConfereDulpaSena confere = new ConfereDulpaSena();
        confere.confere(jogoSorteado, jogos);
        
        System.out.println("Total de números no fechamento: " + vetor.length);
        System.out.println("Total de jogos: " + confere.getTotalJogos());
        System.out.println("Valor gasto no jogos: " + totalGastoJogo(confere));
        System.out.println("Total jogos 4 acertos: " + confere.getTotalAcertos4());
        System.out.println("Total jogos 5 acertos: " + confere.getTotalAcertos5());
        System.out.println("Total jogos 6 acertos: " + confere.getTotalAcertos6());
        
        System.out.println("Valor premio 4 acertos: " + totalPremio4(confere));
        System.out.println("Valor premio 5 acertos: " + totalPremio5(confere));
        System.out.println("Valor premio 6 acertos: " + totalPremio6(confere));

        System.out.println("Valor total premio: " + totalPremio(confere));
        System.out.println("Lucro com o premio: " + totalLucro(confere));
	}
	 
	 public static String totalGastoJogo(ConfereDulpaSena confere){  
	     double valor = confere.getTotalJogos() * 2;   
		 return mascaraDinheiro(valor);  
	 } 
	 
	 public static String totalPremio6(ConfereDulpaSena confere){  
	     double valor = confere.getTotalAcertos6() * 1000000;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio5(ConfereDulpaSena confere){  
	     double valor = confere.getTotalAcertos5() * 5000;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String totalPremio4(ConfereDulpaSena confere){  
	     double valor = confere.getTotalAcertos4() * 100;   
		 return mascaraDinheiro(valor);  
	 }
	 
	 
	 public static String totalPremio(ConfereDulpaSena confere){  
	     double valor = (confere.getTotalAcertos6() * 1000000)
	    		        + (confere.getTotalAcertos5() * 5000) 
	    		        + (confere.getTotalAcertos4() * 100);   
		 return mascaraDinheiro(valor);  
	 }
	 
	 
	 public static String totalLucro(ConfereDulpaSena confere){  

	     double valor = (confere.getTotalAcertos6() * 1000000)
 		        + (confere.getTotalAcertos5() * 5000) 
 		        + (confere.getTotalAcertos4() * 100) 
 		         - (confere.getTotalJogos() * 2);
	     
		 return mascaraDinheiro(valor);  
	 }
	 
	 public static String mascaraDinheiro(double valor){  
	        return DINHEIRO_REAL.format(valor);  
	 }  
	 

}
