package br.com.gestao.salao.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.service.ServiceAgenda;
import br.com.gestao.salao.service.ServiceAgendamento;
import br.com.gestao.salao.service.ServiceCliente;
import br.com.gestao.salao.service.ServiceFormaPagamento;
import br.com.gestao.salao.service.ServiceFuncionario;
import br.com.gestao.salao.service.ServiceRateio;
import br.com.gestao.salao.service.ServiceServico;
import br.com.gestao.salao.service.ServiceStatusAtendimento;
import br.com.gestao.salao.util.MemoriaUsuario;
import br.com.gestao.salao.util.MensagensUtil;
import br.com.gestao.salao.util.PoupUpJsf;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.HorarioVO;
import br.com.gestao.salao.vo.RateioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller
@Scope("view")
public class AgendaMB {
	
	@Autowired
	private ServiceAgenda serviceAgenda;
	
	@Autowired
	private ServiceAgendamento serviceAgendamento;
	
	@Autowired
	private ServiceCliente serviceCliente;
	
	@Autowired
	private ServiceServico serviceServico;
	
	@Autowired
	private ServiceFormaPagamento serviceFormaPagamento;
	
	@Autowired
	private ServiceStatusAtendimento serviceStatusAtendimento;
	
	@Autowired
	private ServiceFuncionario serviceFuncionario;
	
	@Autowired
	private ServiceRateio serviceRateio; 
	
	private List<HorarioVO> listaHorarios;
	private List<FuncionarioVO> listaAgendaFuncionario;
	private Map<String, Integer> listaServico;
	private Map<String, Integer> listaFormaPagamento;
	private Map<String, Integer> listaStatusAtendimento;
	private Map<String, Integer> listaAjudante; 

	private String mensagemAgendamento;
	private boolean sucessoAgendamento;
	private boolean habilitaAjudante;
	
	private Date dataSelecionada;
	private AgendaVO agenda;
	private UsuarioVO usuario;
	private ClienteVO cliente;
	private ClienteVO clienteCadastro;
	
	@PostConstruct
	public void init() throws Exception{
		
		this.setUsuario(MemoriaUsuario.getUsuarioMemoria());
		
		RateioVO rateio = serviceRateio.pesquisar(usuario);
		this.setHabilitaAjudante(rateio.isHabilita());
		
		this.setAgenda(new AgendaVO());
		this.setDataSelecionada(new Date());
		this.setClienteCadastro(new ClienteVO());
		this.setCliente(new ClienteVO());
		this.populaObjetos();
		this.consultaAgenda();
	}
	
	public void consultaAgenda() throws Exception{
		this.setListaAgendaFuncionario(serviceAgenda.montaHorariosAgendados(this.getDataSelecionada(), this.getUsuario()));
	}
	
	
	public void abreAgendamento() throws Exception {
		
		populaObjetos();
		
		this.setAgenda(serviceAgendamento.getAgendamentoPorCodigo(agenda));
		this.setCliente(new ClienteVO());
		this.getCliente().setNome(agenda.getNomeCliente());
		this.getCliente().setCodigo(agenda.getCodigoCliente());
		
	}
	
	public List<ClienteVO> completaCliente(String query) throws Exception {  
        
		ClienteVO sugestCliente = new ClienteVO();
		sugestCliente.setNome(query);
		
		List<ClienteVO> listaCliente = serviceCliente.getListaCliente(sugestCliente,usuario);
		
		if(listaCliente.size() == 0){
			PoupUpJsf.poupUpSugestionRegistroNaoEncontrado();
		}
		
        return listaCliente;  
        
    }  
	
	public void calculaServico() throws Exception{
		
		agenda.setValor(serviceServico.calculaValorServico(agenda.getListaServicoSelecionado(), this.getUsuario()));
		agenda.setValorTotal(serviceAgendamento.calculaTotalServico(agenda, this.getUsuario()));
	}
	
	public void calculaTodosServicos() throws Exception{
		
		for(String chave : this.getListaServico().keySet()){
			agenda.getListaServicoSelecionado().add(this.getListaServico().get(chave).toString());
		}
		
		calculaServico();
	}
	
	public void gravarAgendamento() throws Exception{
		
		if(this.getCliente() != null && this.getCliente().getCodigo() != null ){
			this.agenda.setCodigoCliente(this.getCliente().getCodigo());
		}
		
		String mensagem = serviceAgendamento.validaCamposObrigatorios(this.getAgenda());
		
		if(mensagem == null){
			this.getAgenda().setTipoAtendimento(RegraNegocioConstantes.ATENDIMENTO_AGENDAMENTO);
			serviceAgendamento.inseriAgendamento(this.getAgenda(), this.getDataSelecionada(), this.getUsuario());
			this.setSucessoAgendamento(true);
		}else{
			this.setSucessoAgendamento(false);
		}
		
		this.setMensagemAgendamento(mensagem);
		PoupUpJsf.poupUpResultadoAgendamento();
		
	}
	
	public void alterarAgendamento()  throws Exception{
		
		if(this.getCliente().getCodigo() != null ){
			this.agenda.setCodigoCliente(this.getCliente().getCodigo());
		}
		
		String mensagem = serviceAgendamento.validaCamposObrigatorios(this.getAgenda());
		
		if(mensagem == null){
			this.getAgenda().setTipoAtendimento(RegraNegocioConstantes.ATENDIMENTO_AGENDAMENTO);
			serviceAgendamento.alterarAgendamento(agenda, this.getDataSelecionada(), this.getUsuario());
			this.setSucessoAgendamento(true);
		}else{
			this.setSucessoAgendamento(false);
		}
		
		this.setMensagemAgendamento(mensagem);
		PoupUpJsf.poupUpResultadoAgendamento();
		
	}
	
	public void excluirAgendamento()  throws Exception{
		serviceAgendamento.excluirAgendamento(agenda);
		this.posCadastro();
	}
	
	public void posCadastro() throws Exception{
		
		this.setAgenda(new AgendaVO());
		this.setCliente(new ClienteVO());
		this.consultaAgenda();
	}
	
	
	public void gravaClienteRapido() throws Exception{
		
		String mensagem = serviceCliente.validaCamposObrigatoriosRapido(this.getClienteCadastro());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			gravarCliente();
			
		}
		
	}
	
	private void populaObjetos() throws Exception{
		
		this.setListaHorarios(serviceAgenda.montaHorario());
		this.setListaServico(serviceServico.getMapServico(this.getUsuario()));
		this.setListaFormaPagamento(serviceFormaPagamento.getMap());
		this.setListaStatusAtendimento(serviceStatusAtendimento.getMap());
		this.setListaAjudante(serviceFuncionario.getMapAjudante(agenda.getCodigoFuncionario(), usuario));
		
	}
	
	private void gravarCliente() throws Exception {
		
		this.getClienteCadastro().setFlagOperacao(0);
		if(serviceCliente.verificaSeServicoExiste(this.getClienteCadastro(), this.getUsuario())){
			
			MensagensUtil.error(MensagensConstantes.CLIENTE_JA_EXISTE);	
		}else{
			
			serviceCliente.gravaCliente(this.getClienteCadastro(), this.getUsuario()); 
			this.setClienteCadastro(new ClienteVO());
			MensagensUtil.info(MensagensConstantes.CADASTRO_CLIENTE_SUCESSO);
			
			
		}
		
	}
	
	/**Get e Set Objetos da paginas **/
	
	public List<HorarioVO> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(List<HorarioVO> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public List<FuncionarioVO> getListaAgendaFuncionario() {
		return listaAgendaFuncionario;
	}

	public void setListaAgendaFuncionario(List<FuncionarioVO> listaAgendaFuncionario) {
		this.listaAgendaFuncionario = listaAgendaFuncionario;
	}

	public AgendaVO getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaVO agenda) {
		this.agenda = agenda;
	}

	public Map<String, Integer> getListaServico() {
		return listaServico;
	}

	public void setListaServico(Map<String, Integer> listaServico) {
		this.listaServico = listaServico;
	}

	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}

	public Map<String, Integer> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(Map<String, Integer> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public Map<String, Integer> getListaStatusAtendimento() {
		return listaStatusAtendimento;
	}

	public void setListaStatusAtendimento(Map<String, Integer> listaStatusAtendimento) {
		this.listaStatusAtendimento = listaStatusAtendimento;
	}

	public Map<String, Integer> getListaAjudante() {
		return listaAjudante;
	}

	public void setListaAjudante(Map<String, Integer> listaAjudante) {
		this.listaAjudante = listaAjudante;
	}

	public String getMensagemAgendamento() {
		return mensagemAgendamento;
	}

	public void setMensagemAgendamento(String mensagemAgendamento) {
		this.mensagemAgendamento = mensagemAgendamento;
	}

	public boolean isSucessoAgendamento() {
		return sucessoAgendamento;
	}

	public void setSucessoAgendamento(boolean sucessoAgendamento) {
		this.sucessoAgendamento = sucessoAgendamento;
	}

	
	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public boolean isHabilitaAjudante() {
		return habilitaAjudante;
	}

	public void setHabilitaAjudante(boolean habilitaAjudante) {
		this.habilitaAjudante = habilitaAjudante;
	}

	public ClienteVO getClienteCadastro() {
		return clienteCadastro;
	}

	public void setClienteCadastro(ClienteVO clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}
	
		
}
