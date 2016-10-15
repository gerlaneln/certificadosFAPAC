package br.acre.fapac.certificado.dto;

public class ProjetoDTO {
	private int IDProjeto;
	private String TituloProjeto;
	private OrientadorDTO IDOrientador ;
	private CoorientadorDTO IDCoorientador;
	private EventoDTO IDEvento;
	
	public int getIDProjeto() {
		return IDProjeto;
	}
	public void setIDProjeto(int iDProjeto) {
		IDProjeto = iDProjeto;
	}
	public String getTituloProjeto() {
		return TituloProjeto;
	}
	public void setTituloProjeto(String tituloProjeto) {
		TituloProjeto = tituloProjeto;
	}
	public OrientadorDTO getIDOrientador() {
		return IDOrientador;
	}
	public void setIDOrientador(OrientadorDTO iDOrientador) {
		IDOrientador = iDOrientador;
	}
	public CoorientadorDTO getIDCoorientador() {
		return IDCoorientador;
	}
	public void setIDCoorientador(CoorientadorDTO iDCoorientador) {
		IDCoorientador = iDCoorientador;
	}
	public EventoDTO getIDEvento() {
		return IDEvento;
	}
	public void setIDEvento(EventoDTO iDEvento) {
		IDEvento = iDEvento;
	}

}
