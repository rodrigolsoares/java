package br.com.gestao.salao.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.gestao.salao.util.DataUtil;
import br.com.gestao.salao.vo.AgendaVO;
import br.com.gestao.salao.vo.FuncionarioVO;


public class ServiceAgendaPaiImpl {
	
	public List<AgendaVO> montaAgendaFunc(Date data, final FuncionarioVO func){
		
		boolean flag = false;
		List<AgendaVO> novoArray = new ArrayList<AgendaVO>();
		
		Calendar calDataInicial = DataUtil.getCalendarPopulado(8,30);
		Calendar calDataFinal = DataUtil.getCalendarPopulado(20,00);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(calDataInicial.getTime());
		
		while(!DataUtil.formatHora(gc.getTime()).equals(DataUtil.formatHora(calDataFinal.getTime()))){   
			
			gc.add(Calendar.MINUTE,30);
			gc.add(Calendar.AM_PM, 0);
			
			for (int j = 0;j < func.getListaAgenda().size();j++){
				
				AgendaVO agenda = (AgendaVO) func.getListaAgenda().get(j);
				
				if (!"".equals(agenda.getHora()) && DataUtil.formatHora(gc.getTime()).toString().equals(agenda.getHora()) ){

						agenda.setHora(DataUtil.formatHora(gc.getTime()));
						novoArray.add(agenda);
						flag = true;
						break;
				}else{
					flag = false;
				}
			}
				
			if(flag == false){
				novoArray.add(montaHorarioVago(data, func, gc));
			}
		}
		
		return novoArray;
	}
	
	private  AgendaVO montaHorarioVago(Date data, FuncionarioVO func, GregorianCalendar gc){
		
		AgendaVO agendaTmp = new AgendaVO();
		agendaTmp.setData(data); //pega a data que foi passada por parametro
		agendaTmp.setHora(DataUtil.formatHora(gc.getTime()));
		agendaTmp.setCodigo(0);
		agendaTmp.setCodigoCliente(0);
		agendaTmp.setNomeCliente("Vago");
		agendaTmp.setCodigoAtendimento(0);
		agendaTmp.setNomeFuncionario(func.getNome());
		agendaTmp.setCodigoFuncionario(func.getCodigo());
		agendaTmp.setFlagAjudante("0");
		agendaTmp.setPorcentagem(func.getPorcentagem());
		
		return agendaTmp;
	}

}
