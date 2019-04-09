package br.com.gestao.salao.mobile.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagensUtil {
	
	public static void info(String msg){
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", msg));  
	}
	
	public static void warn(String msg) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"", msg));  
    }  
  
    public static void error(String msg) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", msg));  
    }  
  
    public static void fatalError(String msg) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"", msg));  
    }
    
    public static void errorGrowl(String msg) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "")); 
    }
    
    

}
