package br.com.gestao.salao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.dao.UsuarioAndroidDao;
import br.com.gestao.salao.service.ServiceUsuarioAndroid;
import br.com.gestao.salao.vo.UsuarioAndroidVO;

@Service
@Transactional(readOnly=false)
public class ServiceUsuarioAndroidImpl  implements ServiceUsuarioAndroid{
	
	@Autowired
	private UsuarioAndroidDao usuarioDao;
	
	public void inserir(UsuarioAndroidVO usuario) throws Exception{
		usuarioDao.inserir(usuario);
	}
	
}
