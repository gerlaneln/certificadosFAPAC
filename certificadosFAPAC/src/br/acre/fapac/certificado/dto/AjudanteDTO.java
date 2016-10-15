package br.acre.fapac.certificado.dto;

public class AjudanteDTO {
	private int IDAjudante;
	private String NomeAjudante;
	private String CpfAjudante;
	private String SenhaAjudante;
	private CertificadoAjudanteDTO CertificadoAjudanteIDCertificado;
	
	
	public int getIDAjudante() {
		return IDAjudante;
	}
	public void setIDAjudante(int iDAjudante) {
		IDAjudante = iDAjudante;
	}
	public String getNomeAjudante() {
		return NomeAjudante;
	}
	public void setNomeAjudante(String nomeAjudante) {
		NomeAjudante = nomeAjudante;
	}
	public String getCpfAjudante() {
		return CpfAjudante;
	}
	public void setCpfAjudante(String cPFAjudante) {
		CpfAjudante = cPFAjudante;
	}
	public String getSenhaAjudante() {
		return SenhaAjudante;
	}
	public void setSenhaAjudante(String senhaAjudante) {
		SenhaAjudante = senhaAjudante;
	}
	public CertificadoAjudanteDTO getCertificadoAjudanteIDCertificado() {
		return CertificadoAjudanteIDCertificado;
	}
	public void setCertificadoAjudanteIDCertificado(CertificadoAjudanteDTO certificadoAjudanteIDCertificado) {
		CertificadoAjudanteIDCertificado = certificadoAjudanteIDCertificado;
	}
	
}
