package br.com.exercicio.exception;

public class FilmeSemEstoqueException extends Exception{

	private static final long serialVersionUID = 7480923874958258664L;
	
	public FilmeSemEstoqueException(String message) {
		super(message);
	}
}
