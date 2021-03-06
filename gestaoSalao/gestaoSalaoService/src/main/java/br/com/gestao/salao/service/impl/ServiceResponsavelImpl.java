package br.com.gestao.salao.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.RateioDao;
import br.com.gestao.salao.dao.ResponsavelDao;
import br.com.gestao.salao.dao.SalaoDao;
import br.com.gestao.salao.dao.UsuarioDao;
import br.com.gestao.salao.service.ServiceResponsavel;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.util.GeraHash;
import br.com.gestao.salao.util.RemoverCaracteres;
import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceResponsavelImpl implements ServiceResponsavel{
	
	@Autowired
	private ResponsavelDao responsavelDao;
	
	@Autowired
	private SalaoDao salaoDao; 
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private RateioDao rateioDao;
	
	public String validaCamposObrigatoriosFrameResponsavel(ResponsavelVO responsavel) {
		
		String retorno = "";
		
		if(StringUtils.isEmpty(responsavel.getNome())){
			retorno += "Erro, digite o nome completo" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(responsavel.getSexo())){
			retorno += "Erro, selecione do Sexo" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(responsavel.getCpf())){
			retorno += "Erro, digite o CPF" + Constantes.PULA_LINHA_XHTML;
		}
		
	
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public String validaCamposObrigatoriosFramePlano(ResponsavelVO responsavel){
		
		String retorno = "";
		
		if(responsavel.getPlano().getCodigo().equals(0)){
			retorno += "Erro, selecione o plano desejado." + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public String validaCamposObrigatoriosFrameSalao(SalaoVO salao){

		String retorno = "";
		
		if(StringUtils.isEmpty(salao.getNomeFantasia())){
			retorno += "Erro, digite o nome fantasia." + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getLogradouro())){
			retorno +=  "Erro, digite o logradouro" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getNumero())){
			retorno +=  "Erro, digite o n�mero" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getBairro())){
			retorno +=  "Erro, digite o bairro" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getCidade())){
			retorno +=  "Erro, digite a cidade" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getEstado())){
			retorno +=  "Erro, selecione o estado" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getTel1())){
			retorno +=  "Erro, digite o primeiro telefone" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(salao.getEmail())){
			retorno += "Erro, digite o e-mail" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
		
	}
	
	public String validaCamposObrigatoriosFrameUsuario(List<UsuarioVO> listaUsuario){
		
		String retorno = "";
		
		for(UsuarioVO usuario : listaUsuario){
		
			if(StringUtils.isEmpty(usuario.getSenha())){
				retorno += "Erro, digite a senha." + Constantes.PULA_LINHA_XHTML;
				break;
			}
			
			if(StringUtils.isEmpty(usuario.getConfirmaSenha())){
				retorno += "Erro, digite a confirma��o da senha." + Constantes.PULA_LINHA_XHTML;
				break;
			}
			
			if(!usuario.getConfirmaSenha().equals(usuario.getSenha())){
				retorno += "Erro, senha n�o confere." + Constantes.PULA_LINHA_XHTML;
				break;
			}
			
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
	}
	
	public void gravarContrato(final ResponsavelVO responsavel)throws Exception{
		
		ResponsavelVO responsavelNovo = responsavel; 
		responsavel.setDtExperienciaSoftGestao(geraDataExpiracaoTeste());
		responsavelNovo.setCodigo(responsavelDao.inserir(trataObjetoResponsavel(responsavel)));
		gravaSalaoContrato(responsavelNovo);
		
	}

	private void gravaSalaoContrato(ResponsavelVO responsavel) throws Exception {
		
		for(SalaoVO salao: responsavel.getListaSalao()){
			salao.setResponsavel(responsavel);
			salao.setHabilitaAjudante(RegraNegocioConstantes.NAO_HABILITA_AJUDANTE_SALAO);
			salao.setCodigo(salaoDao.inserir(salao));
			this.gravaUsuariosContrato(salao);
			this.inserirRateioSalao(salao);
		}
	}
	
	
	private Date geraDataExpiracaoTeste(){		
		return DataUtil.avancarDataComQtDias(new Date(), RegraNegocioConstantes.TEMPO_EXPIRACAO_DO_SISTEMA);
	}
	

	private void gravaUsuariosContrato(SalaoVO salao) throws Exception {
		
		for(UsuarioVO usuario : salao.getListaUsuarios()){
			usuario.setSenha(GeraHash.hashGerado(usuario.getSenha()));
			usuario.getStatus().setCodigo(RegraNegocioConstantes.REGISTRO_ATIVO);
			usuarioDao.inseriUsuario(usuario, trataObjetoSalao(salao));
		}
		
	}
	
	private void inserirRateioSalao(SalaoVO salao) throws Exception{
		rateioDao.inserir(RegraNegocioConstantes.VALOR_INICIAL_RATEIO, salao, false);
	}
	
	private ResponsavelVO trataObjetoResponsavel(final ResponsavelVO responsavelVO){
		ResponsavelVO newResponsavelVO = responsavelVO;
		newResponsavelVO.setCpf(RemoverCaracteres.somenteNumeros(newResponsavelVO.getCpf()));
		newResponsavelVO.setRg(RemoverCaracteres.somenteNumeros(newResponsavelVO.getRg()));
		newResponsavelVO.setTelCelular(RemoverCaracteres.somenteNumeros(newResponsavelVO.getTelCelular()));
		newResponsavelVO.setTelComercial(RemoverCaracteres.somenteNumeros(newResponsavelVO.getTelComercial()));
		newResponsavelVO.setTelResidencial(RemoverCaracteres.somenteNumeros(newResponsavelVO.getTelResidencial()));
		return newResponsavelVO;
	}
	
	private SalaoVO trataObjetoSalao(final SalaoVO salaoVO){
		SalaoVO newSalao = salaoVO;
		newSalao.setCnpj(RemoverCaracteres.somenteNumeros(newSalao.getCnpj()));
		newSalao.setInscEstadual(RemoverCaracteres.somenteNumeros(newSalao.getInscEstadual()));
		newSalao.setCep(RemoverCaracteres.somenteNumeros(newSalao.getCep()));
		newSalao.setTel1(RemoverCaracteres.somenteNumeros(newSalao.getTel1()));
		newSalao.setTel2(RemoverCaracteres.somenteNumeros(newSalao.getTel2()));
		return newSalao;
	}
	

}
