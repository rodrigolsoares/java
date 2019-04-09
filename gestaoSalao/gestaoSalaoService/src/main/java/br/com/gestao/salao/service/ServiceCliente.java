package br.com.gestao.salao.service;

import java.util.List;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceCliente {
	
	List<AgendaVO> pesquisaHistoricoServicoCliente(ClienteVO cli, UsuarioVO usuario) throws Exception;
	
	String validaCamposObrigatorios(ClienteVO cliente) throws Exception;
	
	String validaCamposObrigatoriosRapido(ClienteVO cliente);
	
	List<ClienteVO> getListaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	boolean verificaSeServicoExiste (ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	void gravaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	void atualizarCliente (ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	ClienteVO pesquisaClientePorCodigo(Integer codigo) throws Exception;
	
	ClienteVO pesquisaClientePorAndoid(String numeroTelefone, Integer codigoSalao) throws Exception;
	

}
