package br.com.gestao.salao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.dao.StatusAtendimentoDao;
import br.com.gestao.salao.service.ServiceStatusAtendimento;
import br.com.gestao.salao.vo.StatusAtendimentoVO;

@Service
@Transactional(readOnly=false)
public class ServiceStatusAtendimentoImpl  implements ServiceStatusAtendimento{
	
	@Autowired
	private StatusAtendimentoDao statusAtendimentoDao;
	
	public Map<String, Integer> getMap() throws Exception{
		
		Map<String, Integer> mapServico = new HashMap<String, Integer>();
		for(StatusAtendimentoVO status: getLista()){
			mapServico.put(status.getNome(), status.getCodigo());
		}
		return mapServico;
	}

	private List<StatusAtendimentoVO> getLista() throws Exception{
		return statusAtendimentoDao.pesquisaStatusAtendimento();
	}
	
}
