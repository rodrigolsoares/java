package br.com.gestao.salao.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.service.ServiceCliente;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.util.MensagensUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller
@Scope("view")
public class ClienteMB {
	
	@Autowired
	private ServiceCliente serviceCliente;

	
	private ClienteVO cliente;
	private ClienteVO clienteHist;
	private List<ClienteVO> listaCliente;
	private List<AgendaVO> listaHistorico;
	private UsuarioVO usuario;
	private boolean habilitaAlterar;

	@PostConstruct
	public void init() throws EmailException {
		
		usuario = MemoriaUsuario.getUsuarioMemoria();
		cliente = new ClienteVO();
		clienteHist = new ClienteVO();
		listaCliente = new ArrayList<ClienteVO>();
		listaHistorico = new ArrayList<AgendaVO>();
		habilitaAlterar = false;
		
	}
	
	public void gravar() throws Exception{  
	       
		String mensagem = serviceCliente.validaCamposObrigatorios(this.getCliente());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			gravarCliente();
			
		}
	
    }
	
	
	public void atualizar()throws Exception{
		
		String mensagem = serviceCliente.validaCamposObrigatorios(this.getCliente());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			atualizarCliente();
			
		}
	}
	
	public void consultar()throws Exception {
		this.setListaCliente(serviceCliente.getListaCliente(this.getCliente(), this.getUsuario()));
	}
	
	
	public void abreHistorico(){
		this.getClienteHist().setDataFinal(null);
		this.getClienteHist().setDataInicial(null);
		this.getListaHistorico().clear();
	}
	
	public void pesquisaHistorico() throws Exception{
		this.setListaHistorico(serviceCliente.pesquisaHistoricoServicoCliente(this.getClienteHist(), this.getUsuario()));
	}
	
	public void editar(){
		habilitaAlterar = true;
	}

	
	private void gravarCliente() throws Exception {
		
		this.getCliente().setFlagOperacao(0);
		if(serviceCliente.verificaSeServicoExiste(this.getCliente(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.CLIENTE_JA_EXISTE);	
		}else{
			
			serviceCliente.gravaCliente(this.getCliente(), this.getUsuario()); 
			this.limpar();
			this.consultar();
			MensagensUtil.info(MensagensConstantes.CADASTRO_CLIENTE_SUCESSO);
			
		}
		
	}
	
	private void atualizarCliente() throws Exception {
		
		this.getCliente().setFlagOperacao(1);
		if(serviceCliente.verificaSeServicoExiste(this.getCliente(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.CLIENTE_JA_EXISTE);	
		}else{
			
			serviceCliente.atualizarCliente(this.getCliente(), this.getUsuario()); 
			this.consultar();
			MensagensUtil.info(MensagensConstantes.ATUALIZA_CLIENTE_SUCESSO);
			
		}
		
	}

	public void limpar(){
		cliente = new ClienteVO();
		listaCliente = new ArrayList<ClienteVO>();
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public List<ClienteVO> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<ClienteVO> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public List<AgendaVO> getListaHistorico() {
		return listaHistorico;
	}

	public void setListaHistorico(List<AgendaVO> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

	public ClienteVO getClienteHist() {
		return clienteHist;
	}

	public void setClienteHist(ClienteVO clienteHist) {
		this.clienteHist = clienteHist;
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