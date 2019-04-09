package br.com.jogo.dulpasena;

import java.util.List;

public class ConfereDulpaSena {
	
	private int totalJogos;
	private int totalAcertos4;
	private int totalAcertos5;
	private int totalAcertos6;
	private int totalAcertos;
	
	ConfereDulpaSena(){
		
		totalJogos = 0;
		totalAcertos4 = 0;
		totalAcertos5 = 0;
		totalAcertos6 = 0;
		totalAcertos = 0;
	}
	
	public  void confere(String jogoRealizado, List<String> jogos){
		
		for(String jogo : jogos){
			this.confereJogos(jogoRealizado, jogo);
			
			this.totalJogos = this.totalJogos + 1;
			
			if(this.totalAcertos == 4){
				this.totalAcertos4 = this.totalAcertos4 + 1;
			
			}else if(this.totalAcertos == 5){
				this.totalAcertos5 = this.totalAcertos5 + 1;
			
			}else if(this.totalAcertos == 6){
				this.totalAcertos6= this.totalAcertos6 + 1;
			}
		}
		
		
		
		
	}

	private void confereJogos(String jogo, String jogoConfere) {
		
		String[] arrayJogo =  jogo.split(",");
		String[] arrayJogoConfere =  jogoConfere.split(",");
		this.totalAcertos = 0;
		
		for(int ijogo = 0; ijogo < arrayJogo.length; ijogo++){
			
			for(int iconfere = 0; iconfere < arrayJogoConfere.length; iconfere ++){
				
				int dezenaJogo = Integer.parseInt(arrayJogo[ijogo].trim());
				int dezenaJogoConfere = Integer.parseInt(arrayJogoConfere[iconfere].trim());
				
				if(dezenaJogo == dezenaJogoConfere){
					this.totalAcertos = this.totalAcertos + 1;
					break;
				}
				
			}
			
		}
	}

	public int getTotalJogos() {
		return totalJogos;
	}

	public int getTotalAcertos4() {
		return totalAcertos4;
	}

	public int getTotalAcertos5() {
		return totalAcertos5;
	}

	public int getTotalAcertos6() {
		return totalAcertos6;
	}

	
	
	
}
