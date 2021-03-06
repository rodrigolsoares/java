package br.com.gestao.salao.mobile.util;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gestao.salao.Constants.UrlConstantes;

public class JsfUtil {
	
	public static FacesContext getCurrentInstance() {
		return FacesContext.getCurrentInstance();
	}
	
	public static ExternalContext getExternalContext() {
		return getCurrentInstance().getExternalContext();
	}
	
	public static void setSession(String nomeObj, Object objeto) {
		getExternalContext().getSessionMap().put(nomeObj, objeto);
	}
	
	public static HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}
	
	public static void retornaPaginaInicial(){
		
		getCurrentInstance().getApplication().getNavigationHandler()
		.handleNavigation(FacesContext.getCurrentInstance(), null, UrlConstantes.TELA_INDEX);
	}
	
	public static void retornaPaginaInicialMobile(){
		
		try {
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect( UrlConstantes.TELA_INICIAL_EXTERNO_MOBILE);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Map<String, Object> getSessionMap(){
		return getExternalContext().getSessionMap();
	}
	
	public static Object getObjectSessionMap(String valor){
		return getSessionMap().get(valor);
	}


}
