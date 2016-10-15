<%@page import="java.awt.Desktop"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.acre.fapac.certificado.dao.DocumentoDAO"%>
<%@page import="br.acre.fapac.certificado.dto.DocumentoDTO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="usuarioDB" class="br.acre.fapac.certificado.dao.UsuarioDAO" scope="session"></jsp:useBean>
<jsp:useBean id="usuarioLogic" class="br.acre.fapac.certificado.dto.UsuarioDTO" scope="session"></jsp:useBean>
<jsp:useBean id="ajudanteDB" class="br.acre.fapac.certificado.dao.AjudanteDAO" scope="page"></jsp:useBean>
<jsp:useBean id="ajudanteLogic" class="br.acre.fapac.certificado.dto.AjudanteDTO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaDB" class="br.acre.fapac.certificado.dao.AlunoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaLogic" class="br.acre.fapac.certificado.dto.AlunoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="orietadorDB" class="br.acre.fapac.certificado.dao.OrientadorDAO" scope="page"></jsp:useBean>
<jsp:useBean id="orientadorLogic" class="br.acre.fapac.certificado.dto.OrientadorDTO" scope="page"></jsp:useBean>
<jsp:useBean id="coorientadorDB" class="br.acre.fapac.certificado.dao.CoorientadorDAO" scope="page"></jsp:useBean>
<jsp:useBean id="coorientadorLogic" class="br.acre.fapac.certificado.dto.CoorientadorDTO" scope="page"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700|Merriweather' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="./resources/css/reset-guide.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="./resources/css/style-guide.css"> <!-- Resource style -->
	<script src="./resources/js/modernizr-guide.js"></script> <!-- Modernizr -->
  	
	<title>Portal de Certificados da FAPAC</title>
    <style type="text/css">
<!--
.style4 {font-size: 25px}
.style5 {font-weight: bold}
-->
    </style>
</head>
<body>
<header>
		<div class="cd-logo"><img src="./resources/images/logo.png" alt="Logo" width="188" height="75"></div>

		<nav>
			<ul class="cd-main-nav">
				  <div align="right">
				  <li></li>
				  <p class="style4">Portal de Certificados</p>
				  </div>
			</ul> 
			<!-- cd-main-nav -->
	  </nav>
		
	</header>
	
	<%
	int g = -1;
	String cpf = request.getParameter("n");
	System.out.println(cpf);
	String nome = "";
	
	ajudanteLogic = ajudanteDB.buscarAjudantePorCPF(cpf);
	if(ajudanteLogic == null){
		bolsistaLogic = bolsistaDB.buscarAlunoPorCPF(cpf);
		if(bolsistaLogic == null){
			orientadorLogic = orietadorDB.buscarOrientadorPorCPF(cpf);
			if(orientadorLogic == null){
				coorientadorLogic = coorientadorDB.buscarCoorientadorPorCPF(cpf);
				if(coorientadorLogic == null){
					%>
					<script type="text/javascript">
						alert("Erro ao buscar dados, contate o administrador.")
					</script>
					<%
				}else{
					nome = coorientadorLogic.getNomeCoorientador();
				}
				}else{
					nome = orientadorLogic.getNomeOrientador();
				}
			}else{
				nome = bolsistaLogic.getNomeAluno();
			}
		}else{
			nome = ajudanteLogic.getNomeAjudante();
		}
	
	
	
	
	%>

	<main>
	<form action="certificados.jsp">
	<div align="center" style="margin-top:150px">
			<label for="nome"><span class="style1"><font color="#000000">Nome do participante: </font></span></label>
			  <input type="text" name="nome" size="50" disabled="disabled" value="<%=nome%>">
			  <br/>
			  <br/><br/><br/>
			<label for="certificado"><font color="#000000">Certificados: </font></label>
			  <br/><br/>
			  <table align="center" cellpadding="50" width="550">
			  <%
			  List<DocumentoDTO> documentos = new ArrayList<DocumentoDTO>();
			  DocumentoDAO documentoDB = new DocumentoDAO();
			  documentos = documentoDB.buscarDocumentoPorCPF(cpf);
			  //int i = 0;
			  for(int i = 0;i < documentos.size(); i ++){				  
				  %>
				  <tr>
			  		<td width="550">
			  			<label for="certificado"><span class="style1"><p><span></span><font color="#000000"><%=documentos.get(i).getEvento() %></font></p></label>
			  		</td>
			  		<td>
			  			<button type="button" onclick="<%=g=i%>"><img src="./resources/images/seta.png" width="20" height="20"></button>
			  		</td>
			  	</tr>
				  <%
				//  i = i+1;
			  }
			  %>
			  </table>
			<br/>
			<br/>
			<%
			
			if(g != -1){
				
				File file = null;
				file = documentoDB.converterPdf(documentos.get(g).getDocumento(), nome);
				Desktop.getDesktop().open(file);
				 
			}
			%>
	  </div>
	  </form>

	</main>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/main-guide.js"></script> <!-- Resource jQuery -->
</body>
</html>