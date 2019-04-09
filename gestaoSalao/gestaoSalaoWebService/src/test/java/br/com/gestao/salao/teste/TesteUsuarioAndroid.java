package br.com.gestao.salao.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import br.com.gestao.salao.vo.UsuarioAndroidVO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TesteUsuarioAndroid {
	
	private static final String URL_USUARIO = "http://localhost:8080/gestaoSalaoWebService/rest/getUsuarioAndroid/inserir?usuarioJson=";
	
	public static void main(String args[]){
		
		try {
			
			UsuarioAndroidVO usuario = new UsuarioAndroidVO();
			usuario.setNome("Rodrigo Luiz Soares");
			usuario.setNumeroCeluar("011973356678");
			usuario.setEmail("rodrigolsoares@gmail.com");
			
			String json = converterAgendaVOemJson(usuario);
			String resultado = GET(URL_USUARIO + URLEncoder.encode(json, "UTF-8"));
			
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
	
	private static String converterAgendaVOemJson(UsuarioAndroidVO usuario){
		
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(usuario);
			
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
