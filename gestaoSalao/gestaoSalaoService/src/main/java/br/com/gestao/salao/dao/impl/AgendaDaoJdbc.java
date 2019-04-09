package br.com.gestao.salao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.dao.AgendaDao;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;


@Repository
public class AgendaDaoJdbc  extends SpringDao implements AgendaDao {

	public Integer inserirAgenda( final AgendaVO agenda, final UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into agenda(pk_agenda, fk_cliente, fk_funcionario,  ");
		sb.append(" fk_status_atendimento, fk_forma_pagamento, valor_servico, Data_Agendamento, Hora_Agendamento," );
		sb.append(" valor_desconto_servico, valor_total_servico, data_compensa_cheque, porcentagem, valor_caixinha, ");
		sb.append(" fk_responsavel_salao, fk_salao, fk_usuario ,fk_usuario_android, fk_tipo_atend) ");
		sb.append("	values( 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
		
		final String sql = sb.toString().toLowerCase();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(
				new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps = connection.prepareStatement(sql.toLowerCase(), new String[] {"pk_agenda"});
			            ps.setInt(1,agenda.getCodigoCliente());
						ps.setInt(2,agenda.getCodigoFuncionario());
						ps.setInt(3,agenda.getCodigoAtendimento());
						ps.setInt(4,agenda.getCodigoPagamento());
						ps.setDouble(5,agenda.getValor());
						ps.setDate(6, new java.sql.Date(agenda.getData().getTime()));
						ps.setString(7,agenda.getHora());
						ps.setDouble(8,agenda.getDesconto());
						ps.setDouble(9,agenda.getValorTotal());
						
						if(agenda.getDataCompensaCheque() != null){
							ps.setDate(10,new java.sql.Date(agenda.getDataCompensaCheque().getTime()));
						}else{
							ps.setDate(10,null);
			        	}
						
						ps.setInt(11,agenda.getPorcentagem());
						ps.setDouble(12,agenda.getCaixinha());
						ps.setInt(13, usuario.getResponsavel().getCodigo());
						ps.setInt(14, usuario.getSalao().getCodigo());
						
						if(usuario.getCodigo() != null){
							ps.setInt(15, usuario.getCodigo());
						}else{
							ps.setString(15, null);	
						}
						
						ps.setString(16, null );
						
						ps.setInt(17, agenda.getTipoAtendimento());
			            return ps;
			        }
			    }
				, keyHolder);
		
		return keyHolder.getKey().intValue();

	}

	public void atualizaAgenda(AgendaVO agenda, UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
	
		sb.append(" update agenda set ");
		sb.append(" fk_cliente = ?, ");
		sb.append(" fk_funcionario = ?,  ");
		sb.append(" fk_status_atendimento = ? ,");
		sb.append(" fk_forma_pagamento = ? ,");
		sb.append(" valor_servico = ? ,");
		sb.append(" Data_Agendamento = ? , ");
		sb.append(" Hora_Agendamento = ?, ");
		sb.append(" valor_desconto_servico = ?, ");
		sb.append(" valor_total_servico = ?, ");
		sb.append(" data_compensa_cheque = ?, ");
		sb.append(" porcentagem = ? , ");
		sb.append(" valor_caixinha = ? ,");
		sb.append(" fk_usuario = ? ");
		sb.append(" where pk_agenda= ? ");
		
		Object[] param = new Object[]{
				agenda.getCodigoCliente(),
				agenda.getCodigoFuncionario(),
				agenda.getCodigoAtendimento(),
				agenda.getCodigoPagamento(),
				agenda.getValor(),
				agenda.getData(),
				agenda.getHora(),
				agenda.getDesconto(),
				agenda.getValorTotal(),
				agenda.getDataCompensaCheque(),
				agenda.getPorcentagem(),
				agenda.getCaixinha(),
				usuario.getCodigo(),
				agenda.getCodigo()
				
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase().toLowerCase(), param);	

	}

	public void excluirRegistroAgenda(AgendaVO agenda) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" delete from agenda where pk_agenda = ? ");
		
		Object[] param = new Object[]{
				agenda.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}

	public void excluirRegistrosAgendaServico(AgendaVO agenda) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from agenda_servico where fk_agenda = ? ");
		
		Object[] param = new Object[]{
				agenda.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}

	public void excluirRegistrosAjudante(AgendaVO agenda) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from agenda_ajudante where fk_agenda = ? ");
		
		Object[] param = new Object[]{
				agenda.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
			
	}

	public void inseriServico(String codigoServico, int codigoAgenda, UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into agenda_servico (pk_agenda_servico, fk_agenda, fk_servico, ");
		sb.append(" fk_responsavel_salao, fk_salao, fk_usuario) values(0,?,?, ?, ?, ?)  ");
			
		Object[] param = new Object[]{
				codigoAgenda,
				codigoServico,
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo(),
				usuario.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
			
	}

	public void inseriAjudante(String codigoAjudante, Integer porcentagem, AgendaVO ag, UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();

		sb.append(" insert into agenda_ajudante (pk_agenda_ajudante, fk_agenda, ");
		sb.append(" fk_funcionario, tipo_rateio, porcentagem, fk_responsavel_salao, fk_salao, fk_usuario)");
		sb.append(" values(0,?,?,?,?, ?,?,?)  ");
		
		Object[] param = new Object[]{
				ag.getCodigo(),
				codigoAjudante,
				ag.getTipoRateio(),
				porcentagem,
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo(),
				usuario.getCodigo()
		};
			
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}
	
	public List<AgendaVO> pesquisaAgendaEncaixe(Date sData, Integer codigoResponsavel, Integer codigoSalao, Integer tipoAtendimento) throws Exception{
		
		StringBuffer sb = queryAgenda();
		sb.append(" and a.fk_responsavel_salao = ? and a.fk_salao = ? ");
		sb.append(" and data_agendamento = ?");
		sb.append(" and fk_tipo_atend = ? ");
		
		Object[] param = new Object[]{
				codigoResponsavel,
				codigoSalao,
				new java.sql.Date(sData.getTime()),
				tipoAtendimento
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param ,new rowMapperAgenda());
		
		
	}

	public AgendaVO pesquisaAgendaPorCodigo(Integer codigo) throws Exception{
		
		StringBuffer sb = queryAgenda();
		sb.append("  and a.pk_agenda = ? " );
		
		try{
			
			return (AgendaVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{codigo},new rowMapperAgenda()).get(0);
		
		} catch (EmptyResultDataAccessException e){
			return new AgendaVO();
		}
	}

	public List<String> pesquisaServicoAgendaPorCodigo(Integer codigoAgenda) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select fk_servico, valor from agenda_servico, servico ");
		sb.append(" where  fk_servico = pk_servico and fk_agenda = ? ");
		
		Object[] param = new Object[]{codigoAgenda };

		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperServicoAgendaPorCodigo());

	}

	public List<String> pesquisaAjudanteAgendaPorCodigo(Integer codigoAgenda) throws Exception{

		StringBuffer sb = new StringBuffer();		
		sb.append(" select fk_funcionario, porcentagem from agenda_ajudante where fk_agenda = ? " );
		
		Object[] param = new Object[]{codigoAgenda };
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperAjudanteAgendaPorCodigo());
		
	}

	public Integer pesquisaRateioPorCodigo(Integer codigoAgenda) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select tipo_rateio from agenda_ajudante where fk_agenda = ? " );
		
		Object[] param = new Object[]{ codigoAgenda	};
		
		return getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), param);
		
	
	}

	public List<FuncionarioVO> pesquisaAgenda(Date sData, Integer codigoResponsavel, Integer codigoSalao, Integer tipoAtendimento, Integer codigoFuncionario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_agenda, pk_funcionario, hora_agendamento, Data_agendamento, valor_servico,  nm_funcionario, pk_cliente, nm_Cliente, ");
		sb.append(" fk_status_atendimento, f.porcentagem , s.sigla ");
		sb.append(" from agenda a, funcionario f, cliente c , agenda_servico ags, servico s ");
		sb.append(" where a.fk_funcionario = f.pk_funcionario ");
		sb.append(" and a.fk_cliente = c.pk_cliente ");
		sb.append(" and s.pk_servico = ags.fk_servico ");
		sb.append(" and a.pk_agenda = ags.fk_agenda ");
		sb.append(" and a.fk_responsavel_salao = ? and a.fk_salao = ? ");
		sb.append(" and data_agendamento = ?");
		sb.append(" and fk_tipo_atend = ? ");
		
		if(codigoFuncionario != null){
			sb.append("  and a.fk_funcionario = " + codigoFuncionario );
		}
		
		Object[] param = new Object[]{
				codigoResponsavel,
				codigoSalao,
				DataUtil.converteDataSql(sData),
				tipoAtendimento
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperFuncionarioAgenda());
		
	}

	public List<FuncionarioVO> pesquisaAgendaAjudante(Date sData, Integer codigoResponsavel, Integer codigoSalao, Integer tipoAtendimento, Integer codigoFuncionario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select pk_agenda, aa.fk_funcionario, a.hora_agendamento, a.Data_agendamento, ");
		sb.append("        valor_servico,  nm_funcionario, pk_cliente, nm_Cliente,  fk_status_atendimento, f.porcentagem , s.sigla ");
		sb.append(" from agenda a, cliente c, agenda_ajudante aa, funcionario f, servico s, agenda_servico ase ");
		sb.append(" where a.pk_agenda = aa.fk_agenda ");
		sb.append("  and  aa.fk_funcionario = f.pk_funcionario ");
		sb.append("  and a.fk_cliente = c.pk_cliente ");
		sb.append("  and a.pk_agenda = ase.fk_agenda ");
		sb.append(" and ase.fk_servico = s.pk_servico ");
		sb.append(" and a.fk_responsavel_salao = ? and a.fk_salao = ? ");
		sb.append("  and a.data_agendamento = ? " );
		sb.append("  and a.fk_tipo_atend = ? " );
		
		if(codigoFuncionario != null){
			sb.append("  and a.fk_funcionario = " + codigoFuncionario );
		}
		
		Object[] param = new Object[]{
				codigoResponsavel,
				codigoSalao,
				sData, tipoAtendimento
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param ,new rowMapperFuncionarioAgendaAjudante());

	}
	
	public List<FuncionarioVO> pesquisaFuncionarioAgenda(Integer codigoResponsavel, Integer codigoSalao, Integer codigoFuncionario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_funcionario, nm_funcionario, porcentagem from funcionario ");
		sb.append(" where fk_status = 1 ");
		sb.append(" and fk_responsavel_salao = ? and fk_salao = ?");
		
		if(codigoFuncionario != null){
			sb.append("  and pk_funcionario = " + codigoFuncionario );
		}
		
		Object[] param = new Object[]{
				codigoResponsavel,
				codigoSalao
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase() , param, new rowMapperFuncionario());
		
	}
	
	private StringBuffer queryAgenda() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.pk_agenda, a.fk_funcionario, a.fk_cliente, f.nm_funcionario, c.nm_cliente, valor_servico,  ");
		sb.append("   a.fk_status_atendimento, a.fk_forma_pagamento, a.data_agendamento, a.hora_agendamento,  ");
		sb.append("   a.valor_total_servico, a.data_compensa_cheque, a.valor_desconto_servico, valor_caixinha, ");
		sb.append("   fp.nm_Forma_pagamento,   sa.nm_status ");
		sb.append(" from agenda a, funcionario f, cliente c, forma_pagamento fp , status_atendimento sa  ");
		sb.append(" where a.fk_funcionario = f.pk_funcionario  ");
		sb.append("  and a.fk_cliente = c.pk_cliente ");
		sb.append("  and a.FK_Forma_Pagamento = fp.PK_Forma_Pagamento ");
		sb.append("		  and a.FK_Status_Atendimento = sa.PK_Status_Atendimento ");
		return sb;
	}
	
	private static final class rowMapperAgenda implements RowMapper<AgendaVO> {
		public AgendaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AgendaVO ag = new AgendaVO();
			ag.setCodigo(rs.getInt("pk_agenda"));
			ag.setCodigoFuncionario(rs.getInt("fk_funcionario"));
			ag.setCodigoCliente(rs.getInt("fk_cliente"));
			ag.setNomeCliente(rs.getString("nm_cliente"));
			ag.setNomeFuncionario(rs.getString("nm_funcionario"));
			ag.setCodigoAtendimento(rs.getInt("fk_status_atendimento"));
			ag.setCodigoPagamento(rs.getInt("fk_forma_pagamento"));
			ag.setData(rs.getDate("data_agendamento"));
			ag.setHora(rs.getString("hora_agendamento"));
			ag.setValor(rs.getDouble("valor_servico"));
			ag.setValorTotal(rs.getDouble("valor_total_servico"));
			ag.setDataCompensaCheque(rs.getDate("data_compensa_cheque"));
			ag.setDesconto(rs.getDouble("valor_desconto_servico"));
			ag.setCaixinha(rs.getDouble("valor_caixinha"));
			ag.setNomeStatus(rs.getString("nm_status"));
			ag.setNomePagamento(rs.getString("nm_Forma_pagamento"));
			return ag;
		}
	}
	
	private static final class rowMapperFuncionarioAgenda implements RowMapper<FuncionarioVO> {
		public FuncionarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			FuncionarioVO func = new FuncionarioVO();
			func.setCodigo(rs.getInt("pk_funcionario"));
			func.setNome(rs.getString("nm_funcionario"));	
			func.setPorcentagem(rs.getInt("porcentagem"));
			AgendaVO agenda = new AgendaVO();
			agenda.setCodigo(rs.getInt("pk_agenda"));
			agenda.setCodigoCliente(rs.getInt("pk_cliente"));
			agenda.setCodigoFuncionario(rs.getInt("pk_funcionario"));
			agenda.setHora(rs.getString("hora_agendamento"));
			agenda.setData(rs.getDate("Data_agendamento"));
			agenda.setNomeCliente(rs.getString("nm_Cliente"));
			agenda.setNomeFuncionario(rs.getString("nm_funcionario"));
			agenda.setCodigoAtendimento(rs.getInt("fk_status_atendimento"));
			agenda.setPorcentagem(rs.getInt("porcentagem"));
			agenda.setFlagAjudante("0");
			agenda.setSigla(rs.getString("sigla"));
			func.setAgenda(agenda);
		
			return func;
		}
	}
	
	private static final class rowMapperFuncionarioAgendaAjudante implements RowMapper<FuncionarioVO> {
		public FuncionarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {

			FuncionarioVO func = new FuncionarioVO();
			func.setCodigo(rs.getInt("fk_funcionario"));
			func.setPorcentagem(rs.getInt("porcentagem"));
			AgendaVO agenda = new AgendaVO();
			agenda.setCodigo(rs.getInt("pk_agenda"));
			agenda.setCodigoCliente(rs.getInt("pk_cliente"));
			agenda.setCodigoFuncionario(rs.getInt("fk_funcionario"));
			agenda.setHora(rs.getString("hora_agendamento"));
			agenda.setData(rs.getDate("Data_agendamento"));
			agenda.setNomeCliente(rs.getString("nm_Cliente"));
			agenda.setNomeFuncionario(rs.getString("nm_funcionario"));
			agenda.setCodigoAtendimento(rs.getInt("fk_status_atendimento"));
			agenda.setFlagAjudante("1");
			agenda.setSigla(rs.getString("sigla"));
			func.setAgenda(agenda);
		
			return func;
		}
	}
	
	private static final class rowMapperServicoAgendaPorCodigo implements RowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return  rs.getString("fk_servico");
		}
	}
	
	private static final class rowMapperAjudanteAgendaPorCodigo implements RowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("fk_funcionario");
		}
	}
	
	private static final class rowMapperFuncionario implements RowMapper<FuncionarioVO> {
		public FuncionarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			FuncionarioVO func = new FuncionarioVO();
			func.setCodigo(rs.getInt("pk_funcionario"));
			func.setNome(rs.getString("nm_funcionario"));
			func.setPorcentagem(rs.getInt("porcentagem"));
			return func;
		}
	}
	
	
	
	

}
