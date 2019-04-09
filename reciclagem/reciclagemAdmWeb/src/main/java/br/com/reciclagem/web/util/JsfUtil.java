package br.com.reciclagem.web.util;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	
	
	public static Map<String, Object> getSessionMap(){
		return getExternalContext().getSessionMap();
	}
	
	public static Object getObjectSessionMap(String valor){
		return getSessionMap().get(valor);
	}


}
