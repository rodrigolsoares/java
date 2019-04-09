package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.dao.RateioDao;
import br.com.gestao.salao.vo.RateioVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class RateioDaoJdbc extends SpringDao implements RateioDao{
	
	public void atualizaRateio(Double valor, UsuarioVO usuario, boolean habilitaRateio) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" update rateio set valor = ?, flag_habilita = ?,  fk_usuario = ? ");
		sb.append(" where fk_responsavel_salao = ? and fk_salao = ?");
		
		Object[] param = new Object[]{
				valor,
				habilitaRateio,
				usuario.getCodigo(),
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo()
		};
			
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);	
			
		
	}
	
	public void inserir(Double valor, SalaoVO salao, boolean habilitaRateio) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append("insert into rateio Values(?, ?, ?, '0', null) ");
		
		Object[] param = new Object[]{
				salao.getResponsavel().getCodigo(),
				salao.getCodigo(),
				valor	
		};
			
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);	
			
		
	}
	
	public RateioVO pesquisaRateio(UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select valor, flag_habilita from rateio ");
		sb.append(" where fk_responsavel_salao = ? and fk_salao = ?");
		
		Object[] param = new Object[]{
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo()
		};
		
		return (RateioVO)getJdbcTemplate().query(sb.toString().toLowerCase(), param ,new rowMapper()).get(0);
			
	}
	
	private static final class rowMapper implements RowMapper<RateioVO> {
		public RateioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			RateioVO ra = new RateioVO();
			ra.setHabilita(rs.getBoolean("flag_habilita")); 
			ra.setValor(rs.getDouble("valor"));
			
			return ra;
		}
	}
}
