package br.com.gestao.salao.Controller;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.service.ServiceCadastroRapido;
import br.com.gestao.salao.service.ServiceEmail;
import br.com.gestao.salao.service.ServiceResponsavel;
import br.com.gestao.salao.service.ServiceUsuario;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.util.MensagensUtil;
import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.TipoCobrancaVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller
@Scope("view")
public class SalaoCadRapidoMB {
	
	
	//static Logger log = Logger.getLogger(MainApp.class.getName());

	@Autowired
	private ServiceCadastroRapido serviceCadRapido;
	
	@Autowired
	private ServiceResponsavel serviceResponsavel;
	
	@Autowired
	private ServiceEmail serviceEmail;
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	private ResponsavelVO responsavel;
	private SalaoVO salao;
	private UsuarioVO usuario;

	
	@PostConstruct
	public void init() throws Exception{
		
		usuario = MemoriaUsuario.getUsuarioMemoria();
		responsavel = new ResponsavelVO();
		salao = new SalaoVO();
		responsavel.getPlano().setCodigo(3);
	}
	
	
	public void gravarContrato() throws Exception{
		
		String mensagem = serviceCadRapido.validaCamposObrigatoriosCadRapido(this.getResponsavel(), this.getSalao());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);
			
		}else{
			
			TipoCobrancaVO tipo = new TipoCobrancaVO();
			tipo.setCodigo(2);
			this.getSalao().setRazaoSocial(this.getSalao().getNomeFantasia());
			
			this.getResponsavel().setTipoCobranca(tipo);
			this.getResponsavel().getListaSalao().add(this.getSalao());
			this.getSalao().setListaUsuarios(serviceUsuario.geraUsuariosParaPlanoSalao(this.getSalao(), 3));
			
			serviceResponsavel.gravarContrato(this.getResponsavel());
			serviceEmail.enviaEmailContrato(this.getResponsavel(), this.getSalao().getListaUsuarios());
			MensagensUtil.info(MensagensConstantes.CADASTRO_CONTRATO_SUCESSO);

			
		}
	}

	public ResponsavelVO getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(ResponsavelVO responsavel) {
		this.responsavel = responsavel;
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
