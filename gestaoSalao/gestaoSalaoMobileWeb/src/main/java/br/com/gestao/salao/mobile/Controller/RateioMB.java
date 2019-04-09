package br.com.gestao.salao.mobile.Controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.gestao.salao.Constants.MensagensConstantes;
import br.com.gestao.salao.mobile.util.MemoriaUsuario;
import br.com.gestao.salao.mobile.util.MensagensUtil;
import br.com.gestao.salao.service.ServiceRateio;
import br.com.gestao.salao.vo.RateioVO;
import br.com.gestao.salao.vo.UsuarioVO;

@Controller("rateioMB")
@Scope("view")
public class RateioMB {
	
	@Autowired
	private ServiceRateio serviceRateio;
	
	private RateioVO rateio;
	private UsuarioVO usuario;
	
	@PostConstruct
	public void init() throws Exception{
		this.setUsuario(MemoriaUsuario.getUsuarioMemoria());
		this.setRateio(serviceRateio.pesquisar(this.getUsuario()));
	}
	
	public void atualizarVAlor() throws Exception{
		
		String mensagem = serviceRateio.validaCamposObrigatorios(this.getRateio());
		
		if(mensagem != null){
			MensagensUtil.error(mensagem);	
			
		}else{
			serviceRateio.atualizaRateio(this.getRateio(), this.getUsuario());
			MensagensUtil.info(MensagensConstantes.ATUALIZA_RATEIO_SUCESSO);
		}
	}

	public RateioVO getRateio() {
		return rateio;
	}

	public void setRateio(RateioVO rateio) {
		this.rateio = rateio;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	
	
}
