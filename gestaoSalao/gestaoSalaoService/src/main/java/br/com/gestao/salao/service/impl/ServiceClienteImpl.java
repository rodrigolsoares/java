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
import br.com.gestao.salao.dao.ClienteDao;
import br.com.gestao.salao.service.ServiceCliente;
import br.com.gestao.salao.util.PreparaColecaoAgenda;
import br.com.gestao.salao.util.RemoverCaracteres;
import br.com.gestao.salao.util.ValidarDeCampos;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceClienteImpl implements ServiceCliente{
	
	@Autowired
	private ClienteDao clienteDao;
	
	public List<AgendaVO> pesquisaHistoricoServicoCliente(ClienteVO cli, UsuarioVO usuario) throws Exception{
		
		Map<Integer, AgendaVO> retornoHash = new HashMap<Integer, AgendaVO>();
		List<AgendaVO> lista = clienteDao.pesquisaHistoricoServicoCliente(cli, usuario, false);
		
		for(AgendaVO agenda : lista){
			
			if(retornoHash.containsKey(agenda.getCodigo())){
				agenda = (AgendaVO)retornoHash.get(agenda.getCodigo());
				
				if (!agenda.getListaAjudanteSelecionado().contains(agenda.getNomeAjudante()))
					agenda.getListaAjudanteSelecionado().add(agenda.getNomeAjudante());
				
				if (!agenda.getListaServicoSelecionado().contains(agenda.getNomeServico()))
					agenda.getListaServicoSelecionado().add(agenda.getNomeServico());
				
			}else{
				agenda.getListaServicoSelecionado().add(agenda.getNomeServico());
				agenda.getListaAjudanteSelecionado().add(agenda.getNomeAjudante());
			}
			
			retornoHash.put(agenda.getCodigo(),agenda);
			
		}
		
		return PreparaColecaoAgenda.montaServicoAjudante(new ArrayList<AgendaVO>(retornoHash.values()));
	}
	
	public String validaCamposObrigatorios(ClienteVO cliente) {
		
		String retorno = "";
		
		if(StringUtils.isEmpty(cliente.getNome())){
			retorno += "Digite o nome do cliente" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(cliente.getSexo())){
			retorno +=  "Selecione o sexo" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.rg(cliente.getRg())){
			retorno +=  "RG inv�lido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.cpf(cliente.getCpf())){
			retorno +=  "CPF inv�lido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(cliente.getLogradouro())){
			retorno +=  "Digite o logradouro" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(cliente.getNumero())){
			retorno +=  "Digite o n�mero" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(cliente.getBairro())){
			retorno +=  "Digite o bairro" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(cliente.getCidade())){
			retorno +=  "Digite a cidade" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(cliente.getEstado())){
			retorno +=  "Selecione o estado" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.email(cliente.getEmail())){
			retorno +=  "E-mail digitado � inv�lido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public String validaCamposObrigatoriosRapido(ClienteVO cliente) {
		
		String retorno = "";
		
		if(StringUtils.isEmpty(cliente.getNome())){
			retorno += "Digite o nome do cliente" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!ValidarDeCampos.cpf(cliente.getCpf())){
			retorno +=  "CPF inv�lido" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	
	public ClienteVO pesquisaClientePorCodigo(Integer codigo) throws Exception{
		return clienteDao.pesquisaClientePorCodigo(codigo);
	}
	
	public ClienteVO pesquisaClientePorAndoid(String numeroTelefone, Integer codigoSalao) throws Exception{
		return clienteDao.pesquisaClientePorAppAndroid(numeroTelefone, codigoSalao);
	}
	
	public List<ClienteVO> getListaCliente(ClienteVO cliente, UsuarioVO usuario)throws Exception{
		return clienteDao.pesquisaCliente(cliente, usuario);
	}
	
	public boolean verificaSeServicoExiste (ClienteVO cliente, UsuarioVO usuario) throws Exception{
		return clienteDao.verificaCliente(cliente, usuario);
	}
	
	public void gravaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception{
		clienteDao.inseriCliente(trataObjeto(cliente), usuario);
	}
	
	public void atualizarCliente (ClienteVO cliente, UsuarioVO usuario) throws Exception{
		clienteDao.atualizaCliente(trataObjeto(cliente), usuario);
	}
	
	private ClienteVO trataObjeto(final ClienteVO clienteVO){
		ClienteVO newClienteVO = clienteVO;
		newClienteVO.setCep(RemoverCaracteres.somenteNumeros(clienteVO.getCep()));
		newClienteVO.setCpf(RemoverCaracteres.somenteNumeros(clienteVO.getCpf()));
		newClienteVO.setTelCelular(RemoverCaracteres.somenteNumeros(clienteVO.getTelCelular()));
		newClienteVO.setTelRecado(RemoverCaracteres.somenteNumeros(clienteVO.getTelRecado()));
		newClienteVO.setTelComercial(RemoverCaracteres.somenteNumeros(clienteVO.getTelComercial()));
		newClienteVO.setTelResidencial(RemoverCaracteres.somenteNumeros(clienteVO.getTelResidencial()));
		return clienteVO;
	}


}
