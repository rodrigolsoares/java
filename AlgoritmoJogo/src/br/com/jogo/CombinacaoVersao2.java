package br.com.jogo;

import java.util.ArrayList;
import java.util.List;

public class CombinacaoVersao2 {
	
	private static int sequenciaNumerica ;
    private Integer[] vetor ;
	
	public CombinacaoVersao2(Integer[] vetor, Long totalCombinacao){
		
		this.sequenciaNumerica = totalCombinacao.intValue();
		this.vetor = vetor;
	}
	
	public static void main(String args[]){
		
		Integer[] vetor =  {45, 8,	38,	26,	43,	19,	34,	35};
		sequenciaNumerica = 6;
		
		/*CombinacaoVersao2 comb2 = new CombinacaoVersao2(vetor, totalCombinacao);*/
		
		List<Integer[]>  listaJogo = new ArrayList<Integer[]>();
		
		for(int i = 0; i < vetor.length - 1; i++ ){
			
			Integer[] jogo = new Integer[sequenciaNumerica];
			
			
			for(int j = 0 + 1; j < sequenciaNumerica - 1; j++ ){
				jogo[j] =  vetor[j];
			}
			
			listaJogo.add(jogo);
			
		}
		
		System.exit(0);
		
	}
	
	
	/*public List<Integer[]> montaJogos(){
		
		List<Integer[]>  listaJogo = new ArrayList<Integer[]>();
		
		for(Integer dezena : vetor){
			
		for(int i = 0; i < vetor.length; i++){
			
			Integer[] jogo = new Integer[sequenciaNumerica];
			
			listaJogo.add(montaJogoRecursiva(jogo, i));
		}
		
	}
	
	private static Integer[] montaJogoRecursiva(Integer[] vetor, int indiceVetor){
		
		int sequencia = 0;
		
		for(int i = indiceVetor; i < vetor.length - 1; i++ ){
			
			int conteudoPosicao = vetor[i];
			int conteudoProximo = vetor[i + 1];
			
			conteudoPosicao = conteudoPosicao + 1;
			
			if(conteudoPosicao == conteudoProximo){
				sequencia = sequencia + 1;
			} else if(maximaSequencia > sequencia){
				sequencia = 0;
			}
				
		}
		
		if(maximaSequencia < sequencia){
			return false;
			
		}else if(indiceVetor < vetor.length){
			return verificaSequenciaRecursiva(vetor, indiceVetor + 1, maximaSequencia );
		}
		
		return true;
	}*/
	
	
	
	
}
