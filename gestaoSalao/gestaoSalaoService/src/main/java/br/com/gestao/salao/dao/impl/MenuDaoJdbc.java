package br.com.gestao.salao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.gestao.salao.dao.MenuDao;
import br.com.gestao.salao.vo.MenuVO;

@Repository
public class MenuDaoJdbc extends SpringDao implements MenuDao{
	
	public List<MenuVO> buscarPorCodigoUsuario(int idPerfil, int idPlano) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel from menu m, plano_perfil_menu mp ");
		sb.append(" where m.pk_menu = mp.fk_menu ");
		sb.append("  and mp.fk_perfil = " + idPerfil);
		sb.append(" and  mp.fk_plano = " + idPlano);
		sb.append("  order by nivel ");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
	}
	
	public List<MenuVO> buscarMobilePorCodigoUsuario(int idPerfil, int idPlano) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel from menu_mobile m, plano_perfil_menu mp ");
		sb.append(" where m.pk_menu = mp.fk_menu ");
		sb.append("  and mp.fk_perfil = " + idPerfil);
		sb.append(" and  mp.fk_plano = " + idPlano);
		sb.append("  order by nivel ");
		
		return getJdbcTemplate().query(sb.toString().toLowerCase(),new rowMapper());
	}
	
	private static final class rowMapper implements RowMapper<MenuVO> {
		public MenuVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			MenuVO menu = new MenuVO();
			menu.setCodigo(rs.getInt("pk_menu"));
			menu.setNome(rs.getString("nm_menu"));
			menu.setUrl(rs.getString("nm_url"));
			menu.setIcone(rs.getString("nm_icone"));
			menu.setId(rs.getString("id_obj"));
			menu.setNivel(rs.getInt("nivel"));
			
			return menu;
		}
	}
}
