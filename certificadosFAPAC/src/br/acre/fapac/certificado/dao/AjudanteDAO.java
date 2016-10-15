package br.acre.fapac.certificado.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Statement;

import br.acre.fapac.certificado.conexao.ConexaoUtil;
import br.acre.fapac.certificado.dto.AjudanteDTO;
import br.acre.fapac.certificado.dto.CertificadoAjudanteDTO;
import br.acre.fapac.certificado.dto.DiretorDTO;
import br.acre.fapac.certificado.dto.DocumentoDTO;
import br.acre.fapac.certificado.dto.EventoAjudanteDTO;
import br.acre.fapac.certificado.dto.EventoDTO;
import br.acre.fapac.certificado.dto.TokensDTO;
import br.acre.fapac.certificado.exception.LogicException;
import br.acre.fapac.certificado.exception.PersistenciaException;
import br.acre.fapac.certificado.gerar.gerarTokens;

public class AjudanteDAO {

	public static String nome, patrocinadores, dataFim, nomeDUm, cargoDUm, decretoDUm, nomeDDois, cargoDDois,
			decretoDDois, token, cidade, estado, nomeprograma;
	public static final String IMAGE = "C:/Users/Gerlâne/Downloads/template.png";
	public static final String CAPES = "C:/Users/Gerlâne/Downloads/capes.png";
	public static final String CNPQ = "C:/Users/Gerlâne/Downloads/cnpq.png";
	public static final String FAPAC = "C:/Users/Gerlâne/Downloads/fapac.png";
	public static String govAcre = "C:/Users/Gerlâne/Downloads/governoAcre.png";
	public static String govBrasil = "C:/Users/Gerlâne/Downloads/governoBrasil.jpg";
	public static int i = 0;
	public EventoDAO eventoDB = new EventoDAO();
	public DiretorDAO diretorDB = new DiretorDAO();
	public DocumentoDAO documentoDB = new DocumentoDAO();
	public TokensDAO tokenDB = new TokensDAO();

	public int inserir(AjudanteDTO ajudanteDTO) throws PersistenciaException {
		int chave = 0;

		try {

			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "INSERT INTO ajudante (NomeAjudante, SenhaAjudante, CPFAjudante)" + " VALUES(?, ?, ?);";

			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, ajudanteDTO.getNomeAjudante());
			statement.setString(2, ajudanteDTO.getSenhaAjudante());
			statement.setString(3, ajudanteDTO.getCpfAjudante());

			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				chave = result.getInt(1);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return chave;
	}


	public void atualizar(AjudanteDTO ajudanteDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "UPDATE ajudante" + " SET NomeAjudante = ?," + " SenhaAjudante = ?," + " CPFAjudante = ?"
					+ " WHERE IDAjudante = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setString(1, ajudanteDTO.getNomeAjudante());
			Statement.setString(2, ajudanteDTO.getSenhaAjudante());
			Statement.setString(3, ajudanteDTO.getCpfAjudante());
			Statement.setInt(4, ajudanteDTO.getIDAjudante());

			Statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}


	public void deletar(Integer id) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM ajudante WHERE IDAjudante = ?;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, id);
			Statement.execute();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}

	}


	public void deletartudo() throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM ajudante;";

			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	
	public List<AjudanteDTO> listarTodos() throws PersistenciaException {
		List<AjudanteDTO> listaAjudantes = new ArrayList<AjudanteDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM ajudante;";

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				AjudanteDTO ajudanteDTO = new AjudanteDTO();
				ajudanteDTO.setIDAjudante(resultSet.getInt(1));
				ajudanteDTO.setNomeAjudante(resultSet.getString(2));
				ajudanteDTO.setSenhaAjudante(resultSet.getString(3));
				ajudanteDTO.setCpfAjudante(resultSet.getString(4));

				listaAjudantes.add(ajudanteDTO);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaAjudantes;
	}

	public AjudanteDTO buscarAjudantePoID(int id) throws PersistenciaException {
		AjudanteDTO ajudanteDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM ajudante WHERE IDAjudante = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				ajudanteDTO = new AjudanteDTO();
				ajudanteDTO.setIDAjudante(resultSet.getInt(1));
				ajudanteDTO.setNomeAjudante(resultSet.getString(2));
				ajudanteDTO.setSenhaAjudante(resultSet.getString(3));
				ajudanteDTO.setCpfAjudante(resultSet.getString(4));

			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return ajudanteDTO;
	}
	
	public AjudanteDTO buscarAjudantePorCPF(String cpf) throws PersistenciaException {
		AjudanteDTO ajudanteDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();

			String sql = "SELECT * FROM ajudante WHERE CPFAjudante = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cpf);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				ajudanteDTO = new AjudanteDTO();
				ajudanteDTO.setIDAjudante(resultSet.getInt(1));
				ajudanteDTO.setNomeAjudante(resultSet.getString(2));
				ajudanteDTO.setSenhaAjudante(resultSet.getString(3));
				ajudanteDTO.setCpfAjudante(resultSet.getString(4));

			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return ajudanteDTO;
	}


	public File gerarCertificadoAjudante(int idEvento, String diretores) {
		File file = null;
		DocumentoDAO documentoDB = new DocumentoDAO();
		DocumentoDTO documentoDTO = new DocumentoDTO();
		
		int diretor1, diretor2;
		String[] split = diretores.split(", ");
		String[] a = split[0].toString().split("");
		diretor1 = Integer.parseInt(a[1]);
		System.out.println(diretor1);
		String[] b = split[1].toString().split("");
		diretor2 = Integer.parseInt(b[0]);
		System.out.println(diretor2);
		EventoDAO eventoDB = new EventoDAO();
		AjudanteDAO ajudanteDB = new AjudanteDAO();
		try {
			System.out.println(idEvento);
			List<EventoAjudanteDTO> eventoAjudantes = new ArrayList<EventoAjudanteDTO>();
			eventoAjudantes = eventoDB.listarTodosEventoAjudante(idEvento);
			for (EventoAjudanteDTO eventoAjudante : eventoAjudantes) {
				AjudanteDTO ajudante = ajudanteDB.buscarAjudantePoID(eventoAjudante.getIdAjudante());
				EventoDTO evento = eventoDB.buscarEventoPoID(eventoAjudante.getIdEvento());
				
				TokensDTO tokenDTO = new TokensDTO();
				String tokenC = gerarTokens.criaTokens();
				tokenDTO = tokenDB.buscarTokensPorToken(tokenC);
				if(tokenDTO != null){
					while(tokenDTO != null){
						tokenC = gerarTokens.criaTokens();
						tokenDTO = tokenDB.buscarTokensPorToken(tokenC);
					}
				}
				TokensDTO t = new TokensDTO();
				t.setToken(tokenC);
				tokenDB.inserir(t);
				System.out.println(t.getToken());
				
				file = createPdf(evento.getIDEvento(), ajudante.getIDAjudante(),
						diretor1, diretor2, t.getToken());
				
				documentoDTO.setnomeDocumento(String.valueOf(ajudante.getCpfAjudante() + evento.getNomeEvento()));
				byte[] doc = new byte[(int) (file).length()];
				java.io.DataInputStream is = new java.io.DataInputStream(new FileInputStream(file));
				is.readFully(doc);
				is.close();
				
				documentoDTO.setCpf(ajudante.getCpfAjudante());
				documentoDTO.setEvento(evento.getNomeEvento());
				documentoDTO.setData(evento.getDataFinal());
				documentoDTO.setDocumento(doc);
				documentoDTO.setToken(t.getToken());
				
				documentoDTO.setToken(tokenC);
				int chave = documentoDB.inserir(documentoDTO);
			}
		} catch (PersistenciaException | IOException | DocumentException e) {
			e.printStackTrace();
		}
		
		return file;
	}


	private File createPdf(int idEvento, int idAjudante, int diretorUm, int diretorDois, String tokenCert)
			throws IOException, DocumentException, PersistenciaException {
		DiretorDTO diretorU, diretorD;
		diretorU = diretorDB.buscarDiretorPoID(diretorUm);
		nomeDUm = diretorU.getNomeDiretor();
		cargoDUm = diretorU.getCargoDiretor();
		decretoDUm = diretorU.getNDecreto();

		diretorD = diretorDB.buscarDiretorPoID(diretorDois);
		nomeDDois = diretorD.getNomeDiretor();
		cargoDDois = diretorD.getCargoDiretor();
		decretoDDois = diretorD.getNDecreto();

		EventoDTO evento = eventoDB.buscarEventoPoID(idEvento);
		AjudanteDTO ajudante = buscarAjudantePoID(idAjudante);
		nome = ajudante.getNomeAjudante();
		dataFim = evento.getDataFinal();
		cidade = evento.getCidadeEvento();
		estado = evento.getEstadoEvento();
		nomeprograma = evento.getNomeEvento();

		Image image = Image.getInstance(IMAGE);
		Image capes = Image.getInstance(CAPES);
		Image cnpq = Image.getInstance(CNPQ);
		Image fapac = Image.getInstance(FAPAC);

		File file = null;
		OutputStream documento = null;
		Document document = new Document(image, 72, 72, 90, 10); // esquerda,
																	// direita,
																	// cima,
																	// baixo
		String nomeCaminho = ajudante.getCpfAjudante()+evento.getNomeEvento()+".pdf";
		file = new File(nomeCaminho);
		documento = new FileOutputStream(file);
		PdfWriter writer = PdfWriter.getInstance(document, documento);
		document.open();
		BaseFont bf = BaseFont.createFont();

		String pat = evento.getPatrocinadores();
		String um = pat.replace(";", "");
		String dois = um.replace("[", "");
		String tres = dois.replace("]", "/");
		String[] div = tres.split("/");
		i = div.length;

		switch (i) {
		case 1:
			patrocinadores = div[0];
			switch (patrocinadores) {
			case "FAPAC":
				fapac.setAbsolutePosition(645, 490);
				document.add(fapac);
				break;
			case "CNPQ":
				cnpq.setAbsolutePosition(645, 490);
				document.add(cnpq);
				break;
			case "CAPES":
				capes.setAbsolutePosition(645, 480);
				document.add(capes);
				break;
			}
			break;
		case 2:
			patrocinadores = div[0] + "/" + div[1];
			String patroUm = div[0];
			String patroDois = div[1];
			switch (patroUm) {
			case "FAPAC":
				fapac.setAbsolutePosition(200, 490);
				document.add(fapac);
				break;
			case "CNPQ":
				cnpq.setAbsolutePosition(200, 490);
				document.add(cnpq);
				break;
			case "CAPES":
				capes.setAbsolutePosition(200, 480);
				document.add(capes);
				break;
			}
			switch (patroDois) {
			case "FAPAC":
				fapac.setAbsolutePosition(645, 490);
				document.add(fapac);
				break;
			case "CNPQ":
				cnpq.setAbsolutePosition(645, 490);
				document.add(cnpq);
				break;
			case "CAPES":
				capes.setAbsolutePosition(645, 480);
				document.add(capes);
				break;
			}
			break;
		case 3:
			patrocinadores = div[0] + "/" + div[1] + "/" + div[2];
			fapac.setAbsolutePosition(400, 500);
			document.add(fapac);
			cnpq.setAbsolutePosition(700, 490);
			document.add(cnpq);
			capes.setAbsolutePosition(150, 480);
			document.add(capes);
			break;
		}

		Paragraph titulo = new Paragraph("CERTIFICADO", new Font(bf, 20));
		titulo.setAlignment(Element.ALIGN_CENTER);
		document.add(titulo);
		document.add(Chunk.NEWLINE);

		String pUm = "A Fundação de Amparo à Pesquisa do Estado do Acre agradece a dedicação do(a) servidor(a) ";
		String pDois = "na coordenação do Programa de Bolsas ";
		String pTres = "sempre atendendo com presteza e solicitude os orientadores"
				+ "e bolsistas partícipes deste Programa e contagiando toda a equipe da FAPAC com sua alegria.";

		Paragraph p = new Paragraph(pUm + nome + pDois + nomeprograma + patrocinadores + pTres, new Font(bf, 17));

		Paragraph q = new Paragraph(" " + "\n" + nomeDUm + "\n" + cargoDUm + "\n" + "Dec. " + decretoDUm + ".",
				new Font(bf, 12));
		q.setAlignment(Element.ALIGN_CENTER);

		Paragraph r = new Paragraph(" " + "\n" + nomeDDois + "\n" + cargoDDois + "\n" + "Dec. " + decretoDDois + ".",
				new Font(bf, 12));
		r.setAlignment(Element.ALIGN_CENTER);

		PdfPTable table1 = new PdfPTable(2);

		PdfPCell celula1 = new PdfPCell();
		celula1.addElement(q);
		celula1.setBorder(Rectangle.NO_BORDER);

		PdfPCell celula2 = new PdfPCell();
		celula2.addElement(r);
		celula2.setBorder(Rectangle.NO_BORDER);

		table1.setWidthPercentage(100.0f);
		table1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table1.addCell(celula1);
		table1.addCell(celula2);

		// tamanho da fonte
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		float indenation = bf.getWidthPoint(nomeCaminho, 2); // espaçamento
		p.setFirstLineIndent(indenation); // espaçamento na primeira linha
		document.add(p);

		PdfContentByte canvas = writer.getDirectContentUnder();
		image.setAbsolutePosition(0, 0);
		canvas.addImage(image);
		document.setPageSize(image);
		document.add(Chunk.NEWLINE);

		String[] data = new String[2];
		data = dataFim.split("/");
		int mes = Integer.parseInt(data[1]);
		String mesAno = null;
		switch (mes) {
		case 1:
			mesAno = "janeiro";
			break;
		case 2:
			mesAno = "fevereiro";
			break;
		case 3:
			mesAno = "março";
			break;
		case 4:
			mesAno = "abril";
			break;
		case 5:
			mesAno = "maio";
			break;
		case 6:
			mesAno = "junho";
			break;
		case 7:
			mesAno = "julho";
			break;
		case 8:
			mesAno = "agosto";
			break;
		case 9:
			mesAno = "setembro";
			break;
		case 10:
			mesAno = "outubro";
			break;
		case 11:
			mesAno = "novembro";
			break;
		case 12:
			mesAno = "dezembro";
			break;
		}
		document.add(Chunk.NEWLINE);
		Paragraph dataCompleta = new Paragraph(
				cidade + " - " + estado + ", " + data[0] + " de " + mesAno + " de " + data[2] + ".", new Font(bf, 16));
		dataCompleta.setAlignment(Element.ALIGN_RIGHT);
		document.add(dataCompleta);
		document.add(Chunk.NEWLINE);

		document.add(table1);
		document.add(Chunk.NEWLINE);

		PdfPTable tableFim = new PdfPTable(3);
		tableFim.setWidthPercentage(100.0f);
		tableFim.setWidths(new int[] { 8, 2, 1 });
		tableFim.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		
		token = tokenCert;
		Paragraph codigo = new Paragraph("Código de validação: " + token, new Font(bf, 11));
		codigo.setAlignment(Element.ALIGN_LEFT);
		PdfPCell celulaCodigo = new PdfPCell();
		celulaCodigo.addElement(codigo);
		celulaCodigo.setBorder(Rectangle.NO_BORDER);

		Image governoAcre = Image.getInstance(govAcre);
		governoAcre.scaleAbsoluteHeight(20);
		governoAcre.setAlignment(Element.ALIGN_CENTER);
		Image governoBrasil = Image.getInstance(govBrasil);
		governoBrasil.scaleAbsoluteHeight(20);
		governoBrasil.setAbsolutePosition(0, 0);
		governoBrasil.setAlignment(Element.ALIGN_RIGHT);
		PdfPCell celulaGovA = new PdfPCell();
		celulaGovA.addElement(governoAcre);
		celulaGovA.setBorder(Rectangle.NO_BORDER);
		PdfPCell celulaGovB = new PdfPCell();
		celulaGovB.addElement(governoBrasil);
		celulaGovB.setBorder(Rectangle.NO_BORDER);

		tableFim.addCell(celulaCodigo);
		tableFim.addCell(celulaGovA);
		tableFim.addCell(celulaGovB);

		document.add(tableFim);

		document.close();

		return file;
	}

}
