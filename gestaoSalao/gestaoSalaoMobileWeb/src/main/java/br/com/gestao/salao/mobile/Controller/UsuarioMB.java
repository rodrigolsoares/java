package br.com.gestao.salao.mobile.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.UrlConstantes;
import br.com.gestao.salao.Constants.UrlConstantesMobile;
import br.com.gestao.salao.mobile.util.MemoriaUsuario;
import br.com.gestao.salao.mobile.util.MensagensUtil;
import br.com.gestao.salao.service.ServiceUsuario;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller("usuarioMobileMB")
@Scope("view")
public class UsuarioMB {
	
	UsuarioVO usuario;
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	@PostConstruct
	public void init() {
		usuario = new UsuarioVO();
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
		//String mensagem = this.serviceUsuario.validaAlteraSenha(usuario);
		
		/*if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			this.serviceUsuario.alterarSenha(usuario);
			MensagensUtil.info(MensagensConstantes.SENHA_ALTERADA_SUCESSO);
			
		}*/
		
	}
	
	public String encerrarSessao(){
		
		FacesContext faces = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)faces.getExternalContext().getSession(false);
		session.invalidate();
		
		return UrlConstantes.TELA_INDEX;
		
	}
	
	public String login() throws Exception {
		
		usuario.setLogin(usuario.getLogin().replace("\t", ""));
		usuario = serviceUsuario.login(usuario);
		
		if(usuario.getMensagem() != null){
			
			MensagensUtil.error(usuario.getMensagem());
			return null;
			
		}else{
			
			MemoriaUsuario.guardaUsuarioMemoria(usuario);
			return UrlConstantesMobile.TELA_INICIAL_INTERNO;
			
		}
		
	}

}
