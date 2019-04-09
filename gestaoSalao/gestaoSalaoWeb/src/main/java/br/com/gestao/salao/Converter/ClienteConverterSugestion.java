package br.com.gestao.salao.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.gestao.salao.service.ServiceCliente;
import br.com.gestao.salao.vo.ClienteVO;

@Component
@Scope("request")
public class ClienteConverterSugestion implements Converter{
	
	@Autowired
	private ServiceCliente serviceCliente;
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue){  
        
		try {
			return serviceCliente.pesquisaClientePorCodigo(Integer.parseInt(submittedValue));
			
		} catch (Exception e) {
			
			return null;
		}
		 
    }  
	
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        
		if (value != null && value instanceof ClienteVO) {
			ClienteVO cliente = (ClienteVO)value;
            
			if(cliente.getCodigo() != null)
				return cliente.getCodigo().toString();  
			else
				return null;
		}
		 
        return "";  
         
    }
	
}
