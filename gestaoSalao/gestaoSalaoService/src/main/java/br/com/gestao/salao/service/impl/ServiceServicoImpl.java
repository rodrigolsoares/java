package br.com.gestao.salao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.ServicoDao;
import br.com.gestao.salao.service.ServiceServico;
import br.com.gestao.salao.vo.ServicoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceServicoImpl implements ServiceServico {
	
	@Autowired
	private ServicoDao servicoDao;
	
	public String validaCamposObrigatorios(ServicoVO servico){
		
		String retorno = "";
		
		if(StringUtils.isEmpty(servico.getNome())){
			retorno += "Digite o nome" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(servico.getValor().equals(0d) ){
			retorno += "Digite o valor" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(servico.getSigla())){
			retorno +=  "Digite a silga do serviço" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(servico.getStatus() == 0){
			retorno += "Selecione o status" + Constantes.PULA_LINHA_XHTML;	
		}
		
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
		
	}
	
	public List<ServicoVO> getListServico(UsuarioVO usuario) throws Exception{
		return servicoDao.pesquisaServico(usuario);
	}
	
	public Map<String, Integer> getMapServico(UsuarioVO usuario) throws Exception{
		
		Map<String, Integer> mapServico = new HashMap<String, Integer>();
		for(ServicoVO servico: servicoDao.pesquisaServico(usuario)){
			mapServico.put(servico.getNome(), servico.getCodigo());
		}
		return mapServico;
	}
	
	public Double calculaValorServico(List<String> listaServico, UsuarioVO usuario) throws Exception{
		
		Double valorCalculado = 0d;
		for(String codServico : listaServico){
			ServicoVO servico = this.getMapObjServico(usuario).get(Integer.parseInt(codServico));
			valorCalculado = valorCalculado + servico.getValor();
			
		}
		return valorCalculado;
	}
	
	
	private Map<Integer, ServicoVO> getMapObjServico(UsuarioVO usuario) throws Exception{
		
		Map<Integer, ServicoVO> mapServico = new HashMap<Integer, ServicoVO>();
		for(ServicoVO servico: servicoDao.pesquisaServico(usuario)){
			mapServico.put(servico.getCodigo(), servico);
		}
		return mapServico;
	}
	
	public boolean verificaSeServicoExiste(ServicoVO servico, UsuarioVO usuario) throws Exception { 
		return servicoDao.verificaServico(servico, usuario);
	}
	
	public void gravaServico(ServicoVO servico, UsuarioVO usuario) throws Exception{
		servicoDao.inseriServico(servico, usuario);
	}
	
	public void atualizaServico(ServicoVO servico, UsuarioVO usuario) throws Exception {
		servicoDao.atualizaServico(servico, usuario);
	}

}
