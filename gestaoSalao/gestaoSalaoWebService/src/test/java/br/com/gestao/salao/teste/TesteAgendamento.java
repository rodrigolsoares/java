package br.com.gestao.salao.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import br.com.gestao.salao.vo.AgendaVO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TesteAgendamento {
	
	private static final String URL_AGENDA = "http://localhost:8080/gestaoSalaoWebService/rest/getAgenda/inserir?agendaJson=";
	
	public static void main(String args[]){
		
		try {
			
			AgendaVO agenda = new AgendaVO();
			agenda.setCodigoCliente(2);
			agenda.setCodigoFuncionario(2);
			agenda.setData(new Date());
			agenda.setHora("09:30");
			agenda.setValor(50.0);
			agenda.setValorTotal(0.0);
			agenda.setPorcentagem(50);
			agenda.getUsuarioAndroid().getSalao().setCodigo(9);
			agenda.getUsuarioAndroid().getResponsavel().setCodigo(9);
			
			agenda.getListaServicoSelecionado().add("1");
			
			
			String json = converterAgendaVOemJson(agenda);
			String resultado = GET(URL_AGENDA + URLEncoder.encode(json, "UTF-8"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	private static String GET(String valor){

        String result = "";
        try {
 
        	URL url = new URL(valor);
        	HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        	conn.setRequestProperty("Request-Method", "GET");
        	conn.setDoInput(true);
        	conn.setDoOutput(false);
        	conn.connect();
        	
        	result = readStream(conn.getInputStream());
        	
 
        } catch (Exception e) {
           e.printStackTrace();
        }
 
        return result;
    }
	
	private static String converterAgendaVOemJson(AgendaVO agenda){
		
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(agenda);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}  
	}

	
	private static String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}

}
