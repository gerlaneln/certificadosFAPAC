package br.acre.fapac.certificado.dto;

public class OrientadorDTO {
	private int IDOrientador;
	private String NomeOrientador;
	private String CPFOrientador;
	private String SenhaOrientador;
	private CertificadoOrientadorDTO CertificadoOrientadorIDCertificado;

	public int getIDOrientador() {
		return IDOrientador;
	}
	public void setIDOrientador(int iDOrientador) {
		IDOrientador = iDOrientador;
	}
	public String getNomeOrientador() {
		return NomeOrientador;
	}
	public void setNomeOrientador(String nomeOrientador) {
		NomeOrientador = nomeOrientador;
	}
	public String getCPFOrientador() {
		return CPFOrientador;
	}
	public void setCPFOrientador(String cPFOrientador) {
		CPFOrientador = cPFOrientador;
	}
	public String getSenhaOrientador() {
		return SenhaOrientador;
	}
	public void setSenhaOrientador(String senhaOrientador) {
		SenhaOrientador = senhaOrientador;
	}
	public CertificadoOrientadorDTO getCertificadoOrientadorIDCertificado() {
		return CertificadoOrientadorIDCertificado;
	}
	public void setCertificadoOrientadorIDCertificado(CertificadoOrientadorDTO certificadoOrientadorIDCertificado) {
		CertificadoOrientadorIDCertificado = certificadoOrientadorIDCertificado;
	}

}
