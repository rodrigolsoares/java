package br.com.gestao.salao.service;

import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.SalaoVO;

public interface ServiceCadastroRapido {
	
	String validaCamposObrigatoriosCadRapido(ResponsavelVO responsavel, SalaoVO salao);
	
}
