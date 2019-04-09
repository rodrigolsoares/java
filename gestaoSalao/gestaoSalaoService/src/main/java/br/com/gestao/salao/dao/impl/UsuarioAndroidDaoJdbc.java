package br.com.gestao.salao.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.gestao.salao.Constants.Constantes;
import br.com.gestao.salao.dao.UsuarioAndroidDao;
import br.com.gestao.salao.vo.UsuarioAndroidVO;

@Repository
public class UsuarioAndroidDaoJdbc extends SpringDao implements UsuarioAndroidDao {

	public void inserir(UsuarioAndroidVO usuario) throws Exception{
	
		StringBuffer sb = new StringBuffer();

		sb.append(" insert into usuario_android ( pk_usuario_android, nm_usuario, no_celular, e_mail) ");
		sb.append(" values (?, ?, ?, ?);" );
		
		Object[] param = new Object[]{
				Constantes.ZERO,
				usuario.getNome(),
				usuario.getNumeroCeluar(),
				usuario.getEmail(),
		};
		
		getJdbcTemplate().update(sb.toString().toLowerCase(), param);
		
	}

	

}
