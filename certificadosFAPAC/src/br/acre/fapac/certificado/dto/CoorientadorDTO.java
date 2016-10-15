package br.acre.fapac.certificado.dto;

public class CoorientadorDTO {
	private int IDCoorientador;
	private String NomeCoorientador;
	private String CPFCoorientador;
	private String SenhaCoorientador;
	private CertificadoCoorientadorDTO CertificadoCoorientadorIDCertificado;
	
	public int getIDCoorientador() {
		return IDCoorientador;
	}
	public void setIDCoorientador(int iDCoorientador) {
		IDCoorientador = iDCoorientador;
	}
	public String getNomeCoorientador() {
		return NomeCoorientador;
	}
	public void setNomeCoorientador(String nomeCoorientador) {
		NomeCoorientador = nomeCoorientador;
	}
	public String getCPFCoorientador() {
		return CPFCoorientador;
	}
	public void setCPFCoorientador(String cpfCoorientador) {
		CPFCoorientador = cpfCoorientador;
	}
	public String getSenhaCoorientador() {
		return SenhaCoorientador;
	}
	public void setSenhaCoorientador(String senhaCoorientador) {
		SenhaCoorientador = senhaCoorientador;
	}
	public CertificadoCoorientadorDTO getCertificadoCoorientadorIDCertificado() {
		return CertificadoCoorientadorIDCertificado;
	}
	public void setCertificadoCoorientadorIDCertificado(CertificadoCoorientadorDTO certificadoCoorientadorIDCertificado) {
		CertificadoCoorientadorIDCertificado = certificadoCoorientadorIDCertificado;
	}
	
}
