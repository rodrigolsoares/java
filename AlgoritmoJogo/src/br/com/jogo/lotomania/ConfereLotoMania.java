package br.com.jogo.lotomania;

import java.util.List;

public class ConfereLotoMania {
	
	private int totalJogos;
	private int totalAcertos11;
	private int totalAcertos12;
	private int totalAcertos13;
	private int totalAcertos14;
	private int totalAcertos15;
	private int totalAcertos;
	
	ConfereLotoMania(){
		
		totalJogos = 0;
		totalAcertos11 = 0;
		totalAcertos12 = 0;
		totalAcertos13 = 0;
		totalAcertos14 = 0;
		totalAcertos15 = 0;
		totalAcertos = 0;
	}
	
	public  void confere(String jogoRealizado, List<String> jogos){
		
		for(String jogo : jogos){
			this.confereJogos(jogoRealizado, jogo);
			
			this.totalJogos = this.totalJogos + 1;
			
			if(this.totalAcertos == 11){
				this.totalAcertos11 = this.totalAcertos11 + 1;
			}else if(this.totalAcertos == 12){
				this.totalAcertos12 = this.totalAcertos12 + 1;
			}else if(this.totalAcertos == 13){
				this.totalAcertos13 = this.totalAcertos13 + 1;
			}else if(this.totalAcertos == 14){
				this.totalAcertos14 = this.totalAcertos14 + 1;
			}else if(totalAcertos == 15){
				this.totalAcertos15 = this.totalAcertos15 + 1;
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

	public int getTotalAcertos11() {
		return totalAcertos11;
	}

	public int getTotalAcertos12() {
		return totalAcertos12;
	}

	public int getTotalAcertos13() {
		return totalAcertos13;
	}

	public int getTotalAcertos14() {
		return totalAcertos14;
	}

	public int getTotalAcertos15() {
		return totalAcertos15;
	}
	
	
}
