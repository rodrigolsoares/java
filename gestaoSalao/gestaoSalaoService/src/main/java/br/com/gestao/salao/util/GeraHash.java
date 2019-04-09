package br.com.gestao.salao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** * @author Rodrigo **/
public class GeraHash {
	
	/**
	 * @param frase
	 * @param algoritmo
	 * @return Converte o hash em String
	 */
	private static String stringHexa(byte[] bytes) {
		   StringBuilder s = new StringBuilder();
		   for (int i = 0; i < bytes.length; i++) {
		       int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
		       int parteBaixa = bytes[i] & 0xf;
		       if (parteAlta == 0) s.append('0');
		       s.append(Integer.toHexString(parteAlta | parteBaixa));
		   }
		   return s.toString();
		}
	
	/**
	 * @param valor
	 * @return Hash
	 * @throws Exception
	 */
	public static String hashGerado(String valor)throws Exception{
		
		return stringHexa(gerarHash(valor , "MD5"));
	}
	
	/**
	 * @param frase
	 * @param algoritmo
	 * @return Hash gerado
	 */
	private static byte[] gerarHash(String frase, String algoritmo) {
	  try {
	    MessageDigest md = MessageDigest.getInstance(algoritmo);
	    md.update(frase.getBytes());
	    return md.digest();
	  } catch (NoSuchAlgorithmException e) {
	    return null;
	  }
	}
	
	/*public static void main(String args[]){
		try{
			System.out.println(hashGerado("adm"));
		}catch (Exception e) {
			// TODO: handle exception
		}
	}*/

}
