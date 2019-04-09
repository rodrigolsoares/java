package br.com.gestao.salao.service;

import java.util.List;
import java.util.Map;

import br.com.gestao.salao.vo.ServicoVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceServico {
	
	String validaCamposObrigatorios(ServicoVO servico) throws Exception;
	
	List<ServicoVO> getListServico(UsuarioVO usuario)throws Exception ;
	
	Map<String, Integer> getMapServico(UsuarioVO usuario) throws Exception;
	
	boolean verificaSeServicoExiste(ServicoVO servico, UsuarioVO usuario) throws Exception;
	
	void gravaServico(ServicoVO servico, UsuarioVO usuario) throws Exception;
	
	void atualizaServico(ServicoVO servico, UsuarioVO usuario) throws Exception;
	
	Double calculaValorServico(List<String> listaServico, UsuarioVO usuario) throws Exception;
	

}
