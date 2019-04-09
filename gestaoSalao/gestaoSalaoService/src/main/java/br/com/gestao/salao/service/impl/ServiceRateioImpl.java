package br.com.gestao.salao.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.RateioDao;
import br.com.gestao.salao.service.ServiceRateio;
import br.com.gestao.salao.vo.RateioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
public class ServiceRateioImpl  implements ServiceRateio{
	
	@Autowired
	private RateioDao rateioDao;
	
	public String validaCamposObrigatorios(RateioVO rateio) {
		
		String retorno = "";
		
		if(rateio.getValor() == null || rateio.getValor().intValue() == 0 ){
			retorno += "Erro, valor do rateio não foi preenchido" + Constantes.PULA_LINHA_XHTML;
		}
	
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public void atualizaRateio(RateioVO rateio, UsuarioVO usuario) throws Exception{
		rateioDao.atualizaRateio(rateio.getValor(), usuario, rateio.isHabilita());
	}
	
	public RateioVO pesquisar(UsuarioVO usuario) throws Exception{
		return rateioDao.pesquisaRateio(usuario);
	}
	

}
