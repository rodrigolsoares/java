package br.com.gestao.salao.service;

import java.util.List;

import br.com.gestao.salao.vo.AgendaVO;



public interface ServiceGrafico {
		
	List<AgendaVO> geraDadosClienteMaisAtende(final List<AgendaVO> plistaServico, final List<AgendaVO> plistaServicoPendente);
}
