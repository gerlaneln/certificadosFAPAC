<%@page import="br.acre.fapac.certificado.dto.EventoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="documentoDB" class="br.acre.fapac.certificado.dao.DocumentoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="documentoLogic" class="br.acre.fapac.certificado.dto.DocumentoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoDB" class="br.acre.fapac.certificado.dao.EventoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoLogic" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>
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
	
	<script>
	function formatar(src, mask) {
		var i = src.value.length;
		var saida = mask.substring(0, 1);
		var texto = mask.substring(i)

		if (texto.substring(0, 1) != saida) {
			src.value += texto.substring(0, 1);
		}
	}
	</script>
	
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
		<a href="#0" class="cd-nav-trigger">Menu<span></span></a>
	</header>
	
	<%
	if(request.getParameter("buscar")!=null){
		eventoLogic = eventoDB.buscarEventoPoID(Integer.parseInt(request.getParameter("editalEvento").toString()));
		String evento = eventoLogic.getNomeEvento();
		System.out.println(evento);
		String cpf = request.getParameter("cpf");
		System.out.println(cpf);
		String token = request.getParameter("codigo");
		System.out.println(token);
		if(documentoDB.validaDocumento(evento, cpf, token)){
			response.sendRedirect("validado.jsp?j="+cpf+request.getParameter("editalEvento"));
		}else{
			%>
			<script type="text/javascript">
				alert("Dados não conferem.")
			</script>
			<%
		}
	}
	%>

	<main>
	<br/><br/><br/><br/><br/>
	<h3>Entre com as informações pedidas para verificar a validade do certificado.<br/><br/>O código de acesso se encontra na parte inferior do certificado</h3>
	<form action="validaCertificado.jsp" method="post">
	<div align="justify" style="margin-top:100px">
	<label for="editalEvento"><span class="style1"><font color="#000000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Edital/Evento: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></span></label>
	<select name="editalEvento" size="1" id="editalEvento">
		<option value="0" selected>Clique e selecione</option>
		<%
		List<EventoDTO> eventos = eventoDB.listarTodos();
		for(EventoDTO evento : eventos){
			%>
			<option value="<%=evento.getIDEvento()%>"><%=evento.getNomeEvento()%></option>
			<%
		}
		%>
	</select>
	<br/><br/>
	
	<label for="cpf" style="size:50"><span class="style1"><font color="#000000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CPF do participante: &nbsp;&nbsp;&nbsp;&nbsp;</font></span>
	</label>
	<input type="text" name="cpf" size="20" OnKeyPress="formatar(this, '###.###.###-##')" maxlength="14">
	<br/><br/>
	<label for="codigo" style="size:50"><span class="style1"><font color="#000000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Código de acesso: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></span></label>
	<input type="text" name="codigo" size="24" OnKeyPress="formatar(this, '####.####.####.####.####')" maxlength="24">
    <br/>
    <br/>
		<div align="center">
    		<button class="btn btn-outline" type="submit" name="buscar"><font color="#000000">Buscar</font></button>
	  	</div>
    <br/>
    </form>
	</div>	
	</main>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/main-guide.js"></script> <!-- Resource jQuery -->
</body>
</html>