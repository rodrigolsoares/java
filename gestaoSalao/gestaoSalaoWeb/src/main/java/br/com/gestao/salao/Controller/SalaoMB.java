package br.com.gestao.salao.Controller;

//import java.util.logging.Logger;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.Constants.UrlConstantes;
import br.com.gestao.salao.service.ServicePlano;
import br.com.gestao.salao.service.ServiceResponsavel;
import br.com.gestao.salao.service.ServiceUsuario;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.util.MensagensUtil;
import br.com.gestao.salao.vo.PlanoVO;
import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller
@Scope("view")
public class SalaoMB {

	//private static Logger logger = Logger.getLogger(SalaoMB.class.getName());
	
	@Autowired
	private ServiceResponsavel serviceResponsavel;
	
	@Autowired
	private ServicePlano servicePlano;
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	private ResponsavelVO responsavel;
	private SalaoVO salao;
	private Map<String, Integer> mapPlano;
	private PlanoVO plano;
	private boolean skip; 
	private UsuarioVO usuario;

	
	@PostConstruct
	public void init() throws Exception{
		
		usuario = MemoriaUsuario.getUsuarioMemoria();
		responsavel = new ResponsavelVO();
		mapPlano = servicePlano.getMapPlano();
		plano = new PlanoVO();
		salao = new SalaoVO();
		
	}
	
	public String onFlowProcesso(FlowEvent event) throws Exception{  
	     
		
		if(event.getNewStep().equals("tbPlano")){
			
			String mensagem = serviceResponsavel.validaCamposObrigatoriosFrameResponsavel(this.getResponsavel());
			return validaProcesso(event, mensagem, "tabResp");
			 
		 }
		
		if(event.getNewStep().equals("tabCadSalao")){
			
			String mensagem = serviceResponsavel.validaCamposObrigatoriosFramePlano(this.getResponsavel());
			return validaProcesso(event, mensagem, "tbPlano");
			
		 }
		
		if(event.getNewStep().equals("tbUsuario")){
			
			String mensagem = serviceResponsavel.validaCamposObrigatoriosFrameSalao(this.getSalao());
			this.geraUsuarios(mensagem);
			return validaProcesso(event, mensagem, "tabCadSalao");
			 
		 }
		
		if(event.getNewStep().equals("tabContratoResponsavel")){
			
			String mensagem = serviceResponsavel.validaCamposObrigatoriosFrameUsuario(this.getSalao().getListaUsuarios());
			MensagensUtil.info(MensagensConstantes.CADASTRO_CONTRATO_SUCESSO);
			return validaProcesso(event, mensagem, "tbUsuario");
			
		}
		 
		if(skip){
			 return "tabContratoResponsavel";
		}else{
			 return event.getNewStep();	 
		}
	   
	}
	
	public void onEditUsuer(CellEditEvent event) { }  
	
	public void selecionaDadosPlano() throws Exception{
		this.setPlano(servicePlano.getPlanosPorcodigo(responsavel.getPlano().getCodigo()));
	}
	
	public String gravarContrato() throws Exception{
		
		this.getResponsavel().getListaSalao().add(this.getSalao());
		serviceResponsavel.gravarContrato(this.getResponsavel());
		
		return UrlConstantes.RESULT_CAD_SALAO;
		
	}
	
	/**Metodos privados auxiliares**/
	
	private String validaProcesso(FlowEvent event, String mensagem, String tab) {
		if(mensagem != null){
			 MensagensUtil.error(mensagem);	
			 return tab;

		 }else{
			 return event.getNewStep();	
		 }
	}  
	
	private void geraUsuarios(String mensagem) throws Exception {
		
		if(mensagem == null){
			Integer codigoPlano = this.getResponsavel().getPlano().getCodigo();
			this.getSalao().setListaUsuarios(serviceUsuario.geraUsuariosParaPlanoSalao(this.getSalao(), codigoPlano));
		}
	}
	
	/**Gets and Sets**/
	
	public ResponsavelVO getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ResponsavelVO responsavel) {
		this.responsavel = responsavel;
	}
	
	public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    }

	public Map<String, Integer> getMapPlano() {
		return mapPlano;
	}

	public void setMapPlano(Map<String, Integer> mapPlano) {
		this.mapPlano = mapPlano;
	}

	public PlanoVO getPlano() {
		return plano;
	}

	public void setPlano(PlanoVO plano) {
		this.plano = plano;
	}

	public SalaoVO getSalao() {
		return salao;
	}

	public void setSalao(SalaoVO salao) {
		this.salao = salao;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}		
	
}
