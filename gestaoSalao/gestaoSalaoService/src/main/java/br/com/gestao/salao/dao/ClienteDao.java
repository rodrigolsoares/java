package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ClienteDao {
	
	void inseriCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	void atualizaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	boolean verificaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	List<ClienteVO> pesquisaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception;
	
	List<AgendaVO> pesquisaHistoricoServicoCliente(ClienteVO cli, UsuarioVO usuario, boolean somenteFechados) throws Exception;
	
	ClienteVO pesquisaClientePorCodigo(Integer codigo) throws Exception;
	
	ClienteVO pesquisaClientePorAppAndroid(String numeroTelefone, Integer codigoSalao) throws Exception;
}
