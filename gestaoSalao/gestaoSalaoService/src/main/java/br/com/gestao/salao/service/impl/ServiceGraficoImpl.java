package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.service.ServiceGrafico;
import br.com.gestao.salao.vo.AgendaVO;
 
@Service
@Transactional(readOnly=false)
public class ServiceGraficoImpl  implements ServiceGrafico{
	
	
	public List<AgendaVO> geraDadosClienteMaisAtende(final List<AgendaVO> plista, final List<AgendaVO> plistaServicoPendente){
		
		Map<Integer, AgendaVO> mapAgenda = new HashMap<Integer, AgendaVO>();
		List<AgendaVO> plistaNova = plista;
		plistaNova.addAll(plistaServicoPendente);
		
		for(AgendaVO agenda: plistaNova){
			
			if(mapAgenda.containsKey(agenda.getCodigoCliente())){
			
				agenda = mapAgenda.get(agenda.getCodigoCliente());
				agenda.setQtdeRegistros(agenda.getQtdeRegistros() + 1);
				
			}else{
				agenda.setQtdeRegistros(1);
			}
			
			mapAgenda.put(agenda.getCodigoCliente(), agenda);
			
		}
		
		return new ArrayList<AgendaVO>(mapAgenda.values());
	}
	
}
