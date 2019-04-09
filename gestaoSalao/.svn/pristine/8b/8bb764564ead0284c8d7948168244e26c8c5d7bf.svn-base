package br.com.gestao.salao.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gestao.salao.service.ServiceAgenda;
import br.com.gestao.salao.vo.HorarioVO;


public class SchedulerJob {
	
	@Autowired
    ServiceAgenda serviceAgenda;
	
	public void executar() throws Exception {
		
		for( HorarioVO horario : serviceAgenda.montaHorario()){
			System.out.println("JSF 2 + Quartz 2 example" + horario.getHora());
		}
 
	}
 
	
	/*public static void main(String args[]){
		
		 Datas datas = Datas.novasDatas();
		 datas.comDocumento(1, 5, 2008);
	     datas.comProcessamento(1, 5, 2008);
	     datas.comVencimento(2, 5, 2008);  

        Emissor emissor = Emissor.novoEmissor();  
		emissor.comCedente("Fulano de Tal");  
        emissor.comAgencia("1824").comDigitoAgencia("4");  
        emissor.comContaCorrente("76000");  
        emissor.comNumeroConvenio("1207113");  
        emissor.comDigitoContaCorrente("5");  
        emissor.comCarteira("18");  
        emissor.comNossoNumero("9000206");  

        Sacado sacado = Sacado.novoSacado();  
        sacado.comNome("Fulano da Silva"); 
        sacado.comCpf("111.222.333-12");  
        sacado.comEndereco("Av dos testes, 111 apto 333");  
        sacado.comBairro("Bairro Teste");  
        sacado.comCep("01234-111");  
        sacado.comCidade("São Paulo");  
        sacado.comUf("SP");  

	    Banco banco = new BancoDoBrasil();  

        Boleto boleto = Boleto.novoBoleto();  
		boleto.comBanco(banco);  
        boleto.comDatas(datas);  
        boleto.comEmissor(emissor);  
        boleto.comSacado(sacado);  
        boleto.comValorBoleto("200.00");  
        boleto.comNumeroDoDocumento("1234");  
        boleto.comInstrucoes("instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4", "instrucao 5");  
        boleto.comLocaisDePagamento("local 1", "local 2");  

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);  

        // Para gerar um boleto em PDF  
        gerador.geraPDF("c:/BancoDoBrasil.pdf");  
	}*/
}
