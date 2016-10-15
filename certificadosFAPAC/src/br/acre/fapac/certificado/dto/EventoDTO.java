package br.acre.fapac.certificado.dto;

public class EventoDTO {
	private int IDEvento ;
	private String NomeEvento;
	private String Patrocinadores;
	private String CargaHoraria;
	private String NomePrograma;
	private String CidadeEvento;
	private String EstadoEvento;
	private String DataInicio;
	private String DataFinal;
	
	public int getIDEvento() {
		return IDEvento;
	}
	public void setIDEvento(int iDEvento) {
		IDEvento = iDEvento;
	}
	public String getNomeEvento() {
		return NomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		NomeEvento = nomeEvento;
	}
	public String getPatrocinadores() {
		return Patrocinadores;
	}
	public void setPatrocinadores(String patrocinadores) {
		Patrocinadores = patrocinadores;
	}
	public String getCargaHoraria() {
		return CargaHoraria;
	}
	public void setCargaHoraria(String cargaHoraria) {
		CargaHoraria = cargaHoraria;
	}
	public String getNomePrograma() {
		return NomePrograma;
	}
	public void setNomePrograma(String nomePrograma) {
		NomePrograma = nomePrograma;
	}
	public String getCidadeEvento() {
		return CidadeEvento;
	}
	public void setCidadeEvento(String cidadeEvento) {
		CidadeEvento = cidadeEvento;
	}
	public String getEstadoEvento() {
		return EstadoEvento;
	}
	public void setEstadoEvento(String estadoEvento) {
		EstadoEvento = estadoEvento;
	}
	public String getDataInicio() {
		return DataInicio;
	}
	public void setDataInicio(String dataInicio) {
		DataInicio = dataInicio;
	}
	public String getDataFinal() {
		return DataFinal;
	}
	public void setDataFinal(String dataFinal) {
		DataFinal = dataFinal;
	}
	
}
