package br.com.gestao.salao.mobile.Controller;

import javax.annotation.PostConstruct;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.mobile.util.MensagensUtil;
import br.com.gestao.salao.service.ServiceEmail;
import br.com.gestao.salao.vo.EmailVO;

@Controller("emailMobileMB")
@Scope("view")
public class EmailMB {
	
	@Autowired
	private ServiceEmail serviceEmail;
	
	private EmailVO emailVo;
	
	@PostConstruct
	public void init(){
		emailVo = new EmailVO();
	}
	
	public void enviar() throws EmailException{
		
		String mensagem = serviceEmail.validaCamposObrigatorios(this.getEmailVo());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			serviceEmail.enviaEmailAssociado(emailVo);
			this.limpar();
			MensagensUtil.info(MensagensConstantes.EMAIL_ENVIADO_SUCESSO);
			
		}
	}
	
	private void limpar(){
		emailVo = new EmailVO();
	}

	public EmailVO getEmailVo() {
		return emailVo;
	}

	public void setEmailVo(EmailVO emailVo) {
		this.emailVo = emailVo;
	}
	
	
	
}
