package br.com.gestao.salao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.AgendaDao;
import br.com.gestao.salao.service.ServiceEncaixe;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.UsuarioVO;
 
@Service
@Transactional(readOnly=false)
public class ServiceEncaixeImpl  implements ServiceEncaixe{
	
	@Autowired
	private AgendaDao agendaDao;
	
	public List<AgendaVO> listaEncaixe(Date data, UsuarioVO usuario) throws Exception{
		return agendaDao.pesquisaAgendaEncaixe(data, usuario.getResponsavel().getCodigo(), usuario.getSalao().getCodigo(), RegraNegocioConstantes.ATENDIMENTO_ENCAIXE);
	}
	
	
	
}
