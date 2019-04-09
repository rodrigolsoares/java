package br.com.jogo;

import java.util.ArrayList;
import java.util.List;

public class RemoveSequencial {
	
	public List<String> enxuga(List<String> listaTotal, int maximoSequencial){
		
		List<String> novaLista = new ArrayList<String>();
		
		for(String jogo : listaTotal){
			
			Integer[] vetor =  converteVetorStringToInteger(jogo.split(","));
		
			if(verificaSequenciaRecursiva(vetor, 0, maximoSequencial)){
				novaLista.add(jogo);
			}
		
		}
		
		return novaLista;
		
	}
	
	private Integer[] converteVetorStringToInteger(String[] vetor){
		Integer[] vetorInteger = new Integer[vetor.length];
		
		int i = 0;
		for(String valor : vetor){
			vetorInteger[i] = Integer.parseInt(valor.trim());
			i = i + 1;
		}
		
		return vetorInteger;
		
	}
	
	
	
	private static boolean verificaSequenciaRecursiva(Integer[] vetor, int indiceVetor, int maximaSequencia){
		
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
	}
	
	
}
