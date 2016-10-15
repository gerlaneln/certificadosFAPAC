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
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
import br.acre.fapac.certificado.dto.AlunoDTO;
import br.acre.fapac.certificado.dto.CoorientadorDTO;
import br.acre.fapac.certificado.dto.DiretorDTO;
import br.acre.fapac.certificado.dto.DocumentoDTO;
import br.acre.fapac.certificado.dto.EventoAlunoDTO;
import br.acre.fapac.certificado.dto.EventoDTO;
import br.acre.fapac.certificado.dto.OrientadorDTO;
import br.acre.fapac.certificado.dto.ProjetoAlunoDTO;
import br.acre.fapac.certificado.dto.ProjetoDTO;
import br.acre.fapac.certificado.dto.TokensDTO;
import br.acre.fapac.certificado.exception.PersistenciaException;
import br.acre.fapac.certificado.gerar.gerarTokens;

public class AlunoDAO {
	
	public static String nome, patrocinadores, dataFim, dataInicio, projeto, cidade, estado, nomeDUm, cargoDUm,
			decretoDUm, nomeDDois, cargoDDois, decretoDDois, token, orientador, coorientador, cpfaluno, nomeprograma;
	public static final String IMAGE = "C:/Users/Gerlâne/Downloads/template.png";
	public static final String CAPES = "C:/Users/Gerlâne/Downloads/capes.png";
	public static final String CNPQ = "C:/Users/Gerlâne/Downloads/cnpq.png";
	public static final String FAPAC = "C:/Users/Gerlâne/Downloads/fapac.png";
	public static String govAcre = "C:/Users/Gerlâne/Downloads/governoAcre.png";
	public static String govBrasil = "C:/Users/Gerlâne/Downloads/governoBrasil.jpg";
	public static int i = 0;
	private EventoDAO eventoDB = new EventoDAO();
	private DiretorDAO diretorDB = new DiretorDAO();
	private ProjetoDAO projetoDB = new ProjetoDAO();
	private OrientadorDAO orientadorDB = new OrientadorDAO();
	private CoorientadorDAO coorientadorDB = new CoorientadorDAO();
	private TokensDAO tokenDB = new TokensDAO();

	public int inserir(AlunoDTO alunoDTO) throws PersistenciaException {
		int chave = 0;

		try{
			
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql ="INSERT INTO aluno(CPFAluno, NomeAluno, SenhaAluno)"
			+ " VALUES(?, ?, ?);";
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, alunoDTO.getCPFAluno());
			statement.setString(2, alunoDTO.getNomeAluno());
			statement.setString(3, alunoDTO.getSenhaAluno());

			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
		       if(result.next()){
		         chave = result.getInt(1);  
		       }
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e ) ;
		}
		return chave;
	}
	
	public void atualizar(AlunoDTO alunoDTO) throws PersistenciaException {
		try{
			Connection connection =  ConexaoUtil.getInstance().getConnection();
			
			String sql =  "UPDATE aluno " + "SET CPFAluno =?,"
			+ " NomeAluno =?,"  
			+ " SenhaAluno =?"
			+ " WHERE IDAluno =?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setString(1, alunoDTO.getCPFAluno());
			Statement.setString(2, alunoDTO.getNomeAluno());
			Statement.setString(3, alunoDTO.getSenhaAluno());
			Statement.setInt(4, alunoDTO.getIDAluno());
			
			Statement.execute();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	//inserindo id do certificado 
	
	

	
	public void deletar(Integer id) throws PersistenciaException {
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM aluno WHERE IDAluno = ?;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.setInt(1, id);
			Statement.execute();
			connection.close();
		
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
		
	}
	
	
	public void deletartudo() throws PersistenciaException{
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "DELETE FROM aluno;";
			
			PreparedStatement Statement = connection.prepareStatement(sql);
			Statement.execute();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(),e);
		}
	}
		
	
	public List<AlunoDTO> listarTodos() throws PersistenciaException {
		List<AlunoDTO> listaAlunos = new ArrayList<AlunoDTO>();
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM aluno;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()){ 
				 AlunoDTO alunoDTO = new AlunoDTO();
				 alunoDTO.setIDAluno(resultSet.getInt(1));
				 alunoDTO.setCPFAluno(resultSet.getString(2));
				 alunoDTO.setNomeAluno(resultSet.getString(3));
				 alunoDTO.setSenhaAluno(resultSet.getString(4));
				// alunoDTO.setCertificadoAlunoIDCertificado(buscarCertificadoAlunoPorID(resultSet.getInt(5)));
		
				 
				
				 listaAlunos.add(alunoDTO);
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaAlunos;
	}
	public AlunoDTO buscarAlunoPoID(int id) throws PersistenciaException {
		 AlunoDTO alunoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM aluno WHERE IDAluno = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setInt(1, id);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 alunoDTO = new AlunoDTO();
				 alunoDTO.setIDAluno(resultSet.getInt(1));
				 alunoDTO.setCPFAluno(resultSet.getString(2));
				 alunoDTO.setNomeAluno(resultSet.getString(3));
				 alunoDTO.setSenhaAluno(resultSet.getString(4));
				// alunoDTO.setCertificadoAlunoIDCertificado(buscarCertificadoAlunoPorID(resultSet.getInt(5)));
		
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return alunoDTO;
	}
	
	public AlunoDTO buscarAlunoPorCPF(String cpf) throws PersistenciaException {
		 AlunoDTO alunoDTO = null;
		try{
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM aluno WHERE CPFAluno = ?;";
			
			 PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setString(1, cpf);
			 ResultSet resultSet = statement.executeQuery();
			 
			 if(resultSet.next()){ 
				 alunoDTO = new AlunoDTO();
				 alunoDTO.setIDAluno(resultSet.getInt(1));
				 alunoDTO.setCPFAluno(resultSet.getString(2));
				 alunoDTO.setNomeAluno(resultSet.getString(3));
				 alunoDTO.setSenhaAluno(resultSet.getString(4));
				 //alunoDTO.setCertificadoAlunoIDCertificado(buscarCertificadoAlunoPorID(resultSet.getInt(5)));
		
			 }
			 connection.close();

		}catch(Exception e){
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return alunoDTO;
	}
	
	
	public void gerarCertificadoBolsista(int idProjeto, int idEvento, String diretores, int idCoorientador){
		File file = null;
		DocumentoDAO documentoDB = new DocumentoDAO();
		DocumentoDTO documentoDTO = new DocumentoDTO();
		
		int diretor1, diretor2;
		String[] split = diretores.split(", ");
		String[] a = split[0].toString().split("");
		diretor1 = Integer.parseInt(a[1]);
		String[] b = split[1].toString().split("");
		diretor2 = Integer.parseInt(b[0]);
		
		System.out.println(idCoorientador);
				
		try {
			System.out.println(idEvento);
			List<EventoAlunoDTO> eventoAlunos = new ArrayList<EventoAlunoDTO>();
			eventoAlunos = eventoDB.listarTodosEventoAluno(idEvento);
			
			for (EventoAlunoDTO eventoAluno : eventoAlunos) {
				AlunoDTO aluno = buscarAlunoPoID(eventoAluno.getIdAluno());
				EventoDTO evento = eventoDB.buscarEventoPoID(eventoAluno.getIdEvento());
				int idProj = projetoDB.buscarProjetoAlunoPoIDA(aluno.getIDAluno());
				ProjetoDTO projeto = projetoDB.buscarProjetoPoID(idProj);
				int idOrientador = projetoDB.buscarProjetoOrientadorPoIDP(projeto.getIDProjeto());
				if(idCoorientador == 0){
					idCoorientador = 0;
				}else{
					idCoorientador =  projetoDB.buscarProjetoCoorientadorPoIDP(projeto.getIDProjeto());
				}
				
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
				
				
				file = createPdf(projeto.getIDProjeto(), evento.getIDEvento(), aluno.getIDAluno(), diretor1, diretor2,
						idOrientador, idCoorientador, t.getToken());
				
				documentoDTO.setnomeDocumento(String.valueOf(aluno.getCPFAluno()+evento.getNomeEvento() ));
				byte[] doc = new byte[(int) (file).length()];
				java.io.DataInputStream is = new java.io.DataInputStream(new FileInputStream(file));
				is.readFully(doc);
				is.close();
				
				documentoDTO.setCpf(aluno.getCPFAluno());
				documentoDTO.setEvento(evento.getNomeEvento());
				documentoDTO.setData(evento.getDataFinal());
				documentoDTO.setDocumento(doc);
				documentoDTO.setToken(t.getToken());
				
				int chave = documentoDB.inserir(documentoDTO);
			}
		} catch (PersistenciaException | IOException | DocumentException e) {
			e.printStackTrace();
		}

	}
	
	private File createPdf(int idProjeto, int idEvento, int idBolsista, int diretorUm, int diretorDois, int idOrientador, int idCoorientador, String tokenCert)
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
		AlunoDTO aluno = buscarAlunoPoID(idBolsista);
		nome = aluno.getNomeAluno();
		dataFim = evento.getDataFinal();
		dataInicio = evento.getDataInicio();
		cidade = evento.getCidadeEvento();
		estado = evento.getEstadoEvento();
		cpfaluno = aluno.getCPFAluno();
		nomeprograma = evento.getNomePrograma();
		
		ProjetoDTO projetoDTO = projetoDB.buscarProjetoPoID(idProjeto);
		projeto = projetoDTO.getTituloProjeto();
		
		OrientadorDTO orientadorDTO = orientadorDB.buscarOrientadorPoID(idOrientador);
		orientador = orientadorDTO.getNomeOrientador();
		
		if(idCoorientador != 0){
			CoorientadorDTO coorientadorDTO = coorientadorDB.buscarCoorientadorPoID(idCoorientador);
			coorientador = coorientadorDTO.getNomeCoorientador();
		}
		
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
		String nomeCaminho = aluno.getCPFAluno()+evento.getNomeEvento()+".pdf";
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

		String pUm = "A Fundação de Amparo à Pesquisa do Estado do Acre - FAPAC certifica que ";
		String pDois = ", portador (a) do CPF ";
		String pTres = ",  foi bolsista de ";
		String pQuatro = " no Programa ";
		String pCinco = ", participando do projeto intitulado “";
		String pSeis = "” , sob a orientação do(a) Professor(a) ";
		String pSete = " (Coorientação: ";
		String pOito = "), no período de ";
		String pNove = ".";
		String pDez = ", no período de ";
		String pOnze = " a ";

		
		Paragraph p = new Paragraph();
		if(idCoorientador == 0){
			p = new Paragraph(pUm + nome + pDois + cpfaluno + pTres + nomeprograma +pQuatro+ patrocinadores+ pCinco + projeto +
					pSeis + orientador + pDez + dataInicio + pOnze + dataFim + pNove, new Font(bf, 17));
		}else{
			p = new Paragraph(pUm + nome + pDois + cpfaluno + pTres + nomeprograma +pQuatro+ patrocinadores+ pCinco + projeto +
					pSeis + orientador + pSete +coorientador + pOito + dataInicio + pOnze + dataFim + pNove, new Font(bf, 17));
		}

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
				cidade +" - "+ estado + ", " + data[0] + " de " + mesAno + " de " + data[2] + ".", new Font(bf, 16));
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
	
	@SuppressWarnings("unchecked")
	public List lerPlanila(File file) throws  IOException, PersistenciaException{
		List ids = new ArrayList();
		int id = 0;
		  FileInputStream fisPlanilha = null;
		  List linha = new ArrayList();
	    
	        	// caminho completo do arquivo
	            fisPlanilha = new FileInputStream(file);

	            //cria um workbook = planilha toda com todas as abas
	            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

	            //recuperamos apenas a primeira aba ou primeira planilha
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //retorna todas as linhas da planilha 0 (aba 1)
	            Iterator<Row> rowIterator = sheet.iterator();
	             rowIterator.next();
	            //varre todas as linhas da planilha 0
	            while (rowIterator.hasNext()) {
	            	
	            	
	                //recebe cada linha da planilha
	                Row row = rowIterator.next();
	                

	                //pegamos todas as celulas desta linha
	                Iterator<Cell> cellIterator = row.iterator();

	                //varremos todas as celulas da linha atual
	                AlunoDTO alunoDTO = new AlunoDTO();
	                while (cellIterator.hasNext()) {
	                	 alunoDTO = new AlunoDTO();
	                    //criamos uma celula
	                    Cell cell = cellIterator.next();
	                   
	                    
	                    
	                    switch (cell.getCellType()) {

	                        case Cell.CELL_TYPE_STRING:
	                            System.out.println("TIPO STRING: " + cell.getStringCellValue());
	                            linha.add(cell.getStringCellValue());
	                            break;

	                        case Cell.CELL_TYPE_NUMERIC:
	                            System.out.println("TIPO NUMERICO: " + cell.getNumericCellValue());
	                            linha.add(cell.getNumericCellValue());
	                            break;
	                            
	                        case Cell.CELL_TYPE_FORMULA:
	                            System.out.println("TIPO FORMULA: " + cell.getCellFormula());
	                    }
	                    
	      
	                   
	                }
	                if(!linha.isEmpty()){
	         	                	
	                alunoDTO.setNomeAluno(linha.get(0).toString());
                    alunoDTO.setCPFAluno(linha.get(1).toString());
                    
                    String a = alunoDTO.getCPFAluno();
    				String g = a.replace(".", "");
    				String [] t = g.split("");
    				String senha = t[0]+t[1]+t[2]+t[3]+t[4];
    				System.out.println(senha);
    				alunoDTO.setSenhaAluno(senha);
    				
    				id = inserir(alunoDTO);
    				ids.add(id);
    									
	                }
                 
                    linha.clear();
                 
                  
          
	            }
	            fisPlanilha.close();
	            return ids;
	    }
	
	public void bolsistaEventoProjeto(List ids, int idEvento, int idProjeto){
		EventoAlunoDTO bolsistaEvento = new EventoAlunoDTO();
		ProjetoAlunoDTO bolsistaProjeto = new ProjetoAlunoDTO();
		for(int j = 0; j <= ids.size(); j++){
			bolsistaEvento.setIdAluno(Integer.parseInt(ids.get(j).toString()));
			bolsistaEvento.setIdEvento(idEvento);
						
			bolsistaProjeto.setIdAluno(Integer.parseInt(ids.get(j).toString()));
			bolsistaProjeto.setIdProjeto(idProjeto);
			try {
				projetoDB.inserirProjetoAluno(bolsistaProjeto);
				eventoDB.inserirEventoAluno(bolsistaEvento);
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
