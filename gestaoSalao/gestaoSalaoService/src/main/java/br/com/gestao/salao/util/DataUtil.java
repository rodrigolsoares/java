package br.com.gestao.salao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.gestao.salao.Constants.RegraNegocioConstantes;


public class DataUtil {
	
	public static java.sql.Date converteDataSql(Date sData) throws Exception{
		return new java.sql.Date( sData.getTime() );
	}
	
	public static Date avancarDataComQtDias(Date pDataReferencia, int pQtDias) {
		Calendar calendario = Calendar.getInstance();

		calendario.setTime(pDataReferencia);  
		calendario.add(Calendar.DATE, pQtDias);

		return new Date(calendario.getTime().getTime());
	}
	
	public static Calendar getCalendarPopulado(int hora, int minuto){
		
		Calendar calData = Calendar.getInstance();
		calData.set(Calendar.HOUR, hora); 
		calData.set(Calendar.MINUTE, minuto);
		calData.set(Calendar.SECOND, 00);
		calData.set(Calendar.AM_PM, 0);
		
		return calData;
	}
	
	public static boolean  comparaDataMaiorQueDiaDeHoje(final Date data) {   
 
        String sDtHoje =  dataToStringInvertida(new Date());  
        String sDtInformada  = dataToStringInvertida(data);  

        if(sDtHoje.compareTo(sDtInformada) > 0 )  {	
        	return true;  
        }else {
            return false;
        }
        
    } 
	
	public static String formataHoraCorrente(){
		return formatHora(new Date());
	}
	
	public static String formatHora(Date pData){
		SimpleDateFormat formatador = new SimpleDateFormat( "HH:mm" );
		return formatador.format(pData);
	}
	
	private static String dataToStringInvertida(Date pData) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
		return formatador.format(pData);
	}
	

}
