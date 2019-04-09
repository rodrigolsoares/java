package br.com.gestao.salao.service;

import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.gestao.salao.vo.EmailVO;
import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceEmail {
	
	void enviaEmailAssociado(EmailVO emailVO) throws EmailException;
	
	String validaCamposObrigatorios(EmailVO emailVO);
	
	void enviaEmailContrato(ResponsavelVO responsavel, List<UsuarioVO> listaUsuario) throws EmailException ;
	
}
