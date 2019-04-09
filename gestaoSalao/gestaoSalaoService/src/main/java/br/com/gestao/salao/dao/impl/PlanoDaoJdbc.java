package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.PlanoDao;
import br.com.gestao.salao.vo.PlanoVO;

@Repository
public class PlanoDaoJdbc  extends SpringDao implements PlanoDao{
	
	public List<PlanoVO> pesquisarPlanos() throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_plano, nm_plano, descricao, valor from plano ");
		sb.append(" where fk_status = ? order by 2");
		
		Object[] param = new Object[]{RegraNegocioConstantes.REGISTRO_ATIVO};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param , new rowMapper());
	}
	
	public List<PlanoVO> pesquisarPlanosByCodigo(Integer codigo) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_plano, nm_plano, descricao, valor from plano ");
		sb.append(" where fk_status = ? and pk_plano = ? ");
		sb.append(" order by 2 ");
		
		Object[] param = new Object[]{RegraNegocioConstantes.REGISTRO_ATIVO, codigo};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param , new rowMapper());
	}
	
	private static final class rowMapper implements RowMapper<PlanoVO> {
		public PlanoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			PlanoVO plano = new PlanoVO();
			plano.setCodigo(rs.getInt("pk_plano"));
			plano.setNome(rs.getString("nm_plano"));
			plano.setDescricao(rs.getString("descricao"));
			plano.setValor(rs.getDouble("valor"));
			
			return plano;
		}
	}
}
