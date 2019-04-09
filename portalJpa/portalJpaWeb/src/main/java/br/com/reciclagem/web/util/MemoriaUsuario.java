package br.com.reciclagem.web.util;

import br.com.reciclagem.web.vo.UsuarioVO;

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
