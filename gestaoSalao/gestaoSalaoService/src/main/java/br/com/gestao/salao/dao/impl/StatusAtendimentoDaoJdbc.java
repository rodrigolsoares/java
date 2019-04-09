package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.dao.StatusAtendimentoDao;
import br.com.gestao.salao.vo.StatusAtendimentoVO;

@Repository
public class StatusAtendimentoDaoJdbc extends SpringDao implements StatusAtendimentoDao{
	
	public List<StatusAtendimentoVO> pesquisaStatusAtendimento() throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_status_atendimento, nm_status from status_atendimento order by 2");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
	}
	
	private static final class rowMapper implements RowMapper<StatusAtendimentoVO> {
		public StatusAtendimentoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			StatusAtendimentoVO saRegistro = new StatusAtendimentoVO();
			saRegistro.setCodigo(rs.getInt("pk_status_atendimento"));
			saRegistro.setNome(rs.getString("nm_status"));
			
			return saRegistro;
		}
	}
	
}
