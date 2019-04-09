package br.com.gestao.salao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.dao.FormaPagamentoDao;
import br.com.gestao.salao.service.ServiceFormaPagamento;
import br.com.gestao.salao.vo.FormaPagamentoVO;

@Service
@Transactional(readOnly=false)
public class ServiceFormaPagamentoImpl  implements ServiceFormaPagamento{
	
	@Autowired
	private FormaPagamentoDao formaPagamentoDao;
	
	public Map<String, Integer> getMap() throws Exception{
		
		Map<String, Integer> mapServico = new HashMap<String, Integer>();
		for(FormaPagamentoVO pagamento: getLista()){
			mapServico.put(pagamento.getNomeFormaPagamento(), pagamento.getCodigo());
		}
		return mapServico;
	}

	private List<FormaPagamentoVO> getLista() throws Exception{
		return formaPagamentoDao.pesquisaFormaPagamento();
	}
}
