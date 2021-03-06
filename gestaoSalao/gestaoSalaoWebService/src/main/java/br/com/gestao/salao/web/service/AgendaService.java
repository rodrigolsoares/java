package br.com.gestao.salao.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gestao.salao.service.ServiceAgendamento;
import br.com.gestao.salao.vo.AgendaVO;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Path("/getAgenda")
public class AgendaService {

	@Autowired 
	private ServiceAgendamento serviceAgendamento;
	
	
	// Chamada
	// {"ajudantes":null,"caixinha":null,"codigo":0,"codigoAtendimento":null,"codigoCliente":null,"codigoFuncionario":2,"codigoPagamento":null,"data":null,"dataCompensaCheque":null,"desconto":null,"flagAjudante":null,"hora":null,"listaAjudanteSelecionado":[],"listaServico":null,"listaServicoSelecionado":[],"nomeAjudante":null,"nomeCliente":null,"nomeFuncionario":null,"nomePagamento":null,"nomeServico":null,"nomeStatus":null,"porcentagem":null,"qtdeAjudante":null,"qtdeRegistros":null,"servicos":null,"sigla":null,"tipoAtendimento":null,"tipoRateio":null,"usuarioAndroid":{"codigo":null,"confirmaSenha":null,"status":{"nomeStatus":null,"codigo":0},"login":"011973356678","mensagem":null,"nome":null,"perfil":{"nome":null,"codigo":0},"responsavel":{"codigo":null,"cpf":null,"tipoCobranca":{"codigo":null,"descricao":null},"dtExperienciaSoftGestao":null,"dtNascimento":null,"email":null,"listaSalao":[],"nome":null,"plano":{"codigo":null,"descricao":null,"nome":null,"valor":null},"rg":null,"sexo":null,"statusRegistro":{"nomeStatus":null,"codigo":0},"telCelular":null,"telComercial":null,"telResidencial":null,"diaVencimento":0},"salao":{"bairro":null,"cep":null,"cidade":null,"cnpj":null,"codigo":9,"complemento":null,"email":null,"estado":null,"habilitaAjudante":null,"inscEstadual":null,"listaFuncionario":[],"listaServico":[],"listaUsuarios":[],"logradouro":null,"nomeFantasia":null,"numero":null,"razaoSocial":null,"responsavel":{"codigo":null,"cpf":null,"tipoCobranca":{"codigo":null,"descricao":null},"dtExperienciaSoftGestao":null,"dtNascimento":null,"email":null,"listaSalao":[],"nome":null,"plano":{"codigo":null,"descricao":null,"nome":null,"valor":null},"rg":null,"sexo":null,"statusRegistro":{"nomeStatus":null,"codigo":0},"telCelular":null,"telComercial":null,"telResidencial":null,"diaVencimento":0},"statusRegistro":null,"tel1":null,"tel2":null},"senha":null,"flagHabilitaAjudante":false},"valor":null,"valorComissao":null,"valorTotal":null}
	//http://localhost:8080/gestaoSalaoWebService/rest/getAgenda/inserir?agendaJson=%7B%22ajudantes%22%3Anull%2C%22caixinha%22%3Anull%2C%22codigo%22%3A0%2C%22codigoAtendimento%22%3Anull%2C%22codigoCliente%22%3Anull%2C%22codigoFuncionario%22%3A2%2C%22codigoPagamento%22%3Anull%2C%22data%22%3Anull%2C%22dataCompensaCheque%22%3Anull%2C%22desconto%22%3Anull%2C%22flagAjudante%22%3Anull%2C%22hora%22%3Anull%2C%22listaAjudanteSelecionado%22%3A%5B%5D%2C%22listaServico%22%3Anull%2C%22listaServicoSelecionado%22%3A%5B%5D%2C%22nomeAjudante%22%3Anull%2C%22nomeCliente%22%3Anull%2C%22nomeFuncionario%22%3Anull%2C%22nomePagamento%22%3Anull%2C%22nomeServico%22%3Anull%2C%22nomeStatus%22%3Anull%2C%22porcentagem%22%3Anull%2C%22qtdeAjudante%22%3Anull%2C%22qtdeRegistros%22%3Anull%2C%22servicos%22%3Anull%2C%22sigla%22%3Anull%2C%22tipoAtendimento%22%3Anull%2C%22tipoRateio%22%3Anull%2C%22usuarioAndroid%22%3A%7B%22codigo%22%3Anull%2C%22confirmaSenha%22%3Anull%2C%22status%22%3A%7B%22nomeStatus%22%3Anull%2C%22codigo%22%3A0%7D%2C%22login%22%3A%22011973356678%22%2C%22mensagem%22%3Anull%2C%22nome%22%3Anull%2C%22perfil%22%3A%7B%22nome%22%3Anull%2C%22codigo%22%3A0%7D%2C%22responsavel%22%3A%7B%22codigo%22%3Anull%2C%22cpf%22%3Anull%2C%22tipoCobranca%22%3A%7B%22codigo%22%3Anull%2C%22descricao%22%3Anull%7D%2C%22dtExperienciaSoftGestao%22%3Anull%2C%22dtNascimento%22%3Anull%2C%22email%22%3Anull%2C%22listaSalao%22%3A%5B%5D%2C%22nome%22%3Anull%2C%22plano%22%3A%7B%22codigo%22%3Anull%2C%22descricao%22%3Anull%2C%22nome%22%3Anull%2C%22valor%22%3Anull%7D%2C%22rg%22%3Anull%2C%22sexo%22%3Anull%2C%22statusRegistro%22%3A%7B%22nomeStatus%22%3Anull%2C%22codigo%22%3A0%7D%2C%22telCelular%22%3Anull%2C%22telComercial%22%3Anull%2C%22telResidencial%22%3Anull%2C%22diaVencimento%22%3A0%7D%2C%22salao%22%3A%7B%22bairro%22%3Anull%2C%22cep%22%3Anull%2C%22cidade%22%3Anull%2C%22cnpj%22%3Anull%2C%22codigo%22%3A9%2C%22complemento%22%3Anull%2C%22email%22%3Anull%2C%22estado%22%3Anull%2C%22habilitaAjudante%22%3Anull%2C%22inscEstadual%22%3Anull%2C%22listaFuncionario%22%3A%5B%5D%2C%22listaServico%22%3A%5B%5D%2C%22listaUsuarios%22%3A%5B%5D%2C%22logradouro%22%3Anull%2C%22nomeFantasia%22%3Anull%2C%22numero%22%3Anull%2C%22razaoSocial%22%3Anull%2C%22responsavel%22%3A%7B%22codigo%22%3Anull%2C%22cpf%22%3Anull%2C%22tipoCobranca%22%3A%7B%22codigo%22%3Anull%2C%22descricao%22%3Anull%7D%2C%22dtExperienciaSoftGestao%22%3Anull%2C%22dtNascimento%22%3Anull%2C%22email%22%3Anull%2C%22listaSalao%22%3A%5B%5D%2C%22nome%22%3Anull%2C%22plano%22%3A%7B%22codigo%22%3Anull%2C%22descricao%22%3Anull%2C%22nome%22%3Anull%2C%22valor%22%3Anull%7D%2C%22rg%22%3Anull%2C%22sexo%22%3Anull%2C%22statusRegistro%22%3A%7B%22nomeStatus%22%3Anull%2C%22codigo%22%3A0%7D%2C%22telCelular%22%3Anull%2C%22telComercial%22%3Anull%2C%22telResidencial%22%3Anull%2C%22diaVencimento%22%3A0%7D%2C%22statusRegistro%22%3Anull%2C%22tel1%22%3Anull%2C%22tel2%22%3Anull%7D%2C%22senha%22%3Anull%2C%22flagHabilitaAjudante%22%3Afalse%7D%2C%22valor%22%3Anull%2C%22valorComissao%22%3Anull%2C%22valorTotal%22%3Anull%7D
	@GET
	@Path("/inserir")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getSalaoCliente( @QueryParam("agendaJson") String agendaJson )
										 throws Exception {
		
		AgendaVO agenda = convertJsonEmAgendaVo(agendaJson);
		
		//Valores fixos
		agenda.setTipoAtendimento(1);
		agenda.setCodigoAtendimento(4);
		agenda.setCodigoPagamento(6);
		agenda.setDesconto(0d);
		agenda.setCaixinha(0d);

		
		serviceAgendamento.inseriAgendamentoAndroid(agenda, agenda.getData());
		
		
		return 0;
	}
	
	private AgendaVO convertJsonEmAgendaVo(String valor) {

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(valor.toString(), AgendaVO.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}
}
