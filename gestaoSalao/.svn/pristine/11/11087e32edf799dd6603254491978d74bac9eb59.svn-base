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
import br.com.gestao.salao.dao.ClienteDao;
import br.com.gestao.salao.dao.DespesaDao;
import br.com.gestao.salao.dao.FluxoCaixaDao;
import br.com.gestao.salao.dao.FuncionarioDao;
import br.com.gestao.salao.dao.RateioDao;
import br.com.gestao.salao.service.ServiceFluxoCaixa;
import br.com.gestao.salao.util.PreparaColecaoAgenda;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.DespesaVO;
import br.com.gestao.salao.vo.FluxoCaixaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.RateioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceFluxoCaixaImpl  implements ServiceFluxoCaixa{
	
	@Autowired
	private FluxoCaixaDao fluxoCaixaDao; 
	
	@Autowired
	private RateioDao rateioDao;
	
	@Autowired
	private DespesaDao despesaDao;
	
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	public FluxoCaixaVO getFluxoDeCaixa(final FluxoCaixaVO fluxoCaixaOld, UsuarioVO usuario) throws Exception{
		
		FluxoCaixaVO fluxoCaixa = fluxoCaixaOld;
		Map<Integer, AgendaVO> mapComissao = new HashMap<Integer, AgendaVO>();
		
		DespesaVO desp = new DespesaVO();
		desp.setDataPagamento(fluxoCaixa.getDataInicial());
		desp.setDataPagamentoFim(fluxoCaixa.getDataFinal());
		
		fluxoCaixa.setListaDespesa(despesaDao.pesquisaDespesa(desp, usuario));
		fluxoCaixa.setListaServico(this.pesquisaAgendaServico(fluxoCaixa.getDataInicial(), fluxoCaixa.getDataFinal(), RegraNegocioConstantes.SERVICO_DIFERENTE_DE_STATUS_PENDENTE, usuario ));
		fluxoCaixa.setListaPendencia(this.pesquisaAgendaServico(fluxoCaixa.getDataInicial(), fluxoCaixa.getDataFinal(), RegraNegocioConstantes.SERVICO_STATUS_PENDENTE, usuario ));
		mapComissao = this.pesquisaComissaoServicoPrincipal(fluxoCaixa.getDataInicial(), fluxoCaixa.getDataFinal(), usuario);
		fluxoCaixa.setListaComissao(this.pesquisaComissaoServicoAjudante(fluxoCaixa.getDataInicial(), fluxoCaixa.getDataFinal() ,mapComissao, usuario));
		fluxoCaixa.setListaCheques(fluxoCaixaDao.pesquisaChequesCompensar(fluxoCaixa.getDataInicial(), fluxoCaixa.getDataFinal(), usuario));
		
		fluxoCaixa.setListaComissao(new ArrayList<AgendaVO>(mapComissao.values()));
		
		fluxoCaixa.setDespesaTotal(calculaTotalDespesa(fluxoCaixa.getListaDespesa()));
		fluxoCaixa.setServicoTotal(calculaTotalServico(fluxoCaixa.getListaServico()));
		fluxoCaixa.setComissaoTotal(calculaTotalComissao(fluxoCaixa.getListaComissao()));
		fluxoCaixa.setLucro(calculaLucro(fluxoCaixa));
				
		return fluxoCaixa;
		
	}
	
	public FluxoCaixaVO getFluxoDeCaixaByFuncionario(final FuncionarioVO funcOld, UsuarioVO usuario) throws Exception{
		
		FuncionarioVO func = funcOld;
		FluxoCaixaVO fluxoCaixa = new FluxoCaixaVO();
		
		List<AgendaVO> listaFuncionario =  funcionarioDao.pesquisaHistoricoServicoFuncionario(func, usuario, true);
		List<AgendaVO> listaAg = this.pesquisaComissaoServicoPrincipalPorFunc(listaFuncionario, func.getDataInicial(), func.getDataFinal(), usuario);
		List<AgendaVO> listaComissaoPronta = this.pesquisaComissaoServicoAjudantePorFunc(func.getDataInicial(), func.getDataFinal(),listaAg, func.getCodigo(), usuario);
	
		fluxoCaixa.setListaComissao(listaComissaoPronta);
		
		fluxoCaixa.setServicoTotal(calculaTotalServico(listaComissaoPronta));
		fluxoCaixa.setComissaoTotal(calculaTotalComissao(listaComissaoPronta));
		fluxoCaixa.setCaixinhaTotal(calculaTotalCaixinha(listaComissaoPronta));
		fluxoCaixa.setTotalPagaFuncionario(fluxoCaixa.getCaixinhaTotal()+ fluxoCaixa.getComissaoTotal());

				
		return fluxoCaixa;
		
	}
	
	public FluxoCaixaVO getFluxoDeCaixaByCliente(final ClienteVO cliOld, UsuarioVO usuario) throws Exception{
		
		ClienteVO cliente = cliOld;
		FluxoCaixaVO fluxoCaixa = new FluxoCaixaVO();	
		Map<Integer ,AgendaVO> mapHistorico = new HashMap<Integer, AgendaVO>();
		
		for (AgendaVO agenda: clienteDao.pesquisaHistoricoServicoCliente(cliente, usuario, true)){

			String servicos = agenda.getNomeServico();
			
			if(mapHistorico.containsKey(agenda.getCodigo())){
				agenda = mapHistorico.get(agenda.getCodigo());
				agenda.setServicos( agenda.getServicos()+  " - " + servicos);
			}else{
				agenda.setServicos(servicos);
			}
					
			mapHistorico.put(agenda.getCodigo(), agenda);
			
			
		}
		
		fluxoCaixa.setListaServico(new ArrayList<AgendaVO>(mapHistorico.values()));
		fluxoCaixa.setServicoTotal(calculaTotalServico(new ArrayList<AgendaVO>(mapHistorico.values())));
			
		return fluxoCaixa;
		
	}
	
	public List<AgendaVO> pesquisaComissaoServicoAjudantePorFunc(Date dataInicio, Date dataFinal, List<AgendaVO> pLista, Integer codigo, UsuarioVO usuario  ) throws Exception{
		
		Double fcalculo = 0d;
		HashMap<Integer, AgendaVO> retornoHash = new HashMap<Integer, AgendaVO>();
	
		Map<Integer,AgendaVO> hashQtdeAjudante = this.MapQtdeAjudante(dataInicio, dataFinal, usuario);
		RateioVO rateio = rateioDao.pesquisaRateio(usuario);
		List<AgendaVO> listaAgenda = fluxoCaixaDao.pesquisaComissaoServicoAjudantePorFunc(dataInicio, dataFinal, codigo, usuario);
			
		for(AgendaVO agenda : listaAgenda){
			
			String servico = agenda.getNomeServico();
			agenda.setPorcentagem( agenda.getPorcentagem());
			
			if(retornoHash.containsKey(agenda.getCodigo())) {
				agenda = (AgendaVO)retornoHash.get(agenda.getCodigo());
				agenda.getListaServicoSelecionado().add(servico);
			}else{	
				
				fcalculo = realizaCalculoAjudante(agenda, hashQtdeAjudante, rateio.getValor());
				agenda.setValorComissao(fcalculo);
				agenda.setValor(0d);
				agenda.setDesconto(0d);
				agenda.setValorTotal(0d);
				agenda.getListaServicoSelecionado().add(servico);
			}
			
			retornoHash.put(agenda.getCodigo(),agenda);
			
		}
			
		pLista.addAll(PreparaColecaoAgenda.montaServicoAjudante(new ArrayList<AgendaVO>(retornoHash.values())));
			
		return pLista;
	}
	
	public List<AgendaVO> pesquisaComissaoServicoPrincipalPorFunc(List<AgendaVO> lista, Date dataInicio, Date dataFinal, UsuarioVO usuario) throws Exception{

		Map<Integer ,AgendaVO> mapHistorico = new HashMap<Integer, AgendaVO>();
		
		try {
			
			Map<Integer,AgendaVO> hashQtdeAjudante = MapQtdeAjudante(dataInicio, dataFinal, usuario);
			
			for (AgendaVO agenda : lista){
				
				String servicos = agenda.getNomeServico();
				if(mapHistorico.containsKey(agenda.getCodigo())){
					agenda = mapHistorico.get(agenda.getCodigo());
					agenda.setServicos( agenda.getServicos()+  " - " + servicos);
				}else{
					agenda.setServicos(servicos);
				}
				
				
				agenda.setPorcentagem(agenda.getPorcentagem());
				Double valorTotal = agenda.getValorTotal();
				
				agenda.setValorComissao(
						realizaCalculoPrincipal(valorTotal, agenda.getPorcentagem(),
												agenda.getTipoRateio(),
												agenda.getCodigo(), hashQtdeAjudante)
				);
				
				
				
				mapHistorico.put(agenda.getCodigo(), agenda);
				
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return new ArrayList<AgendaVO>(mapHistorico.values());
	}

	
	public List<AgendaVO> pesquisaAgendaServico(Date dataInicio, Date dataFinal, int fk_status, UsuarioVO usuario ) throws Exception{
		
		Map<Integer, AgendaVO> retornoHash = new HashMap<Integer, AgendaVO>();
		List<AgendaVO> lista = fluxoCaixaDao.pesquisaAgendaServico(dataInicio, dataFinal, fk_status, usuario);
		
		for(AgendaVO agenda : lista){
			
			double valorServico = agenda.getValor();
			double valorDesconto = agenda.getDesconto();
			double valorTotal = agenda.getValorTotal();
			
			if(retornoHash.containsKey(agenda.getCodigoCliente())){
				
				agenda = (AgendaVO) retornoHash.get(agenda.getCodigoCliente());
				agenda.setValor( agenda.getValor() +valorServico );
				agenda.setDesconto( agenda.getDesconto() + valorDesconto );
				agenda.setValorTotal( agenda.getValorTotal() + valorTotal);
				agenda.setQtdeRegistros(agenda.getQtdeRegistros() + 1);
				
			} else{
				agenda.setQtdeRegistros(1);
			}
			
			retornoHash.put(agenda.getCodigoCliente(),agenda);
		}	
		
		return new ArrayList<AgendaVO>(retornoHash.values());
	}
	
	public Map<Integer,AgendaVO> pesquisaComissaoServicoPrincipal(Date dataInicio, Date dataFinal, UsuarioVO usuario ) throws Exception {

		Map<Integer,AgendaVO> retornoHash = new HashMap<Integer, AgendaVO>();
		Map<Integer,AgendaVO> hashQtdeAjudante = this.MapQtdeAjudante(dataInicio, dataFinal, usuario);
		List<AgendaVO> lista = fluxoCaixaDao.pesquisaComissaoServicoPrincipal(dataInicio, dataFinal, usuario);
		
		
		for(AgendaVO agenda : lista){
			
			Double valorServico = agenda.getValor();
			Double valorDesconto = agenda.getDesconto();
			Double valorTotal = agenda.getValorTotal();
			Double caixinha = agenda.getCaixinha();
			
			agenda.setPorcentagem(agenda.getPorcentagem());
			
			if(retornoHash.containsKey(agenda.getCodigoFuncionario())){
				
				agenda = (AgendaVO) retornoHash.get(agenda.getCodigoFuncionario());
				agenda.setValor(agenda.getValor() + valorServico);
				agenda.setCaixinha(agenda.getCaixinha() + caixinha);
				agenda.setDesconto(agenda.getDesconto() + valorDesconto);
				agenda.setValorTotal(agenda.getValorTotal() + valorTotal);
				
				agenda.setValorComissao(
						 (agenda.getValorComissao() + 
						  realizaCalculoPrincipal(valorTotal, agenda.getPorcentagem(), agenda.getTipoRateio(),agenda.getCodigo(), hashQtdeAjudante)	 
						 )
					);
				
				agenda.setQtdeRegistros(agenda.getQtdeRegistros() + 1);
				
			}else{
				
				agenda.setValorComissao(
						this.realizaCalculoPrincipal(valorTotal, agenda.getPorcentagem(),
								agenda.getTipoRateio(), agenda.getCodigo(), hashQtdeAjudante)
								
					);
				
				agenda.setQtdeRegistros(1);
			}
			
			retornoHash.put(agenda.getCodigoFuncionario(),agenda);
			
		}

		return retornoHash;
	}	
	
	public List<AgendaVO> pesquisaComissaoServicoAjudante(Date dataInicio, Date dataFinal, Map<Integer,AgendaVO> retornoHash, UsuarioVO usuario  ) throws Exception{
		
	
		Map<Integer,AgendaVO> hashQtdeAjudante = this.MapQtdeAjudante(dataInicio, dataFinal, usuario);
		List<AgendaVO> lista = fluxoCaixaDao.pesquisaComissaoServicoAjudante(dataInicio, dataFinal, usuario);
		RateioVO rateio = rateioDao.pesquisaRateio(usuario);
		
		for(AgendaVO agenda : lista){
			
			agenda.setPorcentagem(agenda.getPorcentagem());
		
			Double dcalculo = this.realizaCalculoAjudante(agenda, hashQtdeAjudante, rateio.getValor());
				
			if(retornoHash.containsKey(agenda.getCodigoFuncionario())){
				agenda = (AgendaVO) retornoHash.get(agenda.getCodigoFuncionario());
				agenda.setValorComissao( agenda.getValorComissao() + dcalculo);
			}else{
				agenda.setValorComissao(dcalculo);
				agenda.setValor(0d);
				agenda.setDesconto(0d);
				agenda.setValorTotal(0d);
			}
		
			retornoHash.put(agenda.getCodigoFuncionario(),agenda);
		}

		return new ArrayList<AgendaVO>(retornoHash.values());
	}


	private Map<Integer,AgendaVO> MapQtdeAjudante(Date dataInicio, Date dataFinal, UsuarioVO usuario) throws Exception{
		
		Map<Integer,AgendaVO> retornoQtdeHash = new HashMap<Integer, AgendaVO>();
		List<AgendaVO> lista = fluxoCaixaDao.verificaQtdeAjudante(dataInicio, dataFinal, usuario);
		
		for(AgendaVO agenda : lista){
			retornoQtdeHash.put(agenda.getCodigo(),agenda);
		}
		
		return retornoQtdeHash;
	}
	
	private Double realizaCalculoPrincipal(Double valorTotal, Integer porcentagem, Integer tipoRateio, Integer codigo, Map<Integer,AgendaVO> hashQtdeAjudante ) throws Exception{
		
		Double dCalculo = 0d;
		int iqtejudante = 0;
		AgendaVO ag;

		if ( tipoRateio.intValue() == 2 ){

			if(hashQtdeAjudante.containsKey(codigo)){
				ag = (AgendaVO) hashQtdeAjudante.get(codigo);
				iqtejudante =  Integer.parseInt(ag.getQtdeAjudante());
			}
			
			dCalculo = ( (valorTotal / (iqtejudante + 1)) * porcentagem ) / 100; 
		}
		else{
			dCalculo = (valorTotal * porcentagem) / 100;
		}

		
		return dCalculo;
	}
	
	private Double realizaCalculoAjudante(AgendaVO agenda, Map<Integer,AgendaVO> hashQtdeAjudante, Double valorRateio ) throws Exception{
		
		Double dCalculo = 0d;
		int iqtejudante = 0;
		
		AgendaVO ag;
		try {

			if ( agenda.getTipoRateio().intValue() == 1 ){
				dCalculo = (valorRateio * agenda.getPorcentagem())/100;
			}else{
				
				if(hashQtdeAjudante.containsKey(agenda.getCodigo())){
					ag = (AgendaVO) hashQtdeAjudante.get(agenda.getCodigo());
					iqtejudante =  Integer.parseInt(ag.getQtdeAjudante());
				}
				dCalculo = (agenda.getValorTotal() / (iqtejudante + 1) * agenda.getPorcentagem()) /100; 
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dCalculo;
	}
	
	private Double calculaTotalComissao(List<AgendaVO> listaComissao){
		
		Double dtotal = 0d;
		
		for(AgendaVO agenda : listaComissao){
			dtotal = dtotal + agenda.getValorComissao();
		}
		
		return dtotal;
	}
	
	private Double calculaTotalServico(List<AgendaVO> listaServico){
		
		Double dTotal = 0d;
		
		for(AgendaVO agenda : listaServico){
			dTotal = dTotal + agenda.getValorTotal();
		}
		
		return dTotal;
	}
	
	private Double calculaTotalDespesa(List<DespesaVO> listaDespesa){
		
		Double dTotal = 0d;
		
		for(DespesaVO desp : listaDespesa){
			dTotal = dTotal + desp.getValorConta();
		}
		
		return dTotal;
	}
	
	private Double calculaLucro(FluxoCaixaVO fluxo){
		return fluxo.getServicoTotal() - ( fluxo.getDespesaTotal() + fluxo.getComissaoTotal());
	}
	
	private Double calculaTotalCaixinha(List<AgendaVO> listaCaixinha){
		
		Double dTotal = 0d;
		
		for(AgendaVO agenda : listaCaixinha){
			dTotal = dTotal + agenda.getCaixinha();
		}

		return dTotal;
	}
	
}