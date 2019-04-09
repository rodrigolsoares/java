package br.com.gestao.salao.mobile.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.mobile.util.MemoriaUsuario;
import br.com.gestao.salao.mobile.util.MensagensUtil;
import br.com.gestao.salao.service.ServiceProduto;
import br.com.gestao.salao.vo.ProdutoVO;
import br.com.gestao.salao.vo.UsuarioVO;


@Controller("produtoMobileMB")
@Scope("view")
public class ProdutoMB {
	
	@Autowired
	private ServiceProduto serviceProduto;
	
	private ProdutoVO produto;
	private List<ProdutoVO> listaProduto;
	private UsuarioVO usuario;
	private boolean habilitaAlterar;

	@PostConstruct
	public void init() {
		usuario = MemoriaUsuario.getUsuarioMemoria();
		produto = new ProdutoVO();
		listaProduto = new ArrayList<ProdutoVO>();
		habilitaAlterar = false;
	}
	
	public void gravar() throws Exception{  
       
		this.getListaProduto().clear();
		String mensagem = serviceProduto.validaCamposObrigatorios(this.getProduto());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);		
		}else{
			gravarProduto();
		}
	
    }
	
	public void calculaTotal() throws Exception{
		produto.setValorTotal(serviceProduto.calculaTotal(this.getProduto(), this.getUsuario()));
	}
	
	public void consultarProduto()throws Exception {
		this.setListaProduto(serviceProduto.getListProduto(this.getProduto(), this.getUsuario()));
	}
	
	public void editar(){
		habilitaAlterar = true;
	}
	
	public void atualizar() throws Exception{
		
		this.getListaProduto().clear();
		String mensagem = serviceProduto.validaCamposObrigatorios(this.getProduto());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);		
		}else{
			atualizaProduto();
		}
		
	}

	private void gravarProduto() throws Exception {
		
		this.getProduto().setFlagOperacao(0);
		if(serviceProduto.verificaSeProdutoExiste(this.getProduto(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.PRODUTO_JA_EXISTE);	
		}else{
			
			serviceProduto.gravaProduto(this.getProduto(), this.getUsuario()); 
			this.limpar();
			MensagensUtil.info(MensagensConstantes.CADASTRO_PRODUTO_SUCESSO);
			
		}
		
	}
	
	private void atualizaProduto() throws Exception {
		
		this.getProduto().setFlagOperacao(1);
		if(serviceProduto.verificaSeProdutoExiste(this.getProduto(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.PRODUTO_JA_EXISTE);	
		}else{
			
			serviceProduto.atualizaProduto(this.getProduto(), this.getUsuario());
			MensagensUtil.info(MensagensConstantes.ATUALIZA_PRODUTO_SUCESSO);

		}
		
	}
	
	
	public void limpar(){
		produto = new ProdutoVO();
	}
	
	

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public List<ProdutoVO> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<ProdutoVO> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public boolean isHabilitaAlterar() {
		return habilitaAlterar;
	}

	public void setHabilitaAlterar(boolean habilitaAlterar) {
		this.habilitaAlterar = habilitaAlterar;
	}
	
	
	
}
