package br.com.gestao.salao.dao;

import java.util.Date;
import java.util.List;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.UsuarioVO;


public interface FluxoCaixaDao {
	
	List<AgendaVO> pesquisaAgendaServico(Date dataInicio, Date dataFinal, int fk_status,  UsuarioVO usuario ) throws Exception;
	
	List<AgendaVO> verificaQtdeAjudante(Date dataInicio, Date dataFinal, UsuarioVO usuario) throws Exception;
	
	List<AgendaVO> pesquisaComissaoServicoPrincipal(Date dataInicio, Date dataFinal, UsuarioVO usuario  ) throws Exception;
	
	List<AgendaVO> pesquisaComissaoServicoAjudante(Date dataInicio, Date dataFinal, UsuarioVO usuario  ) throws Exception;
	
	List<AgendaVO> pesquisaComissaoServicoAjudantePorFunc(Date dataInicio, Date dataFinal,  Integer codigo, UsuarioVO usuario   ) throws Exception;
	
	List<AgendaVO> pesquisaChequesCompensar(Date dataInicio, Date dataFinal, UsuarioVO usuario  ) throws Exception;
}
