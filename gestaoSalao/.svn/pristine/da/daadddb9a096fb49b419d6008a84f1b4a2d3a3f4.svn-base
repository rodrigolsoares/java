package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.ProdutoVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ProdutoDao {
	
	void inseriProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception;
	
	void atualizaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception;
	
	List<ProdutoVO> pesquisaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception;
	
	ProdutoVO pesquisaProdutoPorCodigo(String codigo, UsuarioVO usuario) throws Exception;
	
	boolean verificaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception;
}
