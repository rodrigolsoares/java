package br.com.gestao.salao.vo;

import java.io.Serializable;

public class HorarioVO implements Serializable {

	private static final long serialVersionUID = 8231650945088625961L;
	private String hora;
	

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return hora;
	}
	
	

}
