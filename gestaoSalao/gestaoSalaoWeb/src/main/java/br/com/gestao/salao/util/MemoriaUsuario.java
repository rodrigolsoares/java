package br.com.gestao.salao.util;

import br.com.gestao.salao.vo.UsuarioVO;

public class MemoriaUsuario {

	public static void guardaUsuarioMemoria( UsuarioVO user){
		JsfUtil.setSession("usuario", user);
	}
	
	public static UsuarioVO getUsuarioMemoria(){
		
		if(JsfUtil.getSession() != null){
			return (UsuarioVO)JsfUtil.getSession().getAttribute("usuario");	
		}else{
			return null;
		}
		
	}
}
