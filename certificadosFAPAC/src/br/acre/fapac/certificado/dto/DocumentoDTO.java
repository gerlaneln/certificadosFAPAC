package br.acre.fapac.certificado.dto;

import java.util.Date;

public class DocumentoDTO {
	private int IDDocumento;
	private byte[] documento;
	private String nomeDocumento;
	private String Cpf;
	private String evento;
	private String data;
	private String token;
	
	public String getnomeDocumento(){
		return nomeDocumento;
	}
	public void setnomeDocumento(String nomeDocumento){
		this.nomeDocumento = nomeDocumento;
	}
	public int getIDDocumento() {
		return IDDocumento;
	}
	public void setIDDocumento(int iDDocumento) {
		IDDocumento = iDDocumento;
	}
	public byte[] getDocumento() {
		return documento;
	}
	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}
	public String getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		Cpf = cpf;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getToken(){
		return token;
	}
	public void setToken(String token){
		this.token = token;
	}
	
}
