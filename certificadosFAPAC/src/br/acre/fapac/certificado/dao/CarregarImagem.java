package br.acre.fapac.certificado.dao;

public class CarregarImagem {

	public static void main(String[] args) {
		
		ImagemDAO imagemDAO = new ImagemDAO();
		
		try {
			imagemDAO.converterImagemToByte("C:/Users/Gerlâne/Downloads/fapac.png", "FAPAC");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
