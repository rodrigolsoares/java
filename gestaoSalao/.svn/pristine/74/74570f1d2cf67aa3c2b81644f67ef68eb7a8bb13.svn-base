package br.com.gestao.salao.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.gestao.salao.service.ServiceFuncionario;
import br.com.gestao.salao.vo.FuncionarioVO;

@Component
@Scope("request")
public class FuncionarioConverterSugestion implements Converter{
	
	@Autowired
	private ServiceFuncionario serviceFunc;
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue){  
        
		try {
			return serviceFunc.pesquisaFuncionariobyCodigo(Integer.parseInt(submittedValue));
			
		} catch (Exception e) {
			
			return null;
		}
		 
    }  
	
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        
		if (value != null && value instanceof FuncionarioVO) {
			FuncionarioVO funcionario = (FuncionarioVO)value;
            
			if (funcionario.getCodigo() != null)
				return funcionario.getCodigo().toString();
			else
				return null;
		}
		 
        return "";  
         
    }
	
}
