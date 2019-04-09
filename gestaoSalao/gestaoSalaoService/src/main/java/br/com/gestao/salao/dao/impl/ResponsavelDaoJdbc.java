package br.com.gestao.salao.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.dao.ResponsavelDao;
import br.com.gestao.salao.vo.ResponsavelVO;

@Repository
public class ResponsavelDaoJdbc  extends SpringDao implements ResponsavelDao {
	
	public Integer inserir(final ResponsavelVO responsavel) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into responsavel_salao(pk_responsavel_salao, nm_responsavel, sexo, rg, cpf, e_mail, tel_residencial, ");
		sb.append("        tel_Comercial, fk_plano, dt_nasc, dt_experiencia_soft, fk_status, fk_tipo_cobranca, tel_celular ) ");
		sb.append("                      values (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		final String sql = sb.toString().toLowerCase();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(
				new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps = connection.prepareStatement(sql.toLowerCase(), new String[] {"pk_responsavel_salao"});
			            ps.setString(1,responsavel.getNome());
						ps.setString(2,responsavel.getSexo());
						ps.setString(3,responsavel.getRg());
						ps.setString(4,responsavel.getCpf());
						ps.setString(5,responsavel.getEmail());
						ps.setString(6, responsavel.getTelResidencial());
						ps.setString(7,responsavel.getTelComercial());
						ps.setInt(8,responsavel.getPlano().getCodigo());
						
						if(responsavel.getDtNascimento() != null){
							ps.setDate(9,new Date(responsavel.getDtNascimento().getTime()));
						}else{
							ps.setDate(9, null);
						}
						
						ps.setDate(10,new Date(responsavel.getDtExperienciaSoftGestao().getTime()));
						ps.setInt(11, RegraNegocioConstantes.REGISTRO_ATIVO);
						ps.setInt(12, responsavel.getTipoCobranca().getCodigo());
						ps.setString(13, responsavel.getTelCelular());				
			            return ps;
			        }
			    }
				, keyHolder);
		
		return keyHolder.getKey().intValue();
		
	}
}
