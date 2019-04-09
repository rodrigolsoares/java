package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.UsuarioDao;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class UsuarioDaoJdbc extends SpringDao implements UsuarioDao {

	public void inseriUsuario(UsuarioVO usuario, SalaoVO salao) throws Exception{
	
		StringBuffer sb = new StringBuffer();

		sb.append(" insert into usuario ( pk_usuario, nm_usuario, login, senha, fk_perfil, fk_status, ");
		sb.append(" fk_responsavel_salao, fk_salao, fk_plano) values (?, ?, ?, ?, ?, ?, ?, ?, ? );" );
		
		Object[] param = new Object[]{
				Constantes.ZERO,
				usuario.getNome(),
				usuario.getLogin(),
				usuario.getSenha(),
				usuario.getPerfil().getCodigo(),
				usuario.getStatus().getCodigo(),
				salao.getResponsavel().getCodigo(),
				salao.getCodigo(),
				salao.getResponsavel().getPlano().getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}

	public void atualizaUsuario(UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();

		sb.append(" update usuario set ");
		sb.append(" nm_usuario = ? ,");
		sb.append(" login = ? ,");
		sb.append(" senha = ? ,");
		sb.append(" fk_perfil = ? ,");
		sb.append(" fk_status = ? ");
		sb.append("where pk_usuario = ? ");
		
		Object[] param = new Object[]{
				usuario.getNome(),
				usuario.getLogin(),
				usuario.getSenha(),
				usuario.getPerfil(),
				usuario.getStatus(),
				usuario.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}
	
	public void atualizaSenha(String login, String senha) throws Exception{

		StringBuffer sb = new StringBuffer();
	
		sb.append(" update usuario set ");
		sb.append(" senha = ? ");
		sb.append(" where login = ? ");
		
		Object[] param = new Object[]{
				senha,
				login
		};
			
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}
	
	public boolean verificaUsuario(UsuarioVO usuario, int tipoOperacao) throws Exception{

		StringBuffer sb = new StringBuffer();		
		sb.append("select * from usuario where (nm_usuario = '" + usuario.getNome() + "' or login = '" + usuario.getLogin() + "') ");
		
		if (tipoOperacao == RegraNegocioConstantes.PESQUISAR_REGISTRO_INCLUINDO_A_CHAVE)
			sb.append(" and pk_usuario <> " + usuario.getCodigo());

		try{
			Integer param = getJdbcTemplate().queryForInt(sb.toString().toLowerCase());
			
			if (param != null && param > 0) return true;
			else return false;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}
		
	}

	public boolean verificaAutenticacaoUsuario(UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select fk_perfil from usuario u where u.login= ?");
		sb.append(" and fk_status = 1");

		try{
			Integer param = getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), new Object[]{ usuario.getLogin()});
			
			if (param != null && param > 0) return true;
			else return false;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}
		
	}

	public boolean verificaAutenticacaoStatus(UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_usuario from usuario u where u.login= ?");
		sb.append(" and fk_status = 1");

		try{
			Integer param = getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), new Object[]{ usuario.getLogin()});
			
			if (param != null && param > 0) return true;
			else return false;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}
	}

	public boolean verificaAutenticacaoSenha(UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		
		sb.append(" select pk_usuario from usuario u where senha = ? ");
		sb.append(" and u.login= ? and fk_status = 1");

		try{
			Integer param = getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), new Object[]{usuario.getSenha(), usuario.getLogin()});
			
			if (param != null && param > 0) return true;
			else return false;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}

	}

	public List<UsuarioVO> pesquisaUsuario() throws Exception{

		StringBuffer sb = getQueryUsuario();		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
	}
	
	public UsuarioVO pesquisaUsuarioPorLogin(String login) throws Exception{

		StringBuffer sb = getQueryUsuario();
		sb.append(" and login = ? " );
		
		try{
			
			return (UsuarioVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{login},new rowMapper()).get(0);
		
		} catch (EmptyResultDataAccessException e){
			return new UsuarioVO();
		}
		
	}
	
	public UsuarioVO pesquisaUsuarioPorCodigo(String codigo) throws Exception{

		StringBuffer sb = getQueryUsuario();
		sb.append(" and pk_usuario= ? " );
		
		try{
			
			return (UsuarioVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{codigo},new rowMapper());
		
		} catch (EmptyResultDataAccessException e){
			return new UsuarioVO();
		}
		
	}
	
	private StringBuffer getQueryUsuario(){
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select u.pk_usuario, u.nm_usuario, u.login, u.senha, u.fk_perfil, p.nm_perfil,  u.fk_status, rs.DT_EXPERIENCIA_SOFT, ");
		sb.append("        sr.nm_status, u.fk_responsavel_salao, rs.nm_responsavel, u.fk_salao, s.nm_fantasia_salao , u.fk_plano, plan.nm_plano, ");
		sb.append("        s.habilita_ajudante");
		sb.append("   from usuario u, responsavel_salao rs, salao s, perfil p, status_registro sr, plano plan ");
		sb.append("  where u.fk_perfil = p.pk_perfil ");
		sb.append("    and u.fk_responsavel_salao = rs.pk_responsavel_salao ");
		sb.append("    and u.fk_salao = s.pk_salao ");
		sb.append("    and u.fk_status = sr.pk_status ");
		sb.append("    and u.fk_plano = plan.pk_plano ");
		
		return sb;
		
	}
	
	private static final class rowMapper implements RowMapper<UsuarioVO> {
		public UsuarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			UsuarioVO us = new UsuarioVO();
			us.setCodigo(rs.getInt("pk_usuario"));
			us.setNome(rs.getString("nm_usuario"));
			us.setLogin(rs.getString("login"));
			us.setSenha(rs.getString("senha"));
			us.setConfirmaSenha(rs.getString("senha"));
			us.getPerfil().setCodigo(rs.getInt("fk_perfil"));
			us.getPerfil().setNome(rs.getString("nm_perfil"));
			us.getStatus().setCodigo(rs.getInt("fk_status"));
			us.getStatus().setNomeStatus(rs.getString("nm_status"));
			us.getSalao().setCodigo(rs.getInt("fk_salao"));
			us.getSalao().setHabilitaAjudante(rs.getString("habilita_ajudante"));
			us.getSalao().setNomeFantasia(rs.getString("nm_fantasia_salao"));
			us.getResponsavel().setCodigo(rs.getInt("fk_responsavel_salao"));
			us.getResponsavel().getPlano().setCodigo(rs.getInt("fk_plano"));
			us.getResponsavel().getPlano().setNome(rs.getString("nm_plano"));
			us.getResponsavel().setDtExperienciaSoftGestao(rs.getDate("DT_EXPERIENCIA_SOFT"));
			
			return us;
		}
	}

}
