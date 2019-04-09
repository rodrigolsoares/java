package br.com.jogo;

import java.util.ArrayList;
import java.util.List;

public class RegraPares {
	
	public List<String> enxuga(List<String> listaTotal, int maximoSequencial){
		
		List<String> novaLista = new ArrayList<String>();
		
		for(String jogo : listaTotal){
			
			Integer[] vetor =  converteVetorStringToInteger(jogo.split(","));
		
			if(verificaParesRecursiva(vetor, 0, maximoSequencial)){
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
	
	
	
	private static boolean verificaParesRecursiva(Integer[] vetor, int indiceVetor, int maximaSequencia){
		
		int sequencia = 0;
		
		for(int i = indiceVetor; i < vetor.length - 1; i++ ){
			
			int conteudoPosicao = vetor[i];

			if((conteudoPosicao % 2) == 0  ){
				sequencia = sequencia + 1;
			} 
				
		}
		
		if(maximaSequencia < sequencia){
			return false;
			
		}else if(indiceVetor < vetor.length){
			return verificaParesRecursiva(vetor, indiceVetor + 1, maximaSequencia );
		}
		
		return true;
	}
	
	
}
