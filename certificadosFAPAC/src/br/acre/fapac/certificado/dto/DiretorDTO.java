package br.acre.fapac.certificado.dto;

public class DiretorDTO {
	private int IDDiretor;
	private String NomeDiretor;
	private String cargoDiretor;
	private String NDecreto;

	public int getIDDiretor() {
		return IDDiretor;
	}
	public void setIDDiretor(int iDDiretor) {
		IDDiretor = iDDiretor;
	}
	public String getNomeDiretor() {
		return NomeDiretor;
	}
	public void setNomeDiretor(String nomeDireto) {
		NomeDiretor = nomeDireto;
	}
	public String getCargoDiretor() {
		return cargoDiretor;
	}
	public void setCargoDiretor(String cargoDiretor) {
		this.cargoDiretor = cargoDiretor;
	}
	public String getNDecreto() {
		return NDecreto;
	}
	public void setNDecreto(String nDecreto) {
		NDecreto = nDecreto;
	}
}
