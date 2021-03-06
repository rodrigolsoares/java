package br.com.gestao.salao.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.service.ServiceServico;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.util.MensagensUtil;
import br.com.gestao.salao.vo.ServicoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller
@Scope("view")
public class ServicoMB {
	
	@Autowired
	private ServiceServico serviceServico;
	
	private ServicoVO servico;
	private List<ServicoVO> listaServico;
	private UsuarioVO usuario;
	private boolean habilitaAlterar;

	@PostConstruct
	public void init() throws Exception{
		usuario = MemoriaUsuario.getUsuarioMemoria();
		servico = new ServicoVO();
		listaServico = new ArrayList<ServicoVO>();
		habilitaAlterar = false;
		this.consultar();
		
	}
	
	
	public void gravar() throws Exception{  
	       
		String mensagem = serviceServico.validaCamposObrigatorios(this.getServico());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			gravarServico();
			
		}
	
    }
	
	public void atualizar()throws Exception{
		
		String mensagem = serviceServico.validaCamposObrigatorios(this.getServico());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			atualizarServico();
			
		}
	}
	
	private void consultar()throws Exception {
		this.setListaServico(serviceServico.getListServico(this.getUsuario()));
	}
	
	
	public void editar(){
		habilitaAlterar = true;
	}

	
	private void gravarServico() throws Exception {
		
		this.getServico().setFlagOperacao(0);
		if(serviceServico.verificaSeServicoExiste(this.getServico(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.SERVICO_JA_EXISTE);	
		}else{
			
			serviceServico.gravaServico(this.getServico(), this.getUsuario()); 
			this.limpar();
			this.consultar();
			MensagensUtil.info(MensagensConstantes.CADASTRO_SERVICO_SUCESSO);
			
		}
		
	}
	
	private void atualizarServico() throws Exception {
		
		this.getServico().setFlagOperacao(1);
		if(serviceServico.verificaSeServicoExiste(this.getServico(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.SERVICO_JA_EXISTE);	
		}else{
			
			serviceServico.atualizaServico(this.getServico(), this.getUsuario()); 
			this.consultar();
			MensagensUtil.info(MensagensConstantes.ATUALIZA_SERVICO_SUCESSO);
			
		}
		
	}
	

	
	public void limpar(){
		servico = new ServicoVO();
	}

	public ServicoVO getServico() {
		return servico;
	}

	public void setServico(ServicoVO servico) {
		this.servico = servico;
	}

	public List<ServicoVO> getListaServico() {
		return listaServico;
	}

	public void setListaServico(List<ServicoVO> listaServico) {
		this.listaServico = listaServico;
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
