package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.DespesaDao;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.DespesaVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class DespesaJdbc extends SpringDao implements DespesaDao {
	
	public void inseriDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();

		sb.append(" insert into despesa ( pk_despesa, nm_conta, valor, data_vencimento, data_pagamento, fk_status, ");
		sb.append(" fk_responsavel_salao, fk_salao, fk_usuario ) values (?, ?, ?, ?, ? , ? , ?, ?, ? )" );

		Object[] param = new Object[]{
			Constantes.ZERO,
			despesa.getNomeConta(),
			despesa.getValorConta(),
			despesa.getDataVencimento(),
			despesa.getDataPagamento(),
			despesa.getStatus(),
			usuario.getResponsavel().getCodigo(),
			usuario.getSalao().getCodigo(),
			usuario.getCodigo()
		};
				
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}
	
	public void atualizaDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();

		sb.append(" update despesa set ");
		sb.append(" nm_conta = ? ,");
		sb.append(" valor = ? ,");
		sb.append(" data_vencimento = ? ,");
		sb.append(" data_pagamento = ? ,");
		sb.append(" fk_status = ? ,");
		sb.append(" fk_usuario = ?");
		sb.append(" where pk_despesa = ? ");

		Object[] param = new Object[]{
			despesa.getNomeConta(),
			despesa.getValorConta(),
			despesa.getDataVencimento(),
			despesa.getDataPagamento(),
			despesa.getStatus(),
			usuario.getCodigo(),
			despesa.getCodigo()
		};
			
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}

	public boolean verificaDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append("select pK_despesa from despesa where nm_conta = ? ");
		sb.append("  and fk_responsavel_salao = ? and fk_salao = ? and data_pagamento = ? and data_vencimento = ?");
		
		if (despesa.getFlagOperacao() == RegraNegocioConstantes.PESQUISAR_REGISTRO_INCLUINDO_A_CHAVE){
			sb.append(" and pk_despesa <> " + despesa.getCodigo());
		}
		
		Object[] param = new Object[]{
				despesa.getNomeConta(),
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo(),
				DataUtil.converteDataSql(despesa.getDataPagamento()),
				DataUtil.converteDataSql(despesa.getDataVencimento())
		};
			
		try{
			
			getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), param);
			return true;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}
		
	}

	public List<DespesaVO> pesquisaDespesa(DespesaVO despesa, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		
		sb.append(" select pk_despesa, nm_conta, valor, data_vencimento , ");
		sb.append(" data_pagamento , fk_status ");
		sb.append(" from despesa");
		sb.append(" where fk_responsavel_salao = ? and fk_salao = ? ");
		
		if(!"".equals(despesa.getNomeConta()) && despesa.getNomeConta() != null)
			sb.append(" and nm_conta like '%" + despesa.getNomeConta() + "%' ");
		
		if ((!"".equals(despesa.getDataPagamento()) && despesa.getDataPagamento() != null) &&
			(!"".equals(despesa.getDataPagamentoFim()) && despesa.getDataPagamentoFim() != null)) {
			sb.append(" and data_pagamento between '"+ DataUtil.converteDataSql(despesa.getDataPagamento()) +"'");
		    sb.append(" and '" + DataUtil.converteDataSql(despesa.getDataPagamentoFim()) + "' ");
		}
		
		if ((!"".equals(despesa.getDataVencimento()) && despesa.getDataVencimento() != null) &&
				(!"".equals(despesa.getDataVencimentoFim()) && despesa.getDataVencimentoFim() != null)) {
				sb.append(" and data_vencimento between '"+ DataUtil.converteDataSql(despesa.getDataVencimento()) +"'");
			    sb.append(" and '" + DataUtil.converteDataSql(despesa.getDataVencimentoFim()) + "' ");
		}
		
		if (despesa.getStatus() != 0)
			sb.append(" and fk_status = '" + despesa.getStatus() + "' ");
		
		
		Object[] param = new Object[]{
			usuario.getResponsavel().getCodigo(),
			usuario.getSalao().getCodigo()
		};
		
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapper());
	}
	
	public DespesaVO pesquisaDespesaPorCodigo(String codigo, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_despesa, nm_conta, valor, data_vencimento," );
		sb.append(" DATE_FORMAT(data_pagamento , '%d/%m/%Y') data_pagamento, fk_status ");
		sb.append(" from despesa");
		sb.append(" Where pk_despesa = " + codigo);
		
		try{
			return (DespesaVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{codigo},new rowMapper());
		
		} catch (EmptyResultDataAccessException e){
			return new DespesaVO();
		}
	}
	
	private static final class rowMapper implements RowMapper<DespesaVO> {
		public DespesaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			DespesaVO ds = new DespesaVO();
			ds.setCodigo(rs.getString("pk_despesa"));
			ds.setNomeConta(rs.getString("nm_conta"));
			ds.setValorConta(rs.getDouble("valor"));
			ds.setDataPagamento(rs.getDate("data_pagamento"));
			ds.setDataVencimento(rs.getDate("data_vencimento"));
			ds.setStatus(rs.getInt("fk_status"));
			
			return ds;
		}
	}

}
