package br.com.gestao.salao.service;

import java.util.Date;
import java.util.List;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.UsuarioVO;


public interface ServiceEncaixe {
	

	List<AgendaVO> listaEncaixe(Date data, UsuarioVO usuario) throws Exception;
	
}
