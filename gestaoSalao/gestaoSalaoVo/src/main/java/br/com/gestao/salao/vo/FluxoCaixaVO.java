package br.com.gestao.salao.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FluxoCaixaVO {
	
	private Date dataInicial;
	private Date dataFinal;
	private List<DespesaVO> listaDespesa = new ArrayList<DespesaVO>();
	private List<AgendaVO> listaServico = new ArrayList<AgendaVO>();
	private List<AgendaVO> listaPendencia = new ArrayList<AgendaVO>();
	private List<AgendaVO> listaComissao = new ArrayList<AgendaVO>();
	private List<AgendaVO> listaCheques = new ArrayList<AgendaVO>();
	private Double despesaTotal;
	private Double servicoTotal;
	private Double comissaoTotal;
	private Double caixinhaTotal;
	private Double totalPagaFuncionario;
	private Double lucro;
	
	public List<DespesaVO> getListaDespesa() {
		return listaDespesa;
	}
	
	public void setListaDespesa(List<DespesaVO> listaDespesa) {
		this.listaDespesa = listaDespesa;
	}
	
	public List<AgendaVO> getListaServico() {
		return listaServico;
	}
	
	public void setListaServico(List<AgendaVO> listaServico) {
		this.listaServico = listaServico;
	}
	
	public List<AgendaVO> getListaPendencia() {
		return listaPendencia;
	}
	
	public void setListaPendencia(List<AgendaVO> listaPendencia) {
		this.listaPendencia = listaPendencia;
	}
	
	public List<AgendaVO> getListaComissao() {
		return listaComissao;
	}
	
	public void setListaComissao(List<AgendaVO> listaComissao) {
		this.listaComissao = listaComissao;
	}
	
	public List<AgendaVO> getListaCheques() {
		return listaCheques;
	}
	
	public void setListaCheques(List<AgendaVO> listaCheques) {
		this.listaCheques = listaCheques;
	}
	
	public Double getDespesaTotal() {
		return despesaTotal;
	}
	
	public void setDespesaTotal(Double despesaTotal) {
		this.despesaTotal = despesaTotal;
	}
	
	public Double getServicoTotal() {
		return servicoTotal;
	}
	
	public void setServicoTotal(Double servicoTotal) {
		this.servicoTotal = servicoTotal;
	}
	
	public Double getComissaoTotal() {
		return comissaoTotal;
	}
	
	public void setComissaoTotal(Double comissaoTotal) {
		this.comissaoTotal = comissaoTotal;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Double getLucro() {
		return lucro;
	}

	public void setLucro(Double lucro) {
		this.lucro = lucro;
	}

	public Double getCaixinhaTotal() {
		return caixinhaTotal;
	}

	public void setCaixinhaTotal(Double caixinhaTotal) {
		this.caixinhaTotal = caixinhaTotal;
	}

	public Double getTotalPagaFuncionario() {
		return totalPagaFuncionario;
	}

	public void setTotalPagaFuncionario(Double totalPagaFuncionario) {
		this.totalPagaFuncionario = totalPagaFuncionario;
	}	
	
}
