package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.FuncionarioDao;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.SalaoVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class FuncionarioDaoJdbc  extends SpringDao implements FuncionarioDao{
	
	public void inseriFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{
		
	
		StringBuffer sb = new StringBuffer();
		
	
		sb.append(" insert into funcionario ( pk_funcionario, nm_funcionario, sexo, rg, cpf, crt_trabalho, pis, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_recado1, tel_recado2, ");
		sb.append(" banco, agencia, conta, porcentagem, fk_status, fk_responsavel_salao, fk_salao, fk_usuario ");
		sb.append("	) values( ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ? , ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
		
		Object[] param = new Object[]{
			Constantes.ZERO,
			funcionario.getNome(), 
			funcionario.getSexo(),
			funcionario.getRg(),
			funcionario.getCpf(),
			funcionario.getCrtTrabalho(),
			funcionario.getPis(),
			funcionario.getLogradouro(),
			funcionario.getNumero(),
			funcionario.getBairro(),
			funcionario.getComplemento(),
			funcionario.getCidade(),
			funcionario.getEstado(),
			funcionario.getTelResidencial(),
			funcionario.getTelCelular(),
			funcionario.getTelRecado1(),
			funcionario.getTelRecado2(),
			funcionario.getBanco(),
			funcionario.getAgencia(),
			funcionario.getConta(),
			funcionario.getPorcentagem(),
			funcionario.getStatus(),
			usuario.getResponsavel().getCodigo(),
			usuario.getSalao().getCodigo(),
			usuario.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
			
	}
	
	public void atualizaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{
		

		StringBuffer sb = new StringBuffer();
	
		sb.append(" update funcionario set ");
		sb.append(" nm_funcionario = ? ,");
		sb.append(" sexo = ? ,");
		sb.append(" rg = ? ,");
		sb.append(" cpf = ? ,");
		sb.append(" crt_trabalho = ? ,");
		sb.append(" pis = ? ,");
		sb.append(" logradouro = ?, ");
		sb.append(" numero = ? ,");
		sb.append(" bairro = ? ,");
		sb.append(" complemento = ?, ");
		sb.append(" cidade = ?, ");
		sb.append(" estado = ? ,");
		sb.append(" tel_residencial = ?, ");
		sb.append(" tel_celular = ? ,");
		sb.append(" tel_recado1 = ? ,");
		sb.append(" tel_recado2 = ? ,");
		sb.append(" banco = ? ,");
		sb.append(" agencia = ?, ");
		sb.append(" conta = ? ,");
		sb.append(" porcentagem = ?, ");
		sb.append(" fk_status = ? ,");
		sb.append(" fk_usuario = ? ");
		sb.append("where pk_funcionario = ? ");

		Object[] param = new Object[]{
				funcionario.getNome(), 
				funcionario.getSexo(),
				funcionario.getRg(),
				funcionario.getCpf(),
				funcionario.getCrtTrabalho(),
				funcionario.getPis(),
				funcionario.getLogradouro(),
				funcionario.getNumero(),
				funcionario.getBairro(),
				funcionario.getComplemento(),
				funcionario.getCidade(),
				funcionario.getEstado(),
				funcionario.getTelResidencial(),
				funcionario.getTelCelular(),
				funcionario.getTelRecado1(),
				funcionario.getTelRecado2(),
				funcionario.getBanco(),
				funcionario.getAgencia(),
				funcionario.getConta(),
				funcionario.getPorcentagem(),
				funcionario.getStatus(),
				usuario.getCodigo(),
				funcionario.getCodigo()
			};
			
			getJdbcTemplate().update(sb.toString().toLowerCase(), param);
					
	}

	public boolean verificaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();		
		sb.append("select pk_funcionario from funcionario where nm_funcionario = '" + funcionario.getNome() + "' ");
		
		if (funcionario.getFlagOperacao() == RegraNegocioConstantes.PESQUISAR_REGISTRO_INCLUINDO_A_CHAVE) {
			sb.append(" and pk_funcionario <> " + funcionario.getCodigo());
		}
			
		try{
			
			getJdbcTemplate().queryForInt(sb.toString().toLowerCase());
			return false;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}
	}
	
	public List<FuncionarioVO> pesquisaFuncionario(SalaoVO salao) throws Exception{

		StringBuffer sb = new StringBuffer();		
		sb.append(" select pk_funcionario, nm_funcionario, sexo, rg, cpf, crt_trabalho, pis, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_recado1, tel_recado2, ");
		sb.append(" banco, agencia, conta, porcentagem, fk_status from funcionario ");
		sb.append(" where  fk_salao = ? ");
		
		Object[] param = new Object[]{
				salao.getCodigo()
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperPesquisaFuncionario() );
	}

	public List<FuncionarioVO> pesquisaFuncionario(FuncionarioVO funcionario, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();		
		sb.append(" select pk_funcionario, nm_funcionario, sexo, rg, cpf, crt_trabalho, pis, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_recado1, tel_recado2, ");
		sb.append(" banco, agencia, conta, porcentagem, fk_status from funcionario ");
		sb.append(" where fk_responsavel_salao = ? and fk_salao = ? ");
		
		if(!"".equals(funcionario.getNome()) && funcionario.getNome() != null)
			sb.append(" and nm_funcionario like '%" + funcionario.getNome() + "%' ");
		
		if(!"".equals(funcionario.getRg()) && funcionario.getRg() != null)
			sb.append(" and rg like '%" + funcionario.getRg() + "%' ");
		
		if(!"".equals(funcionario.getCpf()) && funcionario.getCpf() != null)
			sb.append(" and cpf like '%" + funcionario.getCpf() + "%' ");
		
		if (funcionario.getStatus() != 0 )
			sb.append(" and fk_status = '" + funcionario.getStatus() + "' ");
		
		Object[] param = new Object[]{
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo()
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperPesquisaFuncionario() );
	}

	public FuncionarioVO pesquisaFuncionarioPorCodigo(Integer codigo) throws Exception{
		
		StringBuffer sb = new StringBuffer();		
		sb.append(" select pk_funcionario, nm_funcionario, sexo, rg, cpf, crt_trabalho, pis, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_recado1, tel_recado2, ");
		sb.append(" banco, agencia, conta, porcentagem, fk_status from funcionario");
		sb.append(" Where pk_funcionario = ? ");
		
		try{
			Object[] param = new Object[]{ codigo};
			return (FuncionarioVO) getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperPesquisaFuncionario() ).get(0);
		
		} catch (EmptyResultDataAccessException e){
			return new FuncionarioVO();
		}
	}

	public List<FuncionarioVO> pesquisaFuncionarioAjudante(Integer codigo, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append("select * from funcionario where fk_status = 1 and pk_funcionario <> " + codigo + " and fk_status = 1 ");
		sb.append(" and fk_salao = '" + usuario.getSalao().getCodigo() +"'");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), new rowMapperFuncionarioAjudante() );
	}
	

	public List<AgendaVO> pesquisaHistoricoServicoFuncionario(FuncionarioVO func, UsuarioVO usuario, boolean somenteFechados) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select a.pk_agenda, c.nm_cliente, a.data_agendamento, a.hora_agendamento, fa.nm_forma_pagamento, ");
		sb.append(" sa.nm_status, a.valor_servico, nm_servico, f.nm_funcionario, s.nm_servico, fp.porcentagem, aju.tipo_rateio ");
		sb.append(" , fp.nm_funcionario funcPrincipal , valor_desconto_servico, valor_total_servico, a.valor_caixinha ");
		sb.append(" from agenda_ajudante aju inner join funcionario f on aju.fk_funcionario = f.pk_funcionario ");
		sb.append(" right join agenda a on aju.fk_agenda = a.pk_agenda ");
		sb.append(" inner join cliente c on a.fk_cliente = c.pk_cliente ");
		sb.append(" inner join status_atendimento sa on a.fk_status_atendimento = sa.pk_status_atendimento ");
		sb.append(" inner join forma_pagamento fa on a.fk_forma_pagamento = fa.pk_forma_pagamento ");
		sb.append(" inner join agenda_servico ase on a.pk_agenda = ase.fk_agenda ");
		sb.append(" inner join servico s on ase.fk_servico = s.pk_servico ");
		sb.append(" inner join funcionario fp on a.fk_funcionario = fp.pk_funcionario ");
		sb.append(" where a.FK_funcionario= ? and a.data_agendamento between ? and ?  ");
		sb.append(" and a.fk_salao = ?");
		
		if(somenteFechados){
			sb.append(" and a.fk_status_atendimento = 3 ");
		}
		sb.append(" order by a.data_agendamento, sa.nm_status, a.hora_agendamento ");
		
		Object[] param = new Object[]{
			func.getCodigo(),
			DataUtil.converteDataSql(func.getDataInicial()),
			DataUtil.converteDataSql(func.getDataFinal()),
			usuario.getSalao().getCodigo()
			
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperHistServicoFuncionario() );
	}
	
	
	private static final class rowMapperPesquisaFuncionario implements RowMapper<FuncionarioVO> {
		public FuncionarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			FuncionarioVO func = new FuncionarioVO();
			func.setCodigo(rs.getInt("pk_funcionario"));
			func.setNome(rs.getString("nm_funcionario"));
			func.setSexo(rs.getString("sexo"));
			func.setRg(rs.getString("rg"));
			func.setCpf(rs.getString("cpf"));
			func.setCrtTrabalho(rs.getString("crt_trabalho"));
			func.setPis(rs.getString("pis"));
			func.setLogradouro(rs.getString("logradouro"));
			func.setNumero(rs.getString("numero"));
			func.setBairro(rs.getString("bairro"));
			func.setComplemento(rs.getString("complemento"));
			func.setCidade(rs.getString("cidade"));
			func.setEstado(rs.getString("estado"));
			func.setTelResidencial(rs.getString("tel_residencial"));
			func.setTelCelular(rs.getString("tel_celular"));
			func.setTelRecado1(rs.getString("tel_recado1"));
			func.setTelRecado2(rs.getString("tel_recado2"));
			func.setBanco(rs.getString("banco"));
			func.setAgencia(rs.getString("agencia"));
			func.setConta(rs.getString("conta"));
			func.setPorcentagem(rs.getInt("porcentagem"));
			func.setStatus(rs.getInt("fk_status"));
			
			return func;
		}
	}
	
	private static final class rowMapperFuncionarioAjudante implements RowMapper<FuncionarioVO> {
		public FuncionarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			FuncionarioVO func = new FuncionarioVO();
			func.setCodigo(rs.getInt("pk_funcionario") );
			func.setNome(rs.getString("nm_funcionario"));
			func.setPorcentagem(rs.getInt("porcentagem"));
			
			return func;
		}
	}
	
	private static final class rowMapperHistServicoFuncionario implements RowMapper<AgendaVO> {
		public AgendaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			AgendaVO ag = new AgendaVO();
			ag.setCodigo(rs.getInt("pk_agenda"));
			ag.setNomeFuncionario(rs.getString("funcPrincipal"));
			ag.setNomeCliente(rs.getString("nm_cliente"));
			ag.setHora(rs.getString("hora_agendamento"));
			ag.setData(rs.getDate("data_agendamento"));
			ag.setValor(rs.getDouble("valor_servico"));
			ag.setNomeStatus(rs.getString("nm_status"));
			ag.setNomePagamento(rs.getString("nm_forma_pagamento"));
			ag.setTipoRateio(rs.getInt("tipo_rateio"));
			ag.setPorcentagem(rs.getInt("porcentagem"));
			ag.setDesconto(rs.getDouble("valor_desconto_servico"));
			ag.setValorTotal(rs.getDouble("valor_total_servico"));
			ag.setCaixinha(rs.getDouble("valor_caixinha"));
			ag.setNomeServico(rs.getString("nm_servico"));
			ag.setNomeFuncionario(rs.getString("nm_funcionario"));
			
			return ag;
		}
	}
	
	

	

}
