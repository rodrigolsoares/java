package br.com.gestao.salao.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.DespesaDao;
import br.com.gestao.salao.service.ServiceDespesa;
import br.com.gestao.salao.vo.DespesaVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceDespesaImpl implements ServiceDespesa{
	
	@Autowired
	private DespesaDao despesaDao;
	
	public String validaCamposObrigatorios(DespesaVO despesa){
		
		String retorno = "";
		
		if(StringUtils.isEmpty(despesa.getNomeConta())){
			retorno += "Erro, digite o nome da conta" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(despesa.getDataVencimento() == null ){
			retorno += "Erro, digite a data do vencimento" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(despesa.getDataPagamento() == null ){
			retorno += "Erro, digite a data do pagamento" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(despesa.getValorConta().equals(0d)){
			retorno += "Erro, digite o valor da conta" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(despesa.getStatus() == 0){
			retorno += "Erro, selecione o status" + Constantes.PULA_LINHA_XHTML;	
		}
		
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
		
	}
	
	public void gravar(DespesaVO despesa, UsuarioVO usuario) throws Exception{
		despesaDao.inseriDespesa(despesa, usuario);
	}
	
	public void atualiza(DespesaVO despesa, UsuarioVO usuario) throws Exception{
		despesaDao.atualizaDespesa(despesa, usuario);
	}
	
	public boolean verificaSeDespesaExiste(DespesaVO despesa, UsuarioVO usuario) throws Exception { 
		return despesaDao.verificaDespesa(despesa, usuario);
	}
	
	public List<DespesaVO> pesquisar(DespesaVO despesa, UsuarioVO usuario) throws Exception {
		return despesaDao.pesquisaDespesa(despesa, usuario);
		
	}
	
}
