package br.com.gestao.salao.web.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gestao.salao.service.ServiceSalao;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.SalaoVO;

@Component
@Path("/getSalao")
public class SalaoService {

	@Autowired
	private ServiceSalao serviceSalao;
	
	@Path("/clienteFoneNumber")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalaoVO> getSalaoCliente() throws Exception {
		ClienteVO cliente = new ClienteVO();
		cliente.setTelCelular("011973356678");
		return serviceSalao.pesquisaSalaoCliente(cliente);
	}

	// Chamada
	// http://localhost:8080/gestaoSalaoWebService/rest/getSalao/clienteFoneNumber?number=(011)%209733-56678
	@GET
	@Path("/clienteFoneNumber")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalaoVO> getSalaoCliente(@QueryParam("number") String number) throws Exception { 
		ClienteVO cliente = new ClienteVO();
		cliente.setTelCelular(number);
		return serviceSalao.pesquisaSalaoCliente(cliente);
	}
}
