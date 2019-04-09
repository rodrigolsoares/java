package br.com.gestao.salao.web.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gestao.salao.service.ServiceAgendaAndoid;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.HorarioVO;
import br.com.gestao.salao.vo.SalaoVO;

@Component
@Path("/getFuncionario")
public class FuncionarioService {
	
	@Autowired
	private ServiceAgendaAndoid serviceAgendaAndoid;
	
	// Chamada
	// http://localhost:8080/gestaoSalaoWebService/rest/getFuncionario/horarios?dataAgendamento=01/05/2014&codigoResponsavel=1&codigoSalao=1&codigoFunc=2
	@GET
	@Path("/horarios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HorarioVO> getSalaoCliente( @QueryParam("dataAgendamento") Date dataAgendamento,
			                               @QueryParam("codigoResponsavel") Integer codigoResponsavel,
										   @QueryParam("codigoSalao") Integer codigoSalao,
										   @QueryParam("codigoFunc") Integer CodigoFuncionario)
										 throws Exception {
		SalaoVO salao = new SalaoVO();
		FuncionarioVO funcionario = new FuncionarioVO();
		
		salao.setCodigo(codigoSalao);
		salao.getResponsavel().setCodigo(codigoResponsavel);
		funcionario.setCodigo(CodigoFuncionario);
		
		
		return serviceAgendaAndoid.montaHorariosAgendados(dataAgendamento, salao, funcionario);
	}
}
