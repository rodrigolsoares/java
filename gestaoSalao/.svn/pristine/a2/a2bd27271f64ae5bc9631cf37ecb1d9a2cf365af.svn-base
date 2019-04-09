package br.com.gestao.salao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.AgendaDao;
import br.com.gestao.salao.service.ServiceAgendamento;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class ServiceAgendamentoImpl  implements ServiceAgendamento{
	
	@Autowired
	private AgendaDao agendaDao;
	
	
	public String validaCamposObrigatorios(AgendaVO agenda) {
		
		String retorno = "";
		
		if(agenda.getCodigoCliente() == null){ 
			retorno += "Erro, selecione o nome " + Constantes.PULA_LINHA_XHTML;
		}
		
		if(agenda.getListaServicoSelecionado().size() == 0 ){
			retorno += "Erro, selecione o(s) serviço(s)" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(agenda.getCodigoAtendimento() == 0){
			retorno += "Erro, selecione o status do atendimento" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(agenda.getCodigoPagamento() == 0){
			retorno += "Erro, selecione a forma de pagamento" + Constantes.PULA_LINHA_XHTML;
		} else{
			
			if(agenda.getCodigoPagamento() == 4 && agenda.getDataCompensaCheque() == null){
				
				retorno += "Erro, selecione a data de compensação do cheque" + Constantes.PULA_LINHA_XHTML;
			}
		}
		
		
	
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	
	
	public void inseriAgendamento(AgendaVO agenda, Date dataSelecionada, UsuarioVO usuario) throws Exception{
		
		if(dataSelecionada == null) {
			agenda.setData(new Date());
		}else{
			agenda.setData(dataSelecionada);
		}
		
		Integer codigoAgenda = this.agendaDao.inserirAgenda(agenda, usuario);
		agenda.setCodigo(codigoAgenda);
		
		for(String codigoServico : agenda.getListaServicoSelecionado()){
			this.agendaDao.inseriServico(codigoServico, agenda.getCodigo(), usuario);
		}
		
		for(String codigoAju : agenda.getListaAjudanteSelecionado()){
			
			List<FuncionarioVO> listaFunc = agendaDao.pesquisaFuncionarioAgenda(usuario.getResponsavel().getCodigo(), usuario.getSalao().getCodigo(), Integer.parseInt(codigoAju));
			Integer porcentagem = listaFunc.get(0).getPorcentagem();
			this.agendaDao.inseriAjudante(codigoAju, porcentagem, agenda, usuario);
		}
	}
	
	public void inseriAgendamentoAndroid(AgendaVO agenda, Date dataSelecionada) throws Exception{
		
		
		Integer codigoAgenda = this.agendaDao.inserirAgenda(agenda, agenda.getUsuarioAndroid());
		agenda.setCodigo(codigoAgenda);
		
		for(String codigoServico : agenda.getListaServicoSelecionado()){
			this.agendaDao.inseriServico(codigoServico, agenda.getCodigo(), agenda.getUsuarioAndroid());
		}
		
	
	}
	
	
	public void alterarAgendamento(AgendaVO agenda, Date dataSelecionada, UsuarioVO usuario) throws Exception{
		
		if(dataSelecionada == null) {
			agenda.setData(new Date());
		}else{
			agenda.setData(dataSelecionada);
		}
		
		this.agendaDao.excluirRegistrosAgendaServico(agenda);
		this.agendaDao.excluirRegistrosAjudante(agenda);
		
		this.agendaDao.atualizaAgenda(agenda, usuario);
		
		for(String codigoServico : agenda.getListaServicoSelecionado()){
			this.agendaDao.inseriServico(codigoServico, agenda.getCodigo(), usuario);
		}
		
		for(String codigoAju : agenda.getListaAjudanteSelecionado()){
			Integer porcentagem = 0;
			this.agendaDao.inseriAjudante(codigoAju, porcentagem, agenda, usuario);
		}
	}
	
	public void excluirAgendamento(AgendaVO agenda) throws Exception{
		
		this.agendaDao.excluirRegistrosAgendaServico(agenda);
		this.agendaDao.excluirRegistrosAjudante(agenda);
		this.agendaDao.excluirRegistroAgenda(agenda);
		
	}
	
	public AgendaVO getAgendamentoPorCodigo(AgendaVO agenda) throws Exception{
		
		AgendaVO novaAgenda = agenda;
		
		if(agenda.getCodigo() != null && agenda.getCodigo().intValue() != 0) {
			novaAgenda = this.agendaDao.pesquisaAgendaPorCodigo(agenda.getCodigo());
			novaAgenda.setPorcentagem(agenda.getPorcentagem());
			novaAgenda.setListaAjudanteSelecionado(this.agendaDao.pesquisaAjudanteAgendaPorCodigo(agenda.getCodigo()));
			novaAgenda.setListaServicoSelecionado(this.agendaDao.pesquisaServicoAgendaPorCodigo(agenda.getCodigo()));
			//agenda.setTipoRateio(this.agendaDao.pesquisaRateioPorCodigo(codigoAgenda));
		}
		
		return novaAgenda;	
	}
	
	public Double calculaTotalServico (AgendaVO agenda, UsuarioVO usuario) throws Exception {
		
		if(agenda.getDesconto() != null){
			return agenda.getValor() - agenda.getDesconto();
		} else{
			return agenda.getValor();
		}
		
	}

}
