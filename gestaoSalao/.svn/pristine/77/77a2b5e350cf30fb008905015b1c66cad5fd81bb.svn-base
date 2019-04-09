package br.com.gestao.salao.util;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import br.com.gestao.salao.vo.AgendaVO;

public class PreparaColecaoAgenda {

	public static ArrayList<AgendaVO> montaServicoAjudante(ArrayList<AgendaVO> lista){
		
		for (AgendaVO agenda : lista){
			
			for(String ajudante : agenda.getListaAjudanteSelecionado()){
				
				if(StringUtils.isEmpty(agenda.getAjudantes()) ){
					agenda.setAjudantes(ajudante);
				}else{
					agenda.setAjudantes( agenda.getAjudantes() + " - " + ajudante);
				}
			}
			
			for(String servico : agenda.getListaServicoSelecionado()){
				
				if(StringUtils.isEmpty(agenda.getServicos())){
					agenda.setServicos(servico);
				}else{
					agenda.setServicos( agenda.getServicos() + " - " + servico);
				}
			}
		}
		
		return lista;
	}

}
