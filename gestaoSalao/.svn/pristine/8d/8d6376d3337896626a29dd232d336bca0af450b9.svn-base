package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface UsuarioDao {
	
	void inseriUsuario(UsuarioVO usuario, SalaoVO salao) throws Exception;
	
	void atualizaUsuario(UsuarioVO usuario) throws Exception;
	
	void atualizaSenha(String login, String senha) throws Exception;
	
	boolean verificaUsuario(UsuarioVO usuario, int tipoOperacao) throws Exception;
	
	boolean verificaAutenticacaoUsuario(UsuarioVO usuario) throws Exception;
	
	boolean verificaAutenticacaoStatus(UsuarioVO usuario) throws Exception;
	
	boolean verificaAutenticacaoSenha(UsuarioVO usuario) throws Exception;
	
	UsuarioVO pesquisaUsuarioPorCodigo(String codigo) throws Exception;
	
	UsuarioVO pesquisaUsuarioPorLogin(String login) throws Exception;
	
	List<UsuarioVO> pesquisaUsuario() throws Exception;
}
