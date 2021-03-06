package br.com.gestao.salao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.SalaoDao;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.ResponsavelVO;
import br.com.gestao.salao.vo.SalaoVO;


@Repository
public class SalaoDaoJdbc extends SpringDao implements SalaoDao {
	
	public Integer inserir( final SalaoVO salao){
		
		
		StringBuffer sb = new StringBuffer();		
		sb.append("insert into salao (pk_salao, fk_responsavel_salao, nm_fantasia_salao, nm_razao_social_salao, cnpj, INS_Estadual, ");
		sb.append(" logradouro, numero, bairro, complemento, cidade, cep, estado, tel_1, tel_2, ");
		sb.append(" fk_status, habilita_ajudante) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		final String sql = sb.toString();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(
				new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps = connection.prepareStatement(sql.toLowerCase(), new String[] {"pk_salao"});
			            ps.setString(1,Constantes.ZERO);
			            ps.setInt(2, salao.getResponsavel().getCodigo());
			            ps.setString(3, salao.getNomeFantasia());
			            ps.setString(4, salao.getRazaoSocial());
			            ps.setString(5, salao.getCnpj());
			            ps.setString(6, salao.getInscEstadual());
			            ps.setString(7, salao.getLogradouro());
			            ps.setString(8, salao.getNumero());
			            ps.setString(9, salao.getBairro());
			            ps.setString(10, salao.getComplemento());
			            ps.setString(11, salao.getCidade());
			            ps.setString(12, salao.getCep());
			            ps.setString(13, salao.getEstado());
			            ps.setString(14, salao.getTel1());
			            ps.setString(15, salao.getTel2());
			            ps.setInt(16, RegraNegocioConstantes.REGISTRO_ATIVO);
			            ps.setString(17, salao.getHabilitaAjudante());
			            return ps;
			        }
			    }
				, keyHolder);
		
		return keyHolder.getKey().intValue();
		
	}
	
	
	public List<SalaoVO> pesquisaSalaoCliente(ClienteVO cliente) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select s.pk_salao, s.fk_responsavel_salao, s.nm_fantasia_salao, s.nm_razao_social_salao, ");
		sb.append("   s.cnpj, s.ins_estadual, s.logradouro, s.numero, s.bairro, s.complemento, s.cidade, ");
		sb.append("   s.cep, s.estado, s.tel_1, s.tel_2, s.habilita_ajudante, s.fk_status ");
		sb.append("  from salao s, cliente c ");
		sb.append("   where s.pk_salao = c.fk_salao ");
		sb.append(" and c.tel_celular = ? ");
		sb.append(" and s.fk_status = 1 ");
		
		Object[] param = new Object[]{
				cliente.getTelCelular()
		 };
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(), param, new rowMapper());
	}
	
	
	private static final class rowMapper implements RowMapper<SalaoVO> {
		public SalaoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			SalaoVO sa = new SalaoVO();
			sa.setCodigo(rs.getInt("pk_salao"));
			sa.setNomeFantasia(rs.getString("nm_fantasia_salao"));
			ResponsavelVO responsavel = new ResponsavelVO();
			responsavel.setCodigo(rs.getInt("fk_responsavel_salao"));
			
			sa.setResponsavel(responsavel);
			
			return sa;
		}
	}


}
