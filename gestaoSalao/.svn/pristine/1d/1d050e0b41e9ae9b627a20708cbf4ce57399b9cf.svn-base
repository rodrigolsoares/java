package br.com.gestao.salao.service;

import java.util.List;

import br.com.gestao.salao.vo.ProdutoVO;
import br.com.gestao.salao.vo.UsuarioVO;


public interface ServiceProduto {
	
	String validaCamposObrigatorios(ProdutoVO produto);
	
	void gravaProduto(ProdutoVO produto , UsuarioVO usuario) throws Exception;
	
	boolean verificaSeProdutoExiste(ProdutoVO produto, UsuarioVO usuario) throws Exception;
	
	List<ProdutoVO> getListProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception;
	
	void atualizaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception;
	
	Double calculaTotal(ProdutoVO produto, UsuarioVO usuario)throws Exception;
}
