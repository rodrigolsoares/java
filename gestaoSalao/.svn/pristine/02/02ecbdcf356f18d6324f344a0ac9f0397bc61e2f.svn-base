package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.PerfilUsuario;
import br.com.gestao.salao.Constants.PlanosConstantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.ResponsavelDao;
import br.com.gestao.salao.dao.UsuarioDao;
import br.com.gestao.salao.service.ServiceUsuario;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.util.GeraHash;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Service
@Transactional(readOnly=false)
public class ServiceUsuarioImpl  implements ServiceUsuario{
	
	@Autowired
	private UsuarioDao usuarioDAO;
	
	@Autowired
	private ResponsavelDao responsavelDao;
	
	public UsuarioVO login(UsuarioVO usuario) throws Exception{
		
		UsuarioVO resultado = new UsuarioVO();

		usuario.setSenha(this.geraHash(usuario));
		
		if(this.verificaAutenticacaoUsuario(usuario)) {
			resultado.setMensagem("Erro, Usuário Não existe!");
		}
		
		else if(this.verificaAutenticacaoSenha(usuario)){
			resultado.setMensagem("Erro, Senha não confere!");
		}
		
		else if(this.verificaUsuarioAtivo(usuario)){
			resultado.setMensagem("Erro, Usuário desativado!");
		}
		
		else{
			
			resultado = usuarioDAO.pesquisaUsuarioPorLogin(usuario.getLogin());
			resultado.setFlagHabilitaAjudante(verificaHabilitaAjudante(resultado));
			resultado.setMensagem(verificaPrazoExpiracao(resultado));
			
		}
		
		resultado.setSenha(null);
		return resultado;
	}
	
	public String validaAlteraSenha(final UsuarioVO usuario) throws Exception{
		
		String retorno = "";
		
		if(StringUtils.isEmpty(usuario.getSenha())){
			retorno += "Digite a nova senha" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(StringUtils.isEmpty(usuario.getSenha())){
			retorno += "Digite a confirmação da nova senha" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return verificaDigitacaoNovaSenha(usuario);
		}
		
	}
	
	private String verificaDigitacaoNovaSenha(final UsuarioVO usuario) throws Exception{
		
		String retorno = "";
		
		if (!this.geraHash(usuario).equals(this.geraHashConfirmaSenha(usuario))){
			retorno += "senha não confere com a confirmação" + Constantes.PULA_LINHA_XHTML;
		}
		
		if(!StringUtils.isEmpty(retorno)){
			return retorno;
		}
		else{
			return null;
		}
		
		
	}
	
	
	
	
	public void alterarSenha(final UsuarioVO usuario) throws Exception{
		
		this.usuarioDAO.atualizaSenha(usuario.getLogin(), this.geraHash(usuario));
	}
	

	private String verificaPrazoExpiracao( final UsuarioVO resultado) {
		
		if(DataUtil.comparaDataMaiorQueDiaDeHoje(resultado.getResponsavel().getDtExperienciaSoftGestao())){
			return "Erro, Prazo de teste expirado!";
			
		}
		
		return null;
	}
	
	private boolean verificaHabilitaAjudante(UsuarioVO usuario){
		
		if(usuario.getSalao().getHabilitaAjudante().equals(RegraNegocioConstantes.HABILITA_AJUDANTE_SALAO)){
			return true;
		}
		
		return false;
		
	}
	
	public List<UsuarioVO> geraUsuariosParaPlanoSalao(SalaoVO salao, Integer codigoPlano){
		
		String inicialNomeSalao = salao.getNomeFantasia().substring(0, 3).toLowerCase();
		
		if(PlanosConstantes.BASICO == codigoPlano.intValue()){
			return this.geraUsuarioPlanoBasico(inicialNomeSalao);
		}
		
		else if(PlanosConstantes.INTERMEDIARIO == codigoPlano.intValue()){
			return this.geraUsuarioPlanoIntermediario(inicialNomeSalao);
		}
		
		else if(PlanosConstantes.AVANCADO_1 == codigoPlano.intValue()){
			return this.geraUsuarioPlanoAvancadoI(inicialNomeSalao);
		}
		
		else {
			return new ArrayList<UsuarioVO>();
		}

	}
	
	
	/**Metodos Privados auxiliares**/
	
	private List<UsuarioVO> geraUsuarioPlanoBasico(String inicialNomeSalao){
		
		List<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();
		
		UsuarioVO usuarioResp = new UsuarioVO(PerfilUsuario.NOME_RESPONSAVEL, inicialNomeSalao + PerfilUsuario.SIGLA_RESPONSAVEL, PerfilUsuario.CODIGO_RESPONSAVEL);
		listaUsuarios.add(usuarioResp);
		
		UsuarioVO usuarioOpr = new UsuarioVO(PerfilUsuario.NOME_OPERADOR, inicialNomeSalao + PerfilUsuario.SIGLA_OPERADOR, PerfilUsuario.CODIGO_OPERADOR);
		listaUsuarios.add(usuarioOpr);
		
		return listaUsuarios;
	}
	
	private List<UsuarioVO> geraUsuarioPlanoIntermediario(String inicialNomeSalao){
		
		List<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();
		
		UsuarioVO usuarioResp = new UsuarioVO(PerfilUsuario.NOME_RESPONSAVEL, inicialNomeSalao + PerfilUsuario.SIGLA_RESPONSAVEL, PerfilUsuario.CODIGO_RESPONSAVEL);
		listaUsuarios.add(usuarioResp);
		
		UsuarioVO usuarioAdm = new UsuarioVO(PerfilUsuario.NOME_ADMINISTRADOR, inicialNomeSalao + PerfilUsuario.SIGLA_ADMINISTRADOR, PerfilUsuario.CODIGO_ADMINISTRADOR);
		listaUsuarios.add(usuarioAdm);
		
		UsuarioVO usuarioOpr = new UsuarioVO(PerfilUsuario.NOME_OPERADOR, inicialNomeSalao + PerfilUsuario.SIGLA_OPERADOR, PerfilUsuario.CODIGO_OPERADOR);
		listaUsuarios.add(usuarioOpr);
		
		UsuarioVO usuarioVisu = new UsuarioVO(PerfilUsuario.NOME_VIZUALIZADOR, inicialNomeSalao + PerfilUsuario.SIGLA_VIZUALIZADOR, PerfilUsuario.CODIGO_VIZUALIZADOR);
		listaUsuarios.add(usuarioVisu);
		
		return listaUsuarios;
	}
	
	private List<UsuarioVO> geraUsuarioPlanoAvancadoI(String inicialNomeSalao){
		
		List<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();
		
		UsuarioVO usuarioResp = new UsuarioVO(PerfilUsuario.NOME_RESPONSAVEL, inicialNomeSalao + PerfilUsuario.SIGLA_RESPONSAVEL, PerfilUsuario.CODIGO_RESPONSAVEL);
		listaUsuarios.add(usuarioResp);
		
		UsuarioVO usuarioAdm = new UsuarioVO(PerfilUsuario.NOME_ADMINISTRADOR, inicialNomeSalao + PerfilUsuario.SIGLA_ADMINISTRADOR, PerfilUsuario.CODIGO_ADMINISTRADOR);
		listaUsuarios.add(usuarioAdm);
		
		UsuarioVO usuarioOpr = new UsuarioVO(PerfilUsuario.NOME_OPERADOR, inicialNomeSalao + PerfilUsuario.SIGLA_OPERADOR, PerfilUsuario.CODIGO_OPERADOR);
		listaUsuarios.add(usuarioOpr);
		
		UsuarioVO usuarioOprPrd = new UsuarioVO(PerfilUsuario.NOME_OPERADOR_PROD, inicialNomeSalao + PerfilUsuario.SIGLA_OPERADOR_PROD, PerfilUsuario.CODIGO_OPERADOR_PROD);
		listaUsuarios.add(usuarioOprPrd);
		
		UsuarioVO usuarioVisu = new UsuarioVO(PerfilUsuario.NOME_VIZUALIZADOR, inicialNomeSalao + PerfilUsuario.SIGLA_VIZUALIZADOR, PerfilUsuario.CODIGO_VIZUALIZADOR);
		listaUsuarios.add(usuarioVisu);
		
		return listaUsuarios;
	}
	
	private boolean verificaUsuarioAtivo(UsuarioVO usuario)throws Exception{
		return !usuarioDAO.verificaAutenticacaoStatus(usuario);
	}
	
	private boolean verificaAutenticacaoSenha(UsuarioVO usuario) throws Exception{
		return !usuarioDAO.verificaAutenticacaoSenha(usuario);
	}
	
	private boolean verificaAutenticacaoUsuario(UsuarioVO usuario) throws Exception{
		return !usuarioDAO.verificaAutenticacaoUsuario(usuario);
	}
	
	private String geraHash(UsuarioVO usuario) throws Exception{
		return GeraHash.hashGerado(usuario.getSenha());
	}
	
	private String geraHashConfirmaSenha(UsuarioVO usuario) throws Exception{
		return GeraHash.hashGerado(usuario.getConfirmaSenha());
	}
	
}
