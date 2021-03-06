package br.com.gestao.salao.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.ProdutoDao;
import br.com.gestao.salao.service.ServiceProduto;
import br.com.gestao.salao.vo.ProdutoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceProdutoImpl  implements ServiceProduto{
	
	@Autowired
	private ProdutoDao produtoDao;
	
	public String validaCamposObrigatorios(ProdutoVO produto){
		
		String retorno = "";
		
		if(StringUtils.isEmpty(produto.getNomeProduto())){
			retorno += "Erro, digite o nome do produto" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(produto.getMarca())){
			retorno +=   "Erro, digite a marca" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(produto.getStatus() == 0){
			retorno +=  "Erro, selecione o status" + Constantes.PULA_LINHA_XHTML;
			
		}
		
		if(produto.getQteEstoque() == 0){
			retorno +=  "Erro, quantidade de estoque deve ser maior que 0" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(produto.getQteMinimaEstoque() == 0){
			retorno +=  "Erro, quantidade minima em estoque deve ser maior que 0" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(produto.getValorPago().equals(0)){
			retorno +=  "Erro, valor pago deve ser maior que 0" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public void atualizaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception{
		produtoDao.atualizaProduto(produto, usuario);
	}
	
	public void gravaProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception{
		produtoDao.inseriProduto(produto, usuario);
	}
	
	public boolean verificaSeProdutoExiste(ProdutoVO produto, UsuarioVO usuario) throws Exception{
		return produtoDao.verificaProduto(produto, usuario);
	}
	
	public List<ProdutoVO> getListProduto(ProdutoVO produto, UsuarioVO usuario) throws Exception{
		return produtoDao.pesquisaProduto(produto, usuario);
	}
	
	public Double calculaTotal(ProdutoVO produto, UsuarioVO usuario)throws Exception{
		return produto.getValorPago() * produto.getQteEstoque();
	}
}
