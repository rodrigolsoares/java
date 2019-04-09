package br.com.gestao.salao.service;

import br.com.gestao.salao.vo.RateioVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceRateio {
	
	String validaCamposObrigatorios(RateioVO rateio); 
	
	void atualizaRateio(RateioVO rateio, UsuarioVO usuario) throws Exception;
	
	RateioVO pesquisar(UsuarioVO usuario) throws Exception;
}
