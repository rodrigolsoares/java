package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.FuncionarioDao;
import br.com.gestao.salao.service.ServiceFuncionario;
import br.com.gestao.salao.util.PreparaColecaoAgenda;
import br.com.gestao.salao.util.RemoverCaracteres;
import br.com.gestao.salao.util.ValidarDeCampos;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceFuncionarioImpl implements ServiceFuncionario {
	
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	public List<AgendaVO> pesquisaHistoricoServicoFuncionario(FuncionarioVO func, UsuarioVO usuario) throws Exception{

		HashMap<Integer,AgendaVO> retornoHash = new HashMap<Integer,AgendaVO>();
		List<AgendaVO> lista = funcionarioDao.pesquisaHistoricoServicoFuncionario(func, usuario, false);
		
		for(AgendaVO ag : lista ) {
			
			String servico = ag.getNomeServico();
			String ajudante = ag.getNomeFuncionario();
			
			if(retornoHash.containsKey(ag.getCodigo())){
				
				ag = (AgendaVO)retornoHash.get(ag.getCodigo());
				
				if (!ag.getListaAjudanteSelecionado().contains(ajudante))
					ag.getListaAjudanteSelecionado().add(ajudante);
				
				if (!ag.getListaServicoSelecionado().contains(servico))
					ag.getListaServicoSelecionado().add(servico);
				
			}else{
				ag.getListaServicoSelecionado().add(servico);
				ag.getListaServicoSelecionado().add(ajudante);
			}
			
			retornoHash.put(ag.getCodigo(),ag);
				
		}
			
		return PreparaColecaoAgenda.montaServicoAjudante(new ArrayList<AgendaVO>(retornoHash.values()));
		
	}
	
	public String validaCamposObrigatorios(FuncionarioVO funcionario) throws Exception{
		
		String retorno = "";
		
		if(StringUtils.isEmpty(funcionario.getNome())){
			retorno += "Digite o nome do funcionário" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(funcionario.getSexo())){
			retorno +=   "Selecione o sexo" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(funcionario.getSexo())){
			retorno +=   "Selecione o status" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.rg(funcionario.getRg())){
			retorno +=  "RG digitado está inválido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.cpf(funcionario.getCpf())){
			retorno +=  "CPF digitado está inválido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(funcionario.getPorcentagem() == 0){
			retorno +=  "Porcentagem está inválido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(funcionario.getLogradouro())){
			retorno +=  "Digite o logradouro" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(funcionario.getNumero())){
			retorno +=  "Digite o número" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(funcionario.getBairro())){
			retorno +=  "Digite o bairro" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(funcionario.getCidade())){
			retorno +=  "Digite a cidade" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(funcionario.getEstado())){
			retorno +=  "Selecione o estado" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.email(funcionario.getEmail())){
			retorno +=  "E-mail digitado está inválido" + Constantes.PULA_LINHA_XHTML;
		}
	
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public List<FuncionarioVO> getListaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{
		
		return funcionarioDao.pesquisaFuncionario(funcionario, usuario);
	}
	
	public boolean verificaSeServicoExiste(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{
		return funcionarioDao.verificaFuncionario(funcionario, usuario);
	}
	
	public void gravaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{
		funcionarioDao.inseriFuncionario( trataObjeto(funcionario), usuario);
	}
	
	public void atualizarFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{
		funcionarioDao.atualizaFuncionario( trataObjeto(funcionario), usuario);
	}
	
	public Map<String, Integer> getMapAjudante(Integer codigo, UsuarioVO usuario) throws Exception{
		
		Map<String, Integer> mapServico = new HashMap<String, Integer>();
		for(FuncionarioVO funcionario: this.pesquisaFuncionarioAjudante(codigo, usuario)){
			mapServico.put(funcionario.getNome(), funcionario.getCodigo());
		}
		return mapServico;
	}
	
	public FuncionarioVO pesquisaFuncionariobyCodigo(Integer codigo) throws Exception {
		return funcionarioDao.pesquisaFuncionarioPorCodigo(codigo);
	}
	
	private List<FuncionarioVO> pesquisaFuncionarioAjudante(Integer codigo, UsuarioVO usuario) throws Exception {
		return funcionarioDao.pesquisaFuncionarioAjudante(codigo, usuario);
	}
	
	private FuncionarioVO trataObjeto(final FuncionarioVO funcionarioVO){
		FuncionarioVO newFuncionario = funcionarioVO;
		newFuncionario.setCep(RemoverCaracteres.somenteNumeros(funcionarioVO.getCep()));
		newFuncionario.setCpf(RemoverCaracteres.somenteNumeros(funcionarioVO.getCpf()));
		newFuncionario.setCrtTrabalho(RemoverCaracteres.somenteNumeros(funcionarioVO.getCrtTrabalho()));
		newFuncionario.setTelCelular(RemoverCaracteres.somenteNumeros(funcionarioVO.getTelCelular()));
		newFuncionario.setTelRecado1(RemoverCaracteres.somenteNumeros(funcionarioVO.getTelRecado1()));
		newFuncionario.setTelRecado2(RemoverCaracteres.somenteNumeros(funcionarioVO.getTelRecado2()));
		newFuncionario.setTelResidencial(RemoverCaracteres.somenteNumeros(funcionarioVO.getTelResidencial()));
		return newFuncionario;
	}


}
