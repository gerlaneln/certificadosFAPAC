package br.acre.fapac.certificado.dto;

public class PatrocinadoresDTO {
	private int idPatrocinador;
	private byte[] imagem;
	private String nomePatrocinador;
	
	public int getIdPatrocinadores() {
		return idPatrocinador;
	}
	public void setIdPatrocinadores(int idPatrocinador) {
		this.idPatrocinador = idPatrocinador;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public String getNomePatrocinador() {
		return nomePatrocinador;
	}
	public void setNomePatrocinador(String nomePatrocinador) {
		this.nomePatrocinador = nomePatrocinador;
	}
}
