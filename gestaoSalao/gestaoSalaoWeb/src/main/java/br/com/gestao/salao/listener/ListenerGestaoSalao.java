package br.com.gestao.salao.listener;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.gestao.salao.util.JsfUtil;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.vo.UsuarioVO;

public class ListenerGestaoSalao implements PhaseListener{
	
	private static final long serialVersionUID = 1L;
	
	
	public void afterPhase(PhaseEvent event) {
	}
	
	
	public void beforePhase(PhaseEvent event) {
		
		
        if(event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
        	
        	String viewId = event.getFacesContext().getViewRoot().getViewId();
    		
    		Map requestHeaders = event.getFacesContext().getExternalContext().getRequestHeaderMap();
    		String navegador = requestHeaders.get("user-agent").toString();
    		
        	if(isMobile(navegador)){
        		JsfUtil.retornaPaginaInicialMobile();
    		} else{
    			redirecionamentoWeb(viewId);
    		}
    		
        	
        	
        	
        }
	}

	private void redirecionamentoWeb(String viewId) {
		
		if(!viewId.equals("/pages/login.xhtml") && 
		   !viewId.equals("/pages/cadSalao.xhtml") &&
		   !viewId.equals("/pages/cadRapidoSalao.xhtml") &&
		   !viewId.equals("/pages/portalInicio.xhtml") && 
		   !viewId.equals("/pages/resultadoCadSalao.xhtml")	&&
		   !viewId.equals("/pages/planoBasico.xhtml")	&&	
		   !viewId.equals("/pages/planoIntermediario.xhtml")	&&	
		   !viewId.equals("/pages/planoAvancado.xhtml")	&&
		   !viewId.equals("/pages/expAndroid.xhtml")	&&
		   !viewId.equals("/pages/contato.xhtml") ){
			
			UsuarioVO usuario = MemoriaUsuario.getUsuarioMemoria();
					
			if(usuario == null || usuario.getLogin() == null ){
				JsfUtil.retornaPaginaInicial();
			}
			
		}
	}
	
	private boolean isMobile(String pValor){
		
		String valor = pValor.toLowerCase();
		
		Pattern pattern = Pattern.compile("(iphone|ipad|ipod|android|blackBerry|opera Mobi|opera Mini|iemobile)");  
	    Matcher matcher = pattern.matcher(valor);  
	    
	    if(matcher.find()){
	    	return true;
	    }else{
	    	return false;
	    }
	    	
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
	
}
