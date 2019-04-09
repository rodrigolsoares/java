package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.dao.FormaPagamentoDao;
import br.com.gestao.salao.vo.FormaPagamentoVO;

@Repository
public class FormaPagamentoDaoJdbc extends SpringDao implements FormaPagamentoDao{
	
	public List<FormaPagamentoVO> pesquisaFormaPagamento() throws Exception{
	
		StringBuffer sb = new StringBuffer();
		sb.append("select pk_forma_pagamento, nm_forma_pagamento from forma_pagamento order by 2");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
	}
	
	private static final class rowMapper implements RowMapper<FormaPagamentoVO> {
		public FormaPagamentoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			FormaPagamentoVO fpRegistro = new FormaPagamentoVO();
			fpRegistro.setCodigo(rs.getInt("pk_forma_pagamento"));
			fpRegistro.setNomeFormaPagamento(rs.getString("nm_forma_pagamento"));
			
			return fpRegistro;
		}
	}
	
}
