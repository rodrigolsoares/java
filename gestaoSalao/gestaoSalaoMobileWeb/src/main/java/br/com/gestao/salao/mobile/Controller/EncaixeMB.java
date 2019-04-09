package br.com.gestao.salao.mobile.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.mobile.util.MemoriaUsuario;
import br.com.gestao.salao.mobile.util.MensagensUtil;
import br.com.gestao.salao.service.ServiceAgendamento;
import br.com.gestao.salao.service.ServiceCliente;
import br.com.gestao.salao.service.ServiceEncaixe;
import br.com.gestao.salao.service.ServiceFormaPagamento;
import br.com.gestao.salao.service.ServiceFuncionario;
import br.com.gestao.salao.service.ServiceRateio;
import br.com.gestao.salao.service.ServiceServico;
import br.com.gestao.salao.service.ServiceStatusAtendimento;
import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.RateioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller("encaixeMobileMB")
@Scope("view")
public class EncaixeMB {
	
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
	private ServiceEncaixe serviceEncaixe;
	
	@Autowired
	private ServiceRateio serviceRateio;
	
	private List<AgendaVO> listaEncaixe;
	private List<AgendaVO> listaFiltroEncaixe;
	
	private Map<String, Integer> listaServico;
	private Map<String, Integer> listaFormaPagamento;
	private Map<String, Integer> listaStatusAtendimento;
	private Map<String, Integer> listaAjudante;

	
	private boolean habilitaAjudante;

	private AgendaVO agenda;
	private AgendaVO agendaAlt;
	private UsuarioVO usuario;
	private ClienteVO cliente;
	private ClienteVO clienteCadastro;
	private FuncionarioVO funcionario;
	private int activeIndex = 0;
	
	@PostConstruct
	public void init() throws Exception{
		
		
		this.setUsuario(MemoriaUsuario.getUsuarioMemoria());
		RateioVO rateio = serviceRateio.pesquisar(usuario);
		this.setHabilitaAjudante(rateio.isHabilita());
		
		this.setAgenda(new AgendaVO());
		this.setClienteCadastro(new ClienteVO());
		this.populaObjetos();
		this.buscaEncaixe();
	}
	
	public void onChangeBuscaEncaixe(TabChangeEvent event) throws Exception {
		this.populaObjetos();
		buscaEncaixe();
	}
	
	public void gravar() throws Exception{
		
		if(this.getCliente().getCodigo() != null ){
			this.getAgenda().setCodigoCliente(this.getCliente().getCodigo());
		}
		
		if(this.getFuncionario().getCodigo() != null ){
			this.getAgenda().setCodigoFuncionario(this.getFuncionario().getCodigo());
			this.getAgenda().setPorcentagem(this.getFuncionario().getPorcentagem());
		}
		
		String mensagem = serviceAgendamento.validaCamposObrigatorios(this.getAgenda());
		
		if(mensagem == null){
			
			this.getAgenda().setHora(DataUtil.formataHoraCorrente());
			this.getAgenda().setTipoAtendimento(RegraNegocioConstantes.ATENDIMENTO_ENCAIXE);
			serviceAgendamento.inseriAgendamento(this.getAgenda(), new Date(), this.getUsuario());
			MensagensUtil.info(MensagensConstantes.CADASTRO_ATENDIMENTO_SUCESSO);
			
		}else{
			MensagensUtil.error(mensagem);	
		}

	}
	
	public void alterar()  throws Exception{
		
		if(this.getCliente().getCodigo() != null ){
			this.agenda.setCodigoCliente(this.getCliente().getCodigo());
		}
		
		String mensagem = serviceAgendamento.validaCamposObrigatorios(this.getAgenda());
		
		if(mensagem == null){
			this.getAgenda().setTipoAtendimento(RegraNegocioConstantes.ATENDIMENTO_ENCAIXE);
			serviceAgendamento.alterarAgendamento(agenda, new Date(), this.getUsuario());
			MensagensUtil.info(MensagensConstantes.ATUALIZA_ATENDIMENTO_SUCESSO);
		}else{
			MensagensUtil.error(mensagem);	
		}
		
	}
	
	public void excluir()  throws Exception{
		serviceAgendamento.excluirAgendamento(agenda);
		MensagensUtil.info(MensagensConstantes.EXCLUIR_ATENDIMENTO_SUCESSO);
		this.posCadastro();
	}
	
	public void editar() throws Exception {
		
		populaObjetos();
		this.setAgenda(serviceAgendamento.getAgendamentoPorCodigo(agenda));
		this.setCliente(new ClienteVO());
		this.getCliente().setNome(agenda.getNomeCliente());
		this.getCliente().setCodigo(agenda.getCodigoCliente());
		this.setFuncionario(new FuncionarioVO());
		this.getFuncionario().setNome(agenda.getNomeFuncionario());
		this.getFuncionario().setCodigo(agenda.getCodigoFuncionario());
		this.setActiveIndex(0);
		
	}
	
	
	public void posCadastro() throws Exception{
		
		this.setAgenda(new AgendaVO());
		this.setCliente(new ClienteVO());
	}
	
	public void calculaTodosServicos() throws Exception{
		
		for(String chave : this.getListaServico().keySet()){
			agenda.getListaServicoSelecionado().add(this.getListaServico().get(chave).toString());
		}
		
		calculaServico();
	}
	
	public void calculaServico() throws Exception{
		
		agenda.setValor(serviceServico.calculaValorServico(agenda.getListaServicoSelecionado(), this.getUsuario()));
		agenda.setValorTotal(serviceAgendamento.calculaTotalServico(agenda, this.getUsuario()));
	}
	
	
	public List<ClienteVO> completaCliente(String query) throws Exception {  
        
		ClienteVO sugestCliente = new ClienteVO();
		sugestCliente.setNome(query);
		
		List<ClienteVO> listaCliente = serviceCliente.getListaCliente(sugestCliente, this.getUsuario());
		
		if(listaCliente.size() == 0){
			//PoupUpJsf.poupUpSugestionRegistroNaoEncontrado();
		}
		
        return listaCliente;  
        
    } 
	
	public List<FuncionarioVO> completaFuncionario(String query) throws Exception {  
	        
		FuncionarioVO sugestCliente = new FuncionarioVO();
		sugestCliente.setNome(query);
		
		List<FuncionarioVO> listaFunc = serviceFuncionario.getListaFuncionario(sugestCliente,this.getUsuario());  
		
		if(listaFunc.size() == 0){
			//PoupUpJsf.poupUpSugestionRegistroNaoEncontrado();
		}
		
        return listaFunc;  
        
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
		this.setListaServico(serviceServico.getMapServico(this.getUsuario()));
		this.setListaFormaPagamento(serviceFormaPagamento.getMap());
		this.setListaStatusAtendimento(serviceStatusAtendimento.getMap());
	}
	
	private void buscaEncaixe() throws Exception{
		this.setListaEncaixe(serviceEncaixe.listaEncaixe(new Date(), this.getUsuario()));
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

	public FuncionarioVO getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}
	
	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	
	}
	
	public AgendaVO getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaVO agenda) {
		this.agenda = agenda;
	}
	
	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	
	public Map<String, Integer> getListaServico() {
		return listaServico;
	}

	public void setListaServico(Map<String, Integer> listaServico) {
		this.listaServico = listaServico;
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


	public boolean isHabilitaAjudante() {
		return habilitaAjudante;
	}


	public void setHabilitaAjudante(boolean habilitaAjudante) {
		this.habilitaAjudante = habilitaAjudante;
	}


	public List<AgendaVO> getListaEncaixe() {
		return listaEncaixe;
	}

	public void setListaEncaixe(List<AgendaVO> listaEncaixe) {
		this.listaEncaixe = listaEncaixe;
	}

	public AgendaVO getAgendaAlt() {
		return agendaAlt;
	}

	public void setAgendaAlt(AgendaVO agendaAlt) {
		this.agendaAlt = agendaAlt;
	}

	public List<AgendaVO> getListaFiltroEncaixe() {
		return listaFiltroEncaixe;
	}

	public void setListaFiltroEncaixe(List<AgendaVO> listaFiltroEncaixe) {
		this.listaFiltroEncaixe = listaFiltroEncaixe;
	}
	
	public ClienteVO getClienteCadastro() {
		return clienteCadastro;
	}

	public void setClienteCadastro(ClienteVO clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	
	
		
}
