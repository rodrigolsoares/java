package br.com.gestao.salao.service;

import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.FluxoCaixaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;


public interface ServiceFluxoCaixa {
	
	FluxoCaixaVO getFluxoDeCaixa(final FluxoCaixaVO fluxoCaixaOld , UsuarioVO usuario) throws Exception;
	
	FluxoCaixaVO getFluxoDeCaixaByFuncionario(final FuncionarioVO funcOld , UsuarioVO usuario) throws Exception;
	
	FluxoCaixaVO getFluxoDeCaixaByCliente(final ClienteVO cliOld, UsuarioVO usuario) throws Exception;
	
}
