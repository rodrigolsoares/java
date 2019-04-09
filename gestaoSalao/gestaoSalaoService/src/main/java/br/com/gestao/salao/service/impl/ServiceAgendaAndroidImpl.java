package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.AgendaDao;
import br.com.gestao.salao.service.ServiceAgendaAndoid;
import br.com.gestao.salao.util.PreparaColecaoAgenda;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.HorarioVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;
 
@Service
@Transactional(readOnly=false)
public class ServiceAgendaAndroidImpl  extends ServiceAgendaPaiImpl implements ServiceAgendaAndoid{
	
	@Autowired
	private AgendaDao agendaDao;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HorarioVO> montaHorariosAgendados(Date data, SalaoVO salao, FuncionarioVO funcionario) throws Exception{
		
		Map<Integer,FuncionarioVO> retorno = new HashMap<Integer,FuncionarioVO>();
		
		//Realiza a pesquisa na base de dados
		retorno = this.pesquisaAgenda(data, salao, funcionario);
		this.pesquisaFuncionarioAgenda(retorno, salao, funcionario);
		this.pesquisaAgendaAjudante(data, retorno, salao, funcionario);
		
		//varrendo todos os funcionários
		for (Integer chave : retorno.keySet()){

			FuncionarioVO func = (FuncionarioVO) retorno.get(chave);
			func.setListaAgenda(this.montaAgendaFunc(data, func));
			
			//Atualias informações no HashMap
			retorno.put(func.getCodigo(),func);
		}
		
		List<FuncionarioVO> lista = new ArrayList(retorno.values());
		List<HorarioVO> listaHorario = new ArrayList<HorarioVO>();
		
		for(AgendaVO agenda: lista.get(0).getListaAgenda()) {
			
			if(agenda.getCodigo() == 0){
				HorarioVO horario = new HorarioVO();
				horario.setHora(agenda.getHora());
				listaHorario.add(horario);
			}
		}
		
		return listaHorario;
	}
	
	
	public void pesquisaAgendaAjudante(Date sData, Map<Integer,FuncionarioVO> retornoHash, SalaoVO salao, FuncionarioVO funcionario) throws Exception {
		
		List<FuncionarioVO> lista = agendaDao.pesquisaAgendaAjudante(sData, salao.getResponsavel().getCodigo(), salao.getCodigo(), RegraNegocioConstantes.ATENDIMENTO_AGENDAMENTO, funcionario.getCodigo());
		montaMapAgendaFunc(lista, retornoHash);
		
		for(Integer chave : retornoHash.keySet()){
			FuncionarioVO func = (FuncionarioVO) retornoHash.get(chave);
			ArrayList<AgendaVO> listaAgenda = new ArrayList<AgendaVO>(func.getHashAgenda().values());
			func.setListaAgenda(PreparaColecaoAgenda.montaServicoAjudante(listaAgenda));
		}

	}
	
	public Map<Integer,FuncionarioVO> pesquisaAgenda(Date sData, SalaoVO salao, FuncionarioVO funcionario) throws Exception{
		
		List<FuncionarioVO> lista = agendaDao.pesquisaAgenda(sData, salao.getResponsavel().getCodigo(), salao.getCodigo(), RegraNegocioConstantes.ATENDIMENTO_AGENDAMENTO, funcionario.getCodigo());
		Map<Integer,FuncionarioVO> retornoHash = new HashMap<Integer, FuncionarioVO>();
		montaMapAgendaFunc(lista, retornoHash);
		
		return retornoHash;
	}
	
	
	public AgendaVO pesquisaRateioPorCodigo(AgendaVO ag, UsuarioVO usuario) throws Exception {
		ag.setTipoRateio(agendaDao.pesquisaRateioPorCodigo(ag.getCodigo()));
		return ag;
	}
	
	
	public void pesquisaFuncionarioAgenda(Map<Integer,FuncionarioVO> retornoHash, SalaoVO salao, FuncionarioVO funcionario) throws Exception{
		
		List<FuncionarioVO> lista = agendaDao.pesquisaFuncionarioAgenda(salao.getResponsavel().getCodigo(), salao.getCodigo(), funcionario.getCodigo());
		
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
