package br.com.jogo;

public class VetorUtilities {
	
	public Integer[] ordemCrescente(Integer[] elementos){
		
		for(int i = 0; i < elementos.length; i++ ){
			for(int j = 0; j < elementos.length; j++ ){
				
				if(elementos[i] < elementos[j]){
					
	                int aux = elementos[i];
	                elementos[i] = elementos[j];
	                elementos[j] = aux;
	            }
			}
		}
		
		return elementos;
	}
	
	public Integer[] merge(Integer[] elementos, Integer[] elementos2){
		
		int tamanhoNovoVetor = 0;
		Integer[] novoVetor = new Integer[elementos.length + elementos2.length];
		
		for(int i = 0; i < elementos.length; i++){
			novoVetor[tamanhoNovoVetor] = elementos[i];
			tamanhoNovoVetor += 1;
		}
		
		for(int i = 0; i < elementos2.length; i++){
			novoVetor[tamanhoNovoVetor] = elementos2[i];
			tamanhoNovoVetor += 1;
		}
		
		return novoVetor;
	}

}
