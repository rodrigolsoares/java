package br.com.gestao.salao.service;

import java.util.List;

import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceUsuario {
	
	UsuarioVO login(UsuarioVO usuario) throws Exception;
	
	List<UsuarioVO> geraUsuariosParaPlanoSalao(SalaoVO salao, Integer codigoPlano) throws Exception;
	
	String validaAlteraSenha(final UsuarioVO usuario) throws Exception;
	
	void alterarSenha(final UsuarioVO usuario) throws Exception;
}
