package br.com.gestao.salao.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.RateioDao;
import br.com.gestao.salao.dao.ResponsavelDao;
import br.com.gestao.salao.dao.SalaoDao;
import br.com.gestao.salao.dao.UsuarioDao;
import br.com.gestao.salao.service.ServiceCadastroRapido;
import br.com.gestao.salao.util.ValidarDeCampos;
import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.SalaoVO;

@Service
@Transactional(readOnly=false)
public class ServiceCadastroRapidoImpl implements ServiceCadastroRapido {
	
	@Autowired
	private ResponsavelDao responsavelDao;
	
	@Autowired
	private SalaoDao salaoDao; 
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private RateioDao rateioDao;
	
	public String validaCamposObrigatoriosCadRapido(ResponsavelVO responsavel, SalaoVO salao) {
		
		String retorno = "";
		
		if(StringUtils.isEmpty(responsavel.getNome())){
			retorno += "Erro, digite o nome do responsável" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getNomeFantasia())){
			retorno += "Erro, digite o nome do salão" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getTel1())){
			retorno +=  "Erro, digite o primeiro telefone" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.email(salao.getEmail())){
			retorno +=  "E-mail digitado é inválido" + Constantes.PULA_LINHA_XHTML;
		}
		
	
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}



}
