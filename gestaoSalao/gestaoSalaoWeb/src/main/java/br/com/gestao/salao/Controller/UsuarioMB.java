package br.com.gestao.salao.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.service.ServiceUsuario;
import br.com.gestao.salao.util.JsfUtil;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.util.MensagensUtil;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller
@Scope("request")
public class UsuarioMB{
	
	UsuarioVO usuario = new UsuarioVO();
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	@PostConstruct
	public void init() {
		
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	
	public void getAbrirSenha() throws Exception{
		this.setUsuario(MemoriaUsuario.getUsuarioMemoria());
		this.getUsuario().setSenha(null);
		this.getUsuario().setConfirmaSenha(null);
	}
	
	public void alterarSenha() throws Exception{
		
		this.setUsuario(MemoriaUsuario.getUsuarioMemoria());
		String mensagem = this.serviceUsuario.validaAlteraSenha(usuario);
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			this.serviceUsuario.alterarSenha(usuario);
			MensagensUtil.info(MensagensConstantes.SENHA_ALTERADA_SUCESSO);
			
		}
		
	}
	
	public void encerrarSessao(){
		
		FacesContext faces = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)faces.getExternalContext().getSession(false);
		session.invalidate();
		
		JsfUtil.retornaPaginaInicial();
		
	}
	
	
	public void login() throws Exception{
		
		usuario = serviceUsuario.login(usuario);
		
		if(usuario.getMensagem() != null){
			
			MensagensUtil.error(usuario.getMensagem());
			
		}else{
			
			MemoriaUsuario.guardaUsuarioMemoria(usuario);
			JsfUtil.retornaPaginaInicialInterna();
			
		}
		
	}


}
