package br.com.gestao.salao.service;

import java.util.Date;

import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.UsuarioVO;


public interface ServiceAgendamento {
	
	String validaCamposObrigatorios(AgendaVO agenda);
	
	void inseriAgendamento(AgendaVO agenda, Date dataSelecionada, UsuarioVO usuario) throws Exception;
	
	void inseriAgendamentoAndroid(AgendaVO agenda, Date dataSelecionada) throws Exception;
	
	void alterarAgendamento(AgendaVO agenda, Date dataSelecionada, UsuarioVO usuario) throws Exception;
	
	void excluirAgendamento(AgendaVO agenda) throws Exception;
	
	AgendaVO getAgendamentoPorCodigo(AgendaVO agenda) throws Exception;
	
	Double calculaTotalServico (AgendaVO agenda, UsuarioVO usuario) throws Exception;
	
}
