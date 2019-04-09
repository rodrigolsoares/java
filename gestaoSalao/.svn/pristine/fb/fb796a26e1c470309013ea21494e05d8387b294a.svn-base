package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.ClienteDao;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Repository
public class ClienteDaoJdbc extends SpringDao implements ClienteDao {
	

	public void inseriCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();

		sb.append(" insert into cliente ( pk_cliente, nm_cliente, sexo, rg, cpf, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_comercial, tel_recado, e_mail, ");
		sb.append(" fk_responsavel_salao, fk_salao, fk_usuario");
		sb.append("	) values( ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ? , ?, ?, ?, ? , ?, ?, ?, ?)" );
			
		Object[] param = new Object[]{
			Constantes.ZERO,	
			cliente.getNome(),
			cliente.getSexo(),
			cliente.getRg(),
			cliente.getCpf(),
			cliente.getLogradouro(),
			cliente.getNumero(),
			cliente.getBairro(),
			cliente.getComplemento(),
			cliente.getCidade(),
			cliente.getEstado(),
			cliente.getTelResidencial(),
			cliente.getTelCelular(),
			cliente.getTelComercial(),
			cliente.getTelRecado(),
			cliente.getEmail(),
			usuario.getResponsavel().getCodigo(),
			usuario.getSalao().getCodigo(),
			usuario.getCodigo()
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);	
	
	}

	public void atualizaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception{
		
		
		StringBuffer sb = new StringBuffer();
	
		sb.append(" update cliente set ");
		sb.append(" nm_cliente = ? ,");
		sb.append(" sexo = ? ,");
		sb.append(" rg = ? ,");
		sb.append(" cpf = ? ,");
		sb.append(" logradouro = ?, ");
		sb.append(" numero = ? ,");
		sb.append(" bairro = ? ,");
		sb.append(" complemento = ?, ");
		sb.append(" cidade = ?, ");
		sb.append(" estado = ? ,");
		sb.append(" tel_residencial = ?, ");
		sb.append(" tel_celular = ? ,");
		sb.append(" tel_Comercial = ? ,");
		sb.append(" tel_recado = ? ,");
		sb.append(" e_mail = ? ,");
		sb.append(" fk_usuario = ? ");
		sb.append("where pk_cliente = ? ");
		
		Object[] param = new Object[]{
				cliente.getNome(),
				cliente.getSexo(),
				cliente.getRg(),
				cliente.getCpf(),
				cliente.getLogradouro(), 
				cliente.getNumero(),
				cliente.getBairro(),
				cliente.getComplemento(),
				cliente.getCidade(),
				cliente.getEstado(),
				cliente.getTelResidencial(),
				cliente.getTelCelular(),
				cliente.getTelComercial(),
				cliente.getTelRecado(),
				cliente.getEmail(),
				usuario.getCodigo(),
				cliente.getCodigo()
			
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);	

	}
	
	public boolean verificaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception{

		StringBuffer sb = new StringBuffer();		
		sb.append(" select pk_cliente from cliente where nm_cliente = ? ");
		sb.append(" and fk_responsavel_salao = ? and fk_salao = ?");
		
		if (cliente.getFlagOperacao() == RegraNegocioConstantes.PESQUISAR_REGISTRO_INCLUINDO_A_CHAVE){
			sb.append(" and pk_cliente <> " + cliente.getCodigo());
		}
		
		Object[] param = new Object[]{
				cliente.getNome(),
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo()
		};
			
		try{
			
			getJdbcTemplate().queryForInt(sb.toString().toLowerCase(), param);
			return false;
			
		} catch (EmptyResultDataAccessException e){
			return false;
		}
	}

	public List<ClienteVO> pesquisaCliente(ClienteVO cliente, UsuarioVO usuario) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select ");
		sb.append(" pk_cliente, nm_cliente, sexo, rg, cpf, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_comercial, tel_recado, e_mail ");
		sb.append(" from cliente Where fk_responsavel_salao = ? and fk_salao = ? ");
		
		if(!"".equals(cliente.getNome()) && cliente.getNome() != null)
			sb.append(" and nm_cliente like '%" + cliente.getNome() + "%' ");
		
		if(!"".equals(cliente.getRg()) && cliente.getRg() != null)
			sb.append(" and rg like '%" + cliente.getRg() + "%' ");
		
		if(!"".equals(cliente.getCpf()) && cliente.getCpf() != null)
			sb.append(" and cpf like '%" + cliente.getCpf() + "%' ");
		
		Object[] param = new Object[]{
				usuario.getResponsavel().getCodigo(),
				usuario.getSalao().getCodigo()
		};
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapperCliente());
	}

	public ClienteVO pesquisaClientePorCodigo(Integer codigo) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select ");
		sb.append(" pk_cliente, nm_cliente, sexo, rg, cpf, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_comercial, tel_recado, e_mail ");
		sb.append(" from cliente Where pk_cliente = ? ");
		
		try{
			
			return (ClienteVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{codigo},new rowMapperCliente()).get(0);
		
		} catch (EmptyResultDataAccessException e){
			return new ClienteVO();
		}
	}
	
	
	public ClienteVO pesquisaClientePorAppAndroid(String numeroTelefone, Integer codigoSalao) throws Exception{

		StringBuffer sb = new StringBuffer();
		sb.append(" select ");
		sb.append(" pk_cliente, nm_cliente, sexo, rg, cpf, logradouro, numero, ");
		sb.append(" bairro, complemento, cidade, estado, tel_residencial, tel_celular, tel_comercial, tel_recado, e_mail ");
		sb.append(" from cliente Where tel_celular = ? and  fk_salao = ? ");
		
		try{
			
			return (ClienteVO)getJdbcTemplate().query(sb.toString().toLowerCase(), new Object[]{numeroTelefone, codigoSalao},new rowMapperCliente()).get(0);
		
		} catch (EmptyResultDataAccessException e){
			return new ClienteVO();
		}
	}
	
	
	

	public List<AgendaVO> pesquisaHistoricoServicoCliente(ClienteVO cli, UsuarioVO usuario, boolean somenteFechados) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select a.pk_agenda, c.nm_cliente, a.data_agendamento, a.hora_agendamento, fa.nm_forma_pagamento, ");
		sb.append(" sa.nm_status, a.valor_servico, nm_servico, f.nm_funcionario, s.nm_servico, f2.nm_funcionario funcPrincipal, ");
		sb.append(" valor_desconto_servico, valor_total_servico  "); 
		sb.append(" from agenda_ajudante aju inner join funcionario f on aju.fk_funcionario = f.pk_funcionario ");
		sb.append(" right join agenda a on aju.fk_agenda = a.pk_agenda ");
		sb.append(" inner join cliente c on a.fk_cliente = c.pk_cliente ");
		sb.append(" inner join status_atendimento sa on a.fk_status_atendimento = sa.pk_status_atendimento ");
		sb.append(" inner join forma_pagamento fa on a.fk_forma_pagamento = fa.pk_forma_pagamento ");
		sb.append(" inner join agenda_servico ase on a.pk_agenda = ase.fk_agenda ");
		sb.append(" inner join servico s on ase.fk_servico = s.pk_servico ");
		sb.append(" inner join funcionario f2 on f2.pk_funcionario = a.fk_funcionario ");
		sb.append(" where a.FK_cliente= ? " ); 
		sb.append("   and a.data_agendamento between ? and ? ");
		
		if(somenteFechados)
			sb.append("   and a.fk_status_atendimento = 3 ");
		
		if (cli.getFlagStatusPagamento() == RegraNegocioConstantes.SERVICO_DIFERENTE_DE_STATUS_PENDENTE)
			sb.append("  and a.fk_forma_pagamento <> 5 ");
		if (cli.getFlagStatusPagamento() == RegraNegocioConstantes.SERVICO_STATUS_PENDENTE)
			sb.append("  and a.fk_forma_pagamento = 5 ");
		
		Object[] param = new Object[]{
				cli.getCodigo(), 
				DataUtil.converteDataSql(cli.getDataInicial()), 
				DataUtil.converteDataSql(cli.getDataFinal())
		};
		
		sb.append(" order by a.data_agendamento, sa.nm_status, a.hora_agendamento ");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param,  new rowMappeHistoricoServicoCliente());
		
		
	}
	
	private static final class rowMapperCliente implements RowMapper<ClienteVO> {
		public ClienteVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ClienteVO cli = new ClienteVO();
			cli.setCodigo(rs.getInt("pk_cliente"));
			cli.setNome(rs.getString("nm_cliente"));
			cli.setSexo(rs.getString("sexo"));
			cli.setRg(rs.getString("rg"));
			cli.setCpf(rs.getString("cpf"));
			cli.setLogradouro(rs.getString("logradouro"));
			cli.setNumero(rs.getString("numero"));
			cli.setBairro(rs.getString("bairro"));
			cli.setComplemento(rs.getString("complemento"));
			cli.setCidade(rs.getString("cidade"));
			cli.setEstado(rs.getString("estado"));
			cli.setTelResidencial(rs.getString("tel_residencial"));
			cli.setTelCelular(rs.getString("tel_celular"));
			cli.setTelRecado(rs.getString("tel_recado"));
			cli.setEmail(rs.getString("e_mail"));
			cli.setTelComercial(rs.getString("tel_comercial"));
			
			return cli;
		}
	}
	
	private static final class rowMappeHistoricoServicoCliente implements RowMapper<AgendaVO>{
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
			ag.setDesconto(rs.getDouble("valor_desconto_servico"));
			ag.setValorTotal(rs.getDouble("valor_total_servico"));
			ag.setNomeServico(rs.getString("nm_servico"));
			ag.setNomeAjudante(rs.getString("nm_funcionario"));

			return ag;
		}
	}
	


}
