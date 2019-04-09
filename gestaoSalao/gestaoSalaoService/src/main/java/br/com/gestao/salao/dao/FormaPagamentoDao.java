package br.com.gestao.salao.dao;

import java.util.List;

import br.com.gestao.salao.vo.FormaPagamentoVO;

public interface FormaPagamentoDao {
	
	List<FormaPagamentoVO> pesquisaFormaPagamento() throws Exception;
}
