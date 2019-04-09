package br.com.gestao.salao.dao;

import java.util.Date;
import java.util.List;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;


public interface AgendaDao {
	
	Integer inserirAgenda(AgendaVO agenda, UsuarioVO usuario) throws Exception;
	
	void atualizaAgenda(AgendaVO agenda, UsuarioVO usuario) throws Exception;
	
	void excluirRegistroAgenda(AgendaVO agenda) throws Exception;
	
	void excluirRegistrosAgendaServico(AgendaVO agenda) throws Exception;
	
	void excluirRegistrosAjudante(AgendaVO agenda) throws Exception;
	
	void inseriServico(String codigoServico, int codigoAgenda, UsuarioVO usuario) throws Exception;
	
	void inseriAjudante(String codigoAjudante, Integer porcentagem, AgendaVO ag, UsuarioVO usuario) throws Exception;
	
	List<FuncionarioVO> pesquisaAgenda(Date sData, Integer codigoResponsavel, Integer codigoSalao, Integer tipoAtendimento , Integer codigoFuncionario) throws Exception;
	
	List<FuncionarioVO> pesquisaAgendaAjudante(Date sData, Integer codigoResponsavel, Integer codigoSalao, Integer tipoAtendimento,  Integer codigoFuncionario) throws Exception;
	
	AgendaVO pesquisaAgendaPorCodigo(Integer codigo) throws Exception;
	
	List<String> pesquisaServicoAgendaPorCodigo(Integer codigoAgenda) throws Exception;
	
	List<String> pesquisaAjudanteAgendaPorCodigo(Integer codigoAgenda) throws Exception;
	
	Integer pesquisaRateioPorCodigo(Integer codigoAgenda) throws Exception;
	
	List<FuncionarioVO> pesquisaFuncionarioAgenda(Integer codigoResponsavel, Integer codigoSalao, Integer codigoFuncionario) throws Exception;
	
	List<AgendaVO> pesquisaAgendaEncaixe(Date sData, Integer codigoResponsavel, Integer codigoSalao, Integer tipoAtendimento) throws Exception;
}
