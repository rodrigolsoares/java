package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.AgendaDao;
import br.com.gestao.salao.service.ServiceAgenda;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.util.PreparaColecaoAgenda;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.HorarioVO;
import br.com.gestao.salao.vo.UsuarioVO;
 
@Service
@Transactional(readOnly=false)
public class ServiceAgendaImpl  extends ServiceAgendaPaiImpl implements ServiceAgenda{
	
	@Autowired
	private AgendaDao agendaDao;
	
	public List<HorarioVO> montaHorario()throws Exception{
		
		Calendar calDataInicial = DataUtil.getCalendarPopulado(8,30);
		Calendar calDataFinal = DataUtil.getCalendarPopulado(20,00);
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(calDataInicial.getTime());

		List<HorarioVO> listaHorarios = new ArrayList<HorarioVO>();
		
		while(!DataUtil.formatHora(gc.getTime()).equals(DataUtil.formatHora(calDataFinal.getTime()))){   
			gc.add(Calendar.MINUTE,30);
			gc.add(Calendar.AM_PM, 0);
			HorarioVO hora = new HorarioVO();
			hora.setHora(DataUtil.formatHora(gc.getTime()));
			listaHorarios.add(hora);
		}
		
		return listaHorarios;
				
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FuncionarioVO> montaHorariosAgendados(Date data, UsuarioVO usuario) throws Exception{
		
		Map<Integer,FuncionarioVO> retorno = new HashMap<Integer,FuncionarioVO>();
		
		//Realiza a pesquisa na base de dados
		retorno = this.pesquisaAgenda(data, usuario);
		this.pesquisaFuncionarioAgenda(retorno, usuario);
		this.pesquisaAgendaAjudante(data, retorno, usuario);
		
		//varrendo todos os funcionários
		for (Integer chave : retorno.keySet()){

			FuncionarioVO func = (FuncionarioVO) retorno.get(chave);
			func.setListaAgenda(this.montaAgendaFunc(data, func));
			
			//Atualias informações no HashMap
			retorno.put(func.getCodigo(),func);
		}
		
		return new ArrayList(retorno.values());
	}
	
	
	public void pesquisaAgendaAjudante(Date sData, Map<Integer,FuncionarioVO> retornoHash, UsuarioVO usuario) throws Exception {
		
		List<FuncionarioVO> lista = agendaDao.pesquisaAgendaAjudante(sData, usuario.getResponsavel().getCodigo(), usuario.getSalao().getCodigo(), RegraNegocioConstantes.ATENDIMENTO_AGENDAMENTO, null);
		montaMapAgendaFunc(lista, retornoHash);
		
		for(Integer chave : retornoHash.keySet()){
			FuncionarioVO func = (FuncionarioVO) retornoHash.get(chave);
			ArrayList<AgendaVO> listaAgenda = new ArrayList<AgendaVO>(func.getHashAgenda().values());
			func.setListaAgenda(PreparaColecaoAgenda.montaServicoAjudante(listaAgenda));
		}

	}
	
	public Map<Integer,FuncionarioVO> pesquisaAgenda(Date sData, UsuarioVO usuario) throws Exception{
		
		List<FuncionarioVO> lista = agendaDao.pesquisaAgenda(sData, usuario.getResponsavel().getCodigo(), usuario.getSalao().getCodigo(), RegraNegocioConstantes.ATENDIMENTO_AGENDAMENTO, null);
		Map<Integer,FuncionarioVO> retornoHash = new HashMap<Integer, FuncionarioVO>();
		montaMapAgendaFunc(lista, retornoHash);
		
		return retornoHash;
	}
	
	
	public AgendaVO pesquisaRateioPorCodigo(AgendaVO ag, UsuarioVO usuario) throws Exception {
		ag.setTipoRateio(agendaDao.pesquisaRateioPorCodigo(ag.getCodigo()));
		return ag;
	}
	
	
	public void pesquisaFuncionarioAgenda(Map<Integer,FuncionarioVO> retornoHash, UsuarioVO usuario) throws Exception{
		
		List<FuncionarioVO> lista = agendaDao.pesquisaFuncionarioAgenda(usuario.getResponsavel().getCodigo(), usuario.getSalao().getCodigo(), null);
		
		for(FuncionarioVO func : lista){
			
			if(retornoHash.containsKey(func.getCodigo())){}
			else {
				retornoHash.put(func.getCodigo(),func);
			}	
		}
	}
	
	public void montaMapAgendaFunc(List<FuncionarioVO> lista, Map<Integer,FuncionarioVO> retornoHash){
		
		Map<Integer,AgendaVO> hashAgenda = new HashMap<Integer,AgendaVO>();
		
		for(FuncionarioVO func : lista){
			
			AgendaVO agenda = func.getAgenda();
			
			if(retornoHash.containsKey(func.getCodigo())){
				func = (FuncionarioVO) retornoHash.get(func.getCodigo());
				hashAgenda =  func.getHashAgenda();
				
				if(hashAgenda.containsKey(agenda.getCodigo())){
					agenda = (AgendaVO)hashAgenda.get(agenda.getCodigo());
					
					/*if (!agenda.getArrayServicos().contains(agenda.getSigla()))
						agenda.setArrayServicos(agenda.getSigla());*/
				}
				
			}else{

				if (hashAgenda != null) hashAgenda = new HashMap<Integer,AgendaVO>();
				//agenda.setArrayServicos(agenda.getSigla());
			}
			
			hashAgenda.put( agenda.getCodigo(),agenda);
			func.setHashAgenda(hashAgenda);
			retornoHash.put(func.getCodigo(),func);
			
		}

	}
	
	
	

}
