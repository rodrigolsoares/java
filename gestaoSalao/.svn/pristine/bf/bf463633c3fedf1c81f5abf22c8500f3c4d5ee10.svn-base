package br.com.gestao.salao.mobile.Controller;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.mobile.util.MemoriaUsuario;
import br.com.gestao.salao.service.ServiceFluxoCaixa;
import br.com.gestao.salao.service.ServiceGrafico;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FluxoCaixaVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller("graficoMobileMB")
@Scope("view")
public class GraficoMB {

	@Autowired
	private ServiceFluxoCaixa serviceFluxoCaixa; 
	
	@Autowired
	private ServiceGrafico serviceGrafico;
	
	private PieChartModel graficoPizzaFuncAtendi;
	private PieChartModel graficoPizzaFuncValores;
	private PieChartModel graficoPizzaCliAtendi;
	private PieChartModel graficoPizzaCliValores;
	private FluxoCaixaVO fluxoCaixa;
	private boolean habilitaGrafico = false;
	private UsuarioVO usuario;
	
	@PostConstruct
	public void init(){
		
		usuario = MemoriaUsuario.getUsuarioMemoria();
		graficoPizzaFuncAtendi = new PieChartModel();
		graficoPizzaFuncValores = new PieChartModel();
		graficoPizzaCliAtendi = new PieChartModel();
		graficoPizzaCliValores = new PieChartModel();
		
		fluxoCaixa = new FluxoCaixaVO();
	}
	
	public void geraGrafico() throws Exception{
		
		graficoPizzaFuncAtendi = new PieChartModel();
		graficoPizzaFuncValores = new PieChartModel();
		graficoPizzaCliAtendi = new PieChartModel();
		graficoPizzaCliValores = new PieChartModel();
		
		this.setFluxoCaixa(serviceFluxoCaixa.getFluxoDeCaixa(this.getFluxoCaixa(), this.getUsuario()));
		
		for(AgendaVO agenda : this.getFluxoCaixa().getListaServico() ){
			graficoPizzaCliValores.set(agenda.getNomeCliente(), agenda.getValorTotal());
		}
		
		for(AgendaVO agenda : this.getFluxoCaixa().getListaComissao() ){
			graficoPizzaFuncValores.set(agenda.getNomeFuncionario(), agenda.getValorTotal());
		}
		
		for(AgendaVO agenda : this.getFluxoCaixa().getListaComissao()){
			graficoPizzaFuncAtendi.set(agenda.getNomeFuncionario(), agenda.getQtdeRegistros());
		}
		
		for(AgendaVO agenda : serviceGrafico.geraDadosClienteMaisAtende(this.getFluxoCaixa().getListaServico(), this.getFluxoCaixa().getListaPendencia()) ){
			graficoPizzaCliAtendi.set(agenda.getNomeCliente(), agenda.getQtdeRegistros());
		}

		this.setHabilitaGrafico(true);

	}

	public FluxoCaixaVO getFluxoCaixa() {
		return fluxoCaixa;
	}

	public void setFluxoCaixa(FluxoCaixaVO fluxoCaixa) {
		this.fluxoCaixa = fluxoCaixa;
	}

	public ServiceFluxoCaixa getServiceFluxoCaixa() {
		return serviceFluxoCaixa;
	}

	public PieChartModel getGraficoPizzaFuncAtendi() {
		return graficoPizzaFuncAtendi;
	}

	public PieChartModel getGraficoPizzaFuncValores() {
		return graficoPizzaFuncValores;
	}


	public PieChartModel getGraficoPizzaCliAtendi() {
		return graficoPizzaCliAtendi;
	}

	public PieChartModel getGraficoPizzaCliValores() {
		return graficoPizzaCliValores;
	}

	public boolean isHabilitaGrafico() {
		return habilitaGrafico;
	}

	public void setHabilitaGrafico(boolean habilitaGrafico) {
		this.habilitaGrafico = habilitaGrafico;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	
	
}
