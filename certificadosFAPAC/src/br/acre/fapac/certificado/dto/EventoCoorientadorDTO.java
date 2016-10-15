package br.acre.fapac.certificado.dto;

public class EventoCoorientadorDTO {
	private int idEvento;
	private int idCoorientador;
	
	public int getIdCoorientador() {
		return idCoorientador;
	}
	public void setIdCoorientador(int idCoorientador) {
		this.idCoorientador = idCoorientador;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
}
