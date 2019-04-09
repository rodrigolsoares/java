package br.com.gestao.salao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.dao.PlanoDao;
import br.com.gestao.salao.service.ServicePlano;
import br.com.gestao.salao.vo.PlanoVO;

@Service
@Transactional(readOnly=false)
public class ServicePlanoImpl implements ServicePlano {
	
	@Autowired
	private PlanoDao planoDao;
	
	public Map<String, Integer> getMapPlano() throws Exception{
		
		Map<String, Integer> mapPlano = new HashMap<String, Integer>();
		for(PlanoVO plano: this.getPlanos()){
			mapPlano.put(plano.getNome(), plano.getCodigo());
		}
		return mapPlano;
	}
	
	public PlanoVO getPlanosPorcodigo(Integer codigo) throws Exception{
		return planoDao.pesquisarPlanosByCodigo(codigo).get(0);
	}

	private List<PlanoVO> getPlanos() throws Exception{
		return planoDao.pesquisarPlanos();
	}
}
