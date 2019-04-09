package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.FluxoCaixaDao;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class FluxoCaixaDaoJdbc extends SpringDao implements FluxoCaixaDao {

	public List<AgendaVO> pesquisaAgendaServico(Date dataInicio, Date dataFinal, int fk_status, UsuarioVO usuario ) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select valor_servico, c.nm_cliente, pk_cliente, valor_desconto_servico, valor_total_servico   ");
		sb.append(" from agenda a, cliente c ");
		sb.append(" where a.fk_cliente = c.pk_cliente ");
		sb.append("  and a.data_agendamento between '" + DataUtil.converteDataSql(dataInicio) + "' ");
		sb.append("  and '" + DataUtil.converteDataSql(dataFinal) + "' ");
		sb.append("  and a.fk_salao= " + usuario.getSalao().getCodigo() );
		sb.append("  and a.fk_status_atendimento = 3 ");
		
		if (fk_status == RegraNegocioConstantes.SERVICO_DIFERENTE_DE_STATUS_PENDENTE)
			sb.append("  and a.fk_forma_pagamento <> 5 ");
		else
			sb.append("  and a.fk_forma_pagamento = 5 ");
			
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), new rowMapperAgendaServico());
		
	}

	public List<AgendaVO> pesquisaComissaoServicoPrincipal(Date dataInicio, Date dataFinal, UsuarioVO usuario ) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select distinct a.pk_agenda, f.nm_funcionario, a.valor_servico, a.porcentagem, ");
		sb.append("   f.pk_funcionario,  valor_desconto_servico, valor_total_servico, tipo_rateio, a.valor_caixinha ");
		sb.append(" from agenda a ");
		sb.append(" left join agenda_ajudante aj on a.pk_agenda = aj.fk_agenda ");
		sb.append(" inner join funcionario f on a.fk_funcionario = f.pk_funcionario ");
		sb.append(" where a.data_agendamento between '" + DataUtil.converteDataSql(dataInicio) + "' ");
		sb.append("  and '" + DataUtil.converteDataSql(dataFinal) + "' ");
		sb.append("  and a.fk_status_atendimento = 3 ");
		sb.append("  and a.fk_salao = " + usuario.getSalao().getCodigo());
	    
		return getJdbcTemplate().query(sb.toString().toLowerCase(), new rowMapperComissaoServicoAjudante());

		
	}

	public List<AgendaVO> pesquisaComissaoServicoAjudante(Date dataInicio, Date dataFinal, UsuarioVO usuario ) throws Exception{

		StringBuffer sb = new StringBuffer();	
		sb.append(" select f.pk_funcionario,f.nm_funcionario, a.valor_servico, aj.porcentagem, aj.tipo_rateio , pk_agenda, ");
		sb.append(" valor_desconto_servico, valor_total_servico, 0 valor_caixinha ");
		sb.append(" from agenda a, funcionario f, agenda_ajudante aj ");
		sb.append(" where aj.fk_funcionario = f.pk_funcionario ");
		sb.append(" and aj.fk_agenda = a.pk_agenda ");
		sb.append("  and a.fk_status_atendimento = 3 ");
		sb.append("  and a.data_agendamento between ?  and ? ");
		sb.append("  and a.fk_salao = ? ");
		
		Object[] param = new Object[]{
				DataUtil.converteDataSql(dataInicio),
				DataUtil.converteDataSql(dataFinal),
				usuario.getSalao().getCodigo()
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperComissaoServicoAjudante());
		
		
	}
	
	public List<AgendaVO> pesquisaComissaoServicoAjudantePorFunc(Date dataInicio, Date dataFinal, Integer codigo, UsuarioVO usuario  ) throws Exception{

		StringBuffer sb = new StringBuffer();			
		sb.append(" select f.nm_funcionario, a.valor_servico, aj.porcentagem, aj.tipo_rateio , pk_agenda, ");
		sb.append(" a.hora_agendamento, DATE_FORMAT(a.data_agendamento,'%d/%m/%Y') data_agendamento, nm_cliente, valor_desconto_servico, " );
		sb.append(" valor_total_servico, nm_servico  ");
		sb.append(" from agenda a, funcionario f, agenda_ajudante aj, cliente c , agenda_servico ags, servico s");
		sb.append(" where aj.fk_funcionario = f.pk_funcionario ");
		sb.append(" and a.fk_cliente = c.pk_cliente");
		sb.append(" and aj.fk_agenda = a.pk_agenda ");
		sb.append(" and s.pk_servico = ags.fk_servico ");
		sb.append(" and a.pk_agenda = ags.fk_agenda ");
		sb.append("  and a.fk_status_atendimento = 3 ");
		sb.append(" and a.data_agendamento between '" + DataUtil.converteDataSql(dataInicio) + "' ");
		sb.append(" and '" + DataUtil.converteDataSql(dataFinal) + "' ");
		sb.append(" and aj.fk_funcionario = '"+ codigo+"' ");
		sb.append(" and a.fk_salao = " + usuario.getSalao().getCodigo());
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), new rowMapperComissaoServicoAjudantePorFunc());
		
	}
	
	
	public List<AgendaVO> verificaQtdeAjudante(Date dataInicio, Date dataFinal, UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_agenda, count(*) soma ");
		sb.append(" from agenda a, funcionario f, agenda_ajudante aj ");
		sb.append(" where aj.fk_funcionario = f.pk_funcionario ");
		sb.append("  and aj.fk_agenda = a.pk_agenda ");
		sb.append("  and a.data_agendamento between '" + DataUtil.converteDataSql(dataInicio) + "' ");
		sb.append("  and '" + DataUtil.converteDataSql(dataFinal) + "' ");
		sb.append("  and a.fk_salao = " + usuario.getSalao().getCodigo());
		sb.append("  and a.fk_status_atendimento = 3 ");
		sb.append("  and a.fk_forma_pagamento <> 5   group by pk_agenda");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), new rowMapperverificaQtdeAjudante()); 
		
	}
	
	public List<AgendaVO> pesquisaChequesCompensar(Date dataInicio, Date dataFinal , UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
			
		sb.append(" select valor_servico, c.nm_cliente, valor_total_servico, data_compensa_cheque, ");
		sb.append(" data_agendamento, valor_desconto_servico ");
		sb.append("	 from agenda a, cliente c ");
		sb.append("	 where a.fk_cliente = c.pk_cliente ");
		sb.append("  and a.data_compensa_cheque between ? and ? ");
		sb.append("  and a.fk_salao = ? ");
		sb.append(" order by data_compensa_cheque ");
		
		Object[] param = new Object[]{
				DataUtil.converteDataSql(dataInicio),
				DataUtil.converteDataSql(dataFinal),
				usuario.getSalao().getCodigo()
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapper());
	}
	
	
	private static final class rowMapper implements RowMapper<AgendaVO> {
		public AgendaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			AgendaVO agenda = new AgendaVO();
			agenda.setNomeCliente(rs.getString("nm_Cliente"));
			agenda.setValor(rs.getDouble("valor_servico"));
			agenda.setValorTotal(rs.getDouble("valor_total_servico"));
			agenda.setDataCompensaCheque(rs.getDate("data_compensa_cheque"));
			agenda.setDesconto(rs.getDouble("valor_desconto_servico"));
			agenda.setData(rs.getDate("data_agendamento"));
			
			return agenda;
		}
	}
	
	private static final class rowMapperAgendaServico implements RowMapper<AgendaVO>{
		public AgendaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			AgendaVO agenda = new AgendaVO();
			agenda.setNomeCliente(rs.getString("nm_Cliente"));
			agenda.setCodigoCliente(rs.getInt("pk_cliente"));
			agenda.setValor(rs.getDouble("valor_servico"));
			agenda.setDesconto(rs.getDouble("valor_desconto_servico"));
			agenda.setValorTotal(rs.getDouble("valor_total_servico"));
			
			return agenda;
		}
	}
	
	private static final class rowMapperverificaQtdeAjudante implements RowMapper<AgendaVO>{
		public AgendaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			AgendaVO agenda = new AgendaVO();
			agenda.setCodigo(rs.getInt("pk_agenda"));
			agenda.setQtdeAjudante(rs.getString("soma"));
			
			return agenda;
		}
	}
	
	private static final class rowMapperComissaoServicoAjudante implements RowMapper<AgendaVO>{
		public AgendaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			AgendaVO agenda = new AgendaVO();
			agenda.setNomeFuncionario(rs.getString("nm_funcionario"));
			agenda.setCodigoFuncionario(rs.getInt("pk_funcionario"));
			agenda.setPorcentagem(rs.getInt("porcentagem"));
			agenda.setTipoRateio(rs.getInt("tipo_rateio"));
			agenda.setValor(rs.getDouble("valor_servico"));
			agenda.setDesconto(rs.getDouble("valor_desconto_servico"));
			agenda.setValorTotal(rs.getDouble("valor_total_servico"));
			agenda.setCodigo(rs.getInt("pk_agenda"));
			agenda.setCaixinha(rs.getDouble("valor_caixinha"));
		
			return agenda;
		}
	}
	
	private static final class rowMapperComissaoServicoAjudantePorFunc implements RowMapper<AgendaVO>{
		public AgendaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			AgendaVO agenda = new AgendaVO();
			
			agenda.setNomeFuncionario(rs.getString("nm_funcionario"));
			agenda.setCaixinha(0d);
			agenda.setPorcentagem(rs.getInt("porcentagem"));
			agenda.setTipoRateio(rs.getInt("tipo_rateio"));
			agenda.setCodigo(rs.getInt("pk_agenda"));
			agenda.setValor(rs.getDouble("valor_servico"));
			agenda.setDesconto(rs.getDouble("valor_desconto_servico"));
			agenda.setValorTotal(rs.getDouble("valor_total_servico"));
			agenda.setNomeCliente(rs.getString("nm_cliente"));
			agenda.setHora(rs.getString("hora_agendamento"));
			agenda.setData(rs.getDate("data_agendamento"));
			agenda.setNomeServico(rs.getString("nm_servico"));

			return agenda;
		}
	}
	
	
	
	
	
	
}
