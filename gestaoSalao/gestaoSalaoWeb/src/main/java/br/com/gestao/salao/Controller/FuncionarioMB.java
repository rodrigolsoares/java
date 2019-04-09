package br.com.gestao.salao.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.service.ServiceFuncionario;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.util.MensagensUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;


@Controller
@Scope("view")
public class FuncionarioMB {
	
	@Autowired
	private ServiceFuncionario serviceFuncionario;
	
	private FuncionarioVO funcionario;
	private FuncionarioVO funcionarioHist;
	private List<FuncionarioVO> listaFuncionario;
	private List<AgendaVO> listaHistorico;
	private UsuarioVO usuario;
	private boolean habilitaAlterar;

	@PostConstruct
	public void init() {
		usuario = MemoriaUsuario.getUsuarioMemoria();
		funcionario = new FuncionarioVO();
		listaFuncionario = new ArrayList<FuncionarioVO>();
		listaHistorico = new ArrayList<AgendaVO>();
		habilitaAlterar = false;
	}
	
	public void gravar() throws Exception{  
	       
		String mensagem = serviceFuncionario.validaCamposObrigatorios(this.getFuncionario());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			gravarFuncionario();
			
		}
	
    }
	
	public void atualizar()throws Exception{
		
		String mensagem = serviceFuncionario.validaCamposObrigatorios(this.getFuncionario());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			atualizarFuncionario();
			
		}
	}
	
	public void consultar()throws Exception {
		this.setListaFuncionario(serviceFuncionario.getListaFuncionario(this.getFuncionario(), this.getUsuario()));
	}
	
	
	public void editar(){
		habilitaAlterar = true;
	}
	
	public void abreHistorico(){
		this.getFuncionarioHist().setDataFinal(null);
		this.getFuncionarioHist().setDataInicial(null);
		this.getListaHistorico().clear();
	}
	
	public void pesquisaHistorico() throws Exception{
		this.setListaHistorico(serviceFuncionario.pesquisaHistoricoServicoFuncionario(this.getFuncionarioHist(), this.getUsuario()));
	}

	
	private void gravarFuncionario() throws Exception {
		
		this.getFuncionario().setFlagOperacao(0);
		if(serviceFuncionario.verificaSeServicoExiste(this.getFuncionario(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.FUNCIONARIO_JA_EXISTE);	
		}else{
			
			serviceFuncionario.gravaFuncionario(this.getFuncionario(), this.getUsuario()); 
			this.limpar();
			this.consultar();
			MensagensUtil.info(MensagensConstantes.CADASTRO_FUNCIONARIO_SUCESSO);
			
		}
		
	}
	
	private void atualizarFuncionario() throws Exception {
		
		this.getFuncionario().setFlagOperacao(1);
		if(serviceFuncionario.verificaSeServicoExiste(this.getFuncionario(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.FUNCIONARIO_JA_EXISTE);	
		}else{
			
			serviceFuncionario.atualizarFuncionario(this.getFuncionario(), this.getUsuario()); 
			this.limpar();
			this.consultar();
			MensagensUtil.info(MensagensConstantes.ATUALIZA_FUNCIONARIO_SUCESSO);
			
		}
		
	}
	

	public void limpar(){
		funcionario = new FuncionarioVO();
		funcionarioHist = new FuncionarioVO();
		listaFuncionario = new ArrayList<FuncionarioVO>();
	}


	
	public FuncionarioVO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}

	public List<FuncionarioVO> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<FuncionarioVO> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public List<AgendaVO> getListaHistorico() {
		return listaHistorico;
	}

	public void setListaHistorico(List<AgendaVO> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

	public FuncionarioVO getFuncionarioHist() {
		return funcionarioHist;
	}

	public void setFuncionarioHist(FuncionarioVO funcionarioHist) {
		this.funcionarioHist = funcionarioHist;
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