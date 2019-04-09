package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.ServicoDao;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.ServicoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class ServicoDaoJdbc extends SpringDao implements ServicoDao{

	public void inseriServico(ServicoVO servico, UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into servico ( pk_servico, nm_servico, valor, descricao, fk_status, sigla, ");
		sb.append(" fk_responsavel_salao, fk_salao, fk_usuario ) values");
		sb.append(" (?, ?, ?, ?, ?, ?, ?, ?, ? );");
		
		Object[] param = new Object[]{
				Constantes.ZERO,
				servico.getNome(),
				servico.getValor(),
				servico.getDescricao(),
				servico.getStatus() ,
				servico.getSigla(),
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo(),
				usuario.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}

	public void atualizaServico(ServicoVO servico, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();

			sb.append(" update servico set ");
			sb.append(" nm_servico = ? ,");
			sb.append(" valor = ? ,");
			sb.append(" descricao = ? ,");
			sb.append(" fk_status = ? ,");
			sb.append(" sigla = ? ,");
			sb.append(" fk_usuario = ? ");
			sb.append("where pk_servico = ? ");
			
			Object[] param = new Object[]{
				servico.getNome(),
				servico.getValor(),
				servico.getDescricao(),
				servico.getStatus(),
				servico.getSigla(),
				usuario.getCodigo(),
				servico.getCodigo()
			};
			
			getJdbcTemplate().update(sb.toString().toLowerCase(), param);
			
	}

	public boolean verificaServico(ServicoVO servico, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_servico from servico ");
		sb.append(" where (nm_servico = ? or sigla = ? ) ");
		sb.append("   and fk_responsavel_salao = ? and fk_salao = ? ");
		
		if (servico.getFlagOperacao() == RegraNegocioConstantes.PESQUISAR_REGISTRO_INCLUINDO_A_CHAVE){
			sb.append(" and pk_servico <> " + servico.getCodigo());
		}
		
		Object[] param = new Object[]{
				servico.getNome(),
				servico.getSigla(),
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo()
			};
			
		try{
			
			getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), param);
			return true;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}

	}

	public List<ServicoVO> pesquisaServico(UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_servico, nm_servico, valor, fk_status, sigla, descricao from servico ");
		sb.append(" where fk_responsavel_salao = ? and fk_salao = ? ");
		sb.append(" order by 2");
		
		Object[] param = new Object[]{
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo()
			};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),param, new rowMapper());
	}
	
	public List<ServicoVO> pesquisaServico(SalaoVO salao) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_servico, nm_servico, valor, fk_status, sigla, descricao from servico ");
		sb.append(" where fk_salao = ? ");
		sb.append(" order by 2");
		
		Object[] param = new Object[]{
				salao.getCodigo()
			};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),param, new rowMapper());
	}


	public List<ServicoVO> pesquisaServicoAgenda(UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();		
		sb.append(" select pk_servico, nm_servico, valor, descricao, fk_status, sigla from servico where fk_status = 1 ");
		sb.append(" order by 2");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
	}

	public ServicoVO pesquisaServicoPorCodigo(String codigo, UsuarioVO usuario) throws Exception{
	
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_servico, nm_servico, valor, descricao, fk_status, sigla from servico ");
		sb.append(" Where pk_servico= ? " );
		
		try{
			
			return (ServicoVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{codigo},new rowMapper());
		
		} catch (EmptyResultDataAccessException e){
			return new ServicoVO();
		}
		
	}
	
	private static final class rowMapper implements RowMapper<ServicoVO> {
		public ServicoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ServicoVO se = new ServicoVO();
			se.setCodigo(rs.getInt("pk_servico"));
			se.setNome(rs.getString("nm_servico"));
			se.setValor(rs.getDouble("valor"));
			se.setDescricao(rs.getString("descricao"));
			se.setStatus(rs.getInt("fk_status"));
			se.setSigla(rs.getString("sigla"));
			
			return se;
		}
	}


}
