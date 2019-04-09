package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.dao.StatusRegistroDao;
import br.com.gestao.salao.vo.StatusRegistroVO;

@Repository
public class StatusRegistroDaoJdbc extends SpringDao implements StatusRegistroDao{

	public List<StatusRegistroVO> pesquisaStatus() throws Exception{
				
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_status, nm_status from status_registro order by 2");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
	}
	
	private static final class rowMapper implements RowMapper<StatusRegistroVO> {
		public StatusRegistroVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			StatusRegistroVO stRegistro = new StatusRegistroVO();
			stRegistro.setCodigo(rs.getInt("pk_status"));
			stRegistro.setNomeStatus(rs.getString("nm_status"));
			
			return stRegistro;
		}
	}
	
}
