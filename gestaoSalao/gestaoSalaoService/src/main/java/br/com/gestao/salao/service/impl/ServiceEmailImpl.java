package br.com.gestao.salao.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.service.ServiceEmail;
import br.com.gestao.salao.util.ValidarDeCampos;
import br.com.gestao.salao.vo.EmailVO;
import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceEmailImpl implements  ServiceEmail {
	
	public String validaCamposObrigatorios(EmailVO emailVO){
		String retorno = "";
		
		if(StringUtils.isEmpty(emailVO.getNome())){
			retorno += "Digite o nome" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.email(emailVO.getDistinatario())){
			retorno +=  "E-mail digitado é inválido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(emailVO.getCabecalho())){
			retorno +=  "Digite o cabeçalho" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(emailVO.getMotivo())){
			retorno +=  "Selecione o motivo" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(emailVO.getMensagem())){
			retorno +=  "Digite a mensagem" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public void enviaEmailAssociado(EmailVO emailVO) throws EmailException{
		this.enviaEmail(emailVO);
	}
	
	@SuppressWarnings("deprecation")
	private void enviaEmail(EmailVO emailVO) throws EmailException {

		SimpleEmail email = new SimpleEmail();

		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		
		email.setFrom("rodrigolsoares@gmail.com", "Gestão Salão");
		
		email.addTo(emailVO.getDistinatario(), emailVO.getNome());
		email.setSubject(emailVO.getCabecalho());
		email.setContent(emailVO.getMensagem(), "text/html");
		email.setSSL(true);
		email.setAuthentication("rodrigolsoares", "cristo@jesus");
		email.send();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void enviaEmailContrato(ResponsavelVO responsavel, List<UsuarioVO> listaUsuario) throws EmailException {

		SimpleEmail email = new SimpleEmail();

		email.setHostName("smtp.portaldosalao.com.br");
		email.setSmtpPort(587);
		
		email.setFrom("portaldosalao@portaldosalao.com.br", "Gestão Salão");
		
		email.addTo(responsavel.getListaSalao().get(0).getEmail(), responsavel.getNome());
		email.setSubject("Bem vindo ao Portal do salão");
		
		email.setContent(mensagemContrato(responsavel, listaUsuario), "text/html");
		
		email.setSSL(true);
		email.setAuthentication("portaldosalao@portaldosalao.com.br", "jgrasp@182");
		email.send();
		
	}
	
	private String mensagemContrato(ResponsavelVO responsavel, List<UsuarioVO> listaUsuario){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Seja bem vindo Sr(a)" +  responsavel.getNome());
		sb.append("<br /><br />");
		sb.append("Salão Cadastrado para utilizar o sistema: " +  responsavel.getListaSalao().get(0).getNomeFantasia());
		sb.append("<br /><br />");
		
		sb.append("Lista de Usuários do sistema");
		sb.append("<br />");
		for(UsuarioVO usuario : listaUsuario){
			sb.append("Login: " +  usuario.getLogin()  + " Senha: 000");
			sb.append("<br />");
		}
		
		sb.append("<br />");
		sb.append("OBS: Recomendamos que seja alterada a senha dos usuários do sistema");
		
		
		
		return sb.toString();
		
	}

}