package br.com.gestao.salao.service;

import java.util.List;

import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

public interface ServiceResponsavel {
	
	String validaCamposObrigatoriosFrameResponsavel(ResponsavelVO responsavel);
	
	String validaCamposObrigatoriosFramePlano(ResponsavelVO responsavel);
	
	String validaCamposObrigatoriosFrameSalao(SalaoVO salao);
	
	String validaCamposObrigatoriosFrameUsuario(List<UsuarioVO> listaUsuario);
	
	void gravarContrato(ResponsavelVO responsavel)throws Exception;
	
}
