package br.com.gestao.salao.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gestao.salao.service.ServiceUsuarioAndroid;
import br.com.gestao.salao.vo.UsuarioAndroidVO;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Path("/getUsuarioAndroid")
public class UsuarioAndroidService {

	@Autowired 
	private ServiceUsuarioAndroid serviceUsuarioAndroid;
	
	@GET
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getSalaoCliente( @QueryParam("usuarioJson") String usuarioJson )
										 throws Exception {
		
		UsuarioAndroidVO usuario = convertJsonEmUsuarioVo(usuarioJson);
		serviceUsuarioAndroid.inserir(usuario);
		return 0;
	}
	
	private UsuarioAndroidVO convertJsonEmUsuarioVo(String valor) {

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(valor.toString(), UsuarioAndroidVO.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}
}
