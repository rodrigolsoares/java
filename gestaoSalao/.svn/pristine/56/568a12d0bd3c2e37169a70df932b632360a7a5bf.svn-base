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
import br.com.gestao.salao.service.ServiceDespesa;
import br.com.gestao.salao.vo.DespesaVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller("despesaMobileMB")
@Scope("view")
public class DespesaMB {
	
	@Autowired
	private ServiceDespesa serviceDespesa;
	
	private DespesaVO despesa;
	private List<DespesaVO> listaDespesa;
	private UsuarioVO usuario;
	private boolean habilitaAlterar;
	
	
	@PostConstruct
	public void init() {
		usuario = MemoriaUsuario.getUsuarioMemoria();
		despesa = new DespesaVO();
		listaDespesa = new ArrayList<DespesaVO>();
		habilitaAlterar = false;
	}
	
	public void gravar() throws Exception{  
	       
		String mensagem = serviceDespesa.validaCamposObrigatorios(this.getDespesa());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);		
		}else{
			gravarDespesa();
		}
	
    }
	
	public void atualizar()throws Exception{
		
		String mensagem = serviceDespesa.validaCamposObrigatorios(this.getDespesa());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			atualizarDespesa();
			
		}
	}
	
	public void consultar() throws Exception {
		this.setListaDespesa(serviceDespesa.pesquisar(this.getDespesa(), this.getUsuario()));
	}
	
	private void gravarDespesa() throws Exception {
		
		this.getDespesa().setFlagOperacao(0);
		if(serviceDespesa.verificaSeDespesaExiste(this.getDespesa(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.DESPESA_JA_EXISTE);	
		}else{
			
			serviceDespesa.gravar(this.getDespesa(), this.getUsuario()); 
			this.limpar();
			MensagensUtil.info(MensagensConstantes.CADASTRO_DESPESA_SUCESSO);
			
		}
		
	}
	
	public void editar(){
		habilitaAlterar = true;
	}

	private void atualizarDespesa() throws Exception {
		
		this.getDespesa().setFlagOperacao(1);
		if(serviceDespesa.verificaSeDespesaExiste(this.getDespesa(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.DESPESA_JA_EXISTE);	
		}else{
			
			serviceDespesa.atualiza(this.getDespesa(), this.getUsuario()); 
			this.limpar();
			MensagensUtil.info(MensagensConstantes.ATUALIZA_DESPESA_SUCESSO);
			
		}
		
	}
	
	public void limpar(){
		despesa = new DespesaVO();
		listaDespesa = new ArrayList<DespesaVO>();
	}

	public DespesaVO getDespesa() {
		return despesa;
	}

	public void setDespesa(DespesaVO despesa) {
		this.despesa = despesa;
	}

	public List<DespesaVO> getListaDespesa() {
		return listaDespesa;
	}

	public void setListaDespesa(List<DespesaVO> listaDespesa) {
		this.listaDespesa = listaDespesa;
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
