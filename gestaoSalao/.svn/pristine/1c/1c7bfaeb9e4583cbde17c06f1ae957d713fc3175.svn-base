package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gestao.salao.dao.FuncionarioDao;
import br.com.gestao.salao.dao.SalaoDao;
import br.com.gestao.salao.dao.ServicoDao;
import br.com.gestao.salao.service.ServiceSalao;
import br.com.gestao.salao.vo.ClienteVO;
import br.com.gestao.salao.vo.SalaoVO;

@Service
@Transactional(readOnly=false)
public class ServiceSalaoImpl implements ServiceSalao {
	
	@Autowired
	private SalaoDao salaoDao;
	
	@Autowired
	private ServicoDao servicoDao;
	
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	public List<SalaoVO> pesquisaSalaoCliente(ClienteVO cliente) throws Exception {
		
		List<SalaoVO> lisaSalao = new ArrayList<SalaoVO>();
		
		for(SalaoVO salao : salaoDao.pesquisaSalaoCliente(cliente)){
			
			salao.setListaServico(servicoDao.pesquisaServico(salao));
			salao.setListaFuncionario(funcionarioDao.pesquisaFuncionario(salao));
			lisaSalao.add(salao);
		}
		
		
		
		return lisaSalao;
	}
}
