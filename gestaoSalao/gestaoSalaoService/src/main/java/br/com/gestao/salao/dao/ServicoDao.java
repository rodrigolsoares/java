package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.ServicoVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServicoDao {
	
	void inseriServico(ServicoVO servico, UsuarioVO usuario) throws Exception;
	
	void atualizaServico(ServicoVO servico, UsuarioVO usuario) throws Exception;
	
	boolean verificaServico(ServicoVO servico, UsuarioVO usuario) throws Exception;
	
	List<ServicoVO> pesquisaServico(UsuarioVO usuario) throws Exception;
	
	List<ServicoVO> pesquisaServicoAgenda(UsuarioVO usuario) throws Exception;
	
	ServicoVO pesquisaServicoPorCodigo(String codigo, UsuarioVO usuario) throws Exception;
	
	List<ServicoVO> pesquisaServico(SalaoVO salao) throws Exception;

}
