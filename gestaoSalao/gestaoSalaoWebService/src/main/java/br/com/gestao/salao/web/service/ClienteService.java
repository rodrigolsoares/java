package br.com.gestao.salao.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gestao.salao.service.ServiceCliente;
import br.com.gestao.salao.vo.ClienteVO;

@Component
@Path("/getCliente")
public class ClienteService {

	@Autowired
	private ServiceCliente serviceCliente;

	// Chamada
	// http://localhost:8080/gestaoSalaoWebService/rest/getSalao/clienteFoneNumber?number=(011)%209733-56678
	@GET
	@Path("/buscaCodigoCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteVO getSalaoCliente(@QueryParam("number") String number, @QueryParam("codSalao") Integer codSalao ) throws Exception { 
		ClienteVO cliente = new ClienteVO();
		cliente.setTelCelular(number);
		return serviceCliente.pesquisaClientePorAndoid(number, codSalao);
	}
}
