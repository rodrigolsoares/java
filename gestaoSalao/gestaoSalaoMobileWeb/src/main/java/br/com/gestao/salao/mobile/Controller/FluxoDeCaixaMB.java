package br.com.gestao.salao.mobile.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;
import br.com.gestao.salao.mobile.util.MemoriaUsuario;
import br.com.gestao.salao.service.ServiceFluxoCaixa;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.FluxoCaixaVO;
import br.com.gestao.salao.vo.FuncionarioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller("fluxoDeCaixaMobileMB")
@Scope("view")
public class FluxoDeCaixaMB {
	
	@Autowired
	private ServiceFluxoCaixa serviceFluxoCaixa;
	
	private FluxoCaixaVO fluxoCaixa;
	private FluxoCaixaVO fluxoCaixaByFunc;
	private FluxoCaixaVO fluxoCaixaByCli;
	private FuncionarioVO funcionario;	
	private ClienteVO cliente;
	private UsuarioVO usuario;

	
	@PostConstruct
	public void init() {
		usuario = MemoriaUsuario.getUsuarioMemoria();
		fluxoCaixa = new FluxoCaixaVO();
		fluxoCaixaByFunc = new FluxoCaixaVO();
		fluxoCaixaByCli = new FluxoCaixaVO();
		funcionario = new FuncionarioVO();
		cliente = new ClienteVO();
	}
	
	public void pesquisar() throws Exception{
		this.setFluxoCaixa(serviceFluxoCaixa.getFluxoDeCaixa(this.getFluxoCaixa(), this.getUsuario()));
	}
	
	
	public void detalheComissaoFuncionario()throws Exception {
		
		this.getFuncionario().setDataFinal(fluxoCaixa.getDataFinal());
		this.getFuncionario().setDataInicial(fluxoCaixa.getDataInicial());
		
		this.setFluxoCaixaByFunc(serviceFluxoCaixa.getFluxoDeCaixaByFuncionario(this.getFuncionario(), this.getUsuario()));
	}
	
	public void detalheServicoClienteNaoPendente()throws Exception {
		this.pesquisaClienteServico(RegraNegocioConstantes.SERVICO_DIFERENTE_DE_STATUS_PENDENTE);
	}

	private void pesquisaClienteServico(Integer flagPendencia) throws Exception {
		
		this.getCliente().setDataFinal(fluxoCaixa.getDataFinal());
		this.getCliente().setDataInicial(fluxoCaixa.getDataInicial());
		this.getCliente().setFlagStatusPagamento(flagPendencia);
		
		this.setFluxoCaixaByCli(serviceFluxoCaixa.getFluxoDeCaixaByCliente(this.getCliente(), this.getUsuario()));
	}
	
	public void detalheServicoClientePendente()throws Exception {
		this.pesquisaClienteServico(2);
	}
	
	public void imprimeRelatorio() throws IOException, SQLException/*, JRException*/ {
		
		/*JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(this.montaRelatoriofuncionarioPdf());
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();

		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

		JasperPrint jasperPrint = 
				//JasperFillManager.fillReport(scontext.getRealPath("/WEB-INF/report/relatorio_funcionarios_por_cargo.jasper"),parameters, ds);
				JasperFillManager.fillReport(scontext.getRealPath("/jasper/comissaoFuncionario.jasper"),null, ds);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();*/

		//byte[] bytes = baos.toByteArray();

		/*if (bytes != null && bytes.length > 0) {

			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.setContentType("application/x-download");
			response.setHeader("Content-disposition", "inline; filename=\"relatorioPorData.pdf\"");
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();

		}*/

	

	}
	
	
	private List<AgendaVO> montaRelatoriofuncionarioPdf(){
		return this.getFluxoCaixaByFunc().getListaComissao();
	}
	

	public FluxoCaixaVO getFluxoCaixa() {
		return fluxoCaixa;
	}

	public void setFluxoCaixa(FluxoCaixaVO fluxoCaixa) {
		this.fluxoCaixa = fluxoCaixa;
	}

	public FluxoCaixaVO getFluxoCaixaByFunc() {
		return fluxoCaixaByFunc;
	}

	public void setFluxoCaixaByFunc(FluxoCaixaVO fluxoCaixaByFunc) {
		this.fluxoCaixaByFunc = fluxoCaixaByFunc;
	}

	public FluxoCaixaVO getFluxoCaixaByCli() {
		return fluxoCaixaByCli;
	}

	public void setFluxoCaixaByCli(FluxoCaixaVO fluxoCaixaByCli) {
		this.fluxoCaixaByCli = fluxoCaixaByCli;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

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
	
	
}
