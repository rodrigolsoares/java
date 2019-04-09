package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.dao.PerfilDao;
import br.com.gestao.salao.vo.PerfilVO;

@Repository
public class PerfilDaoJdbc  extends SpringDao implements PerfilDao{
	
	public List<PerfilVO> pesquisaPerfil() throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append("select pk_perfil, nm_perfil from perfil order by 2");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
		
	}
	
	private static final class rowMapper implements RowMapper<PerfilVO> {
		public PerfilVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			PerfilVO perfil = new PerfilVO();
			perfil.setCodigo(rs.getInt("pk_perfil"));
			perfil.setNome(rs.getString("nm_perfil"));
			
			return perfil;
		}
	}

}
