<%@page import="br.acre.fapac.certificado.dto.ProjetoDTO"%>
<%@page import="br.acre.fapac.certificado.dto.EventoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="administradorLogic" class="br.acre.fapac.certificado.dto.AdministradorDTO" scope="session"></jsp:useBean>
<jsp:useBean id="bolsistaDB" class="br.acre.fapac.certificado.dao.AlunoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaLogic" class="br.acre.fapac.certificado.dto.AlunoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoDB" class="br.acre.fapac.certificado.dao.EventoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoLogic" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaEvento" class="br.acre.fapac.certificado.dto.EventoAlunoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="projetoDB" class="br.acre.fapac.certificado.dao.ProjetoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="projetoLogic" class="br.acre.fapac.certificado.dto.ProjetoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaProjeto" class="br.acre.fapac.certificado.dto.ProjetoAlunoDTO" scope="page"></jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="./resources/css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="./resources/css/style.css"> <!-- Resource style -->
	<script src="./resources/js/modernizr.js"></script> <!-- Modernizr -->
  	
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
	
	<title>Portal de Certificados | Administrador</title>
    <style type="text/css">
<!--
.style1 {font-size: xx-small}
-->
    </style>
</head>
<body>
<header class="cd-main-header">
		<a href="#0" class="cd-logo"><img src="./resources/images/logo-fapac.png" alt="Logo" align="top"></a>

		<a href="#0" class="cd-nav-trigger">Menu<span></span></a>
		<nav class="cd-nav">
			<ul class="cd-top-nav">
				<li class="has-children account">
					<a href="#0">
						<img src="./resources/images/cd-avatar.png" alt="avatar"><font color="#000000"><%=administradorLogic.getLogin() %></font>
					</a>
					<ul>
						<li><a href="#0">Sair</a></li>
					</ul>
				</li>
			</ul>
		</nav>
</header> <!-- .cd-main-header -->

	<main class="cd-main-content">
				<nav class="cd-side-nav">
			<ul>
				<li class="cd-label"><font color="#ffffff">Menu</font></li>
				<li class="has-children">
					<a href="administrador.jsp">Início</a>				</li>
				<li class="has-children notifications">
					<a href="#0">Visualizar</a>
					
					<ul>
						<li><a href="visualizarEdital.jsp">Editais</a></li>
						<li><a href="visualizarEvento.jsp">Eventos</a></li>
						<li><a href="visualizarCertificado.jsp">Certificados</a></li>
						<li><a href="visualizarProjeto.jsp">Projetos</a></li>
						<li><a href="visualizarProfessor.jsp">Professores</a></li>
						<li><a href="visualizarDiretor.jsp">Diretores</a></li>
						<li><a href="visualizarBolsistas.jsp">Bolsistas</a></li>
						<li><a href="visualizarAjudantes.jsp">Ajudantes</a></li>
					</ul>
			  </li>
				<li class="has-children notifications">
					<a href="#0">Cadastrar</a>
					
					<ul>
						<li><a href="cadastroEdital.jsp">Edital</a></li>
						<li><a href="cadastroEvento.jsp">Evento</a></li>
						<li><a href="cadastroProfessorOrientador.jsp">Professor</a></li>
						<li><a href="cadastroDiretor.jsp">Diretor</a></li>
						<li><a href="cadastroProjeto.jsp">Projeto</a></li>
						<li><a href="cadastroAjudantes.jsp">Ajudante</a></li>
						<li><a href="cadastroBolsistas.jsp">Bolsistas</a></li>
						<li><a href="cadastroCertificado.jsp">Certificado</a></li>
					</ul>
			  </li>
			  <li>
			  	<a href="">Novo Certificado</a>			  </li>

			</ul>
			
			<div align="center" style="margin:390px 0 10px 5px">
				<span class="style1"><font color="#000000">&copy;FAPAC 2016. Todos os direitos reservados.</font></span>			</div>
			</nav>
			

		<div class="content-wrapper">
		<h1>Cadastro de bolsistas</h1>
		<form action ="uploadFile"   method="post" enctype="multipart/form-data">
		<label for="editalEvento">Edital/Evento: </label>
		<select name="editalEvento" size="1" id="editalEvento">
		<%
		List<EventoDTO>eventos = eventoDB.listarTodos();
		for(EventoDTO evento : eventos){
			%>
			<option value="<%=evento.getIDEvento() %>" id="<%=evento.getIDEvento() %>"><%=evento.getNomeEvento() %></option>
			<%
		}
		%>
	  	</select>
		<br/><br/>
		<label for="projeto">Projeto: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<select name="projeto" size="1" id="projeto">
		<%
		List<ProjetoDTO>projetos = projetoDB.listarTodos();
		for(ProjetoDTO projeto : projetos){
			%>
			<option value="<%=projeto.getIDProjeto()%>" id="<%=projeto.getIDProjeto()%>"><%=projeto.getTituloProjeto() %></option>
			<%
		}
		%>
		</select><br><br>
		<label for="importar">Importar planilha: </label>
		<br/><br/>
		<input name="planilha" type="file"><br/>
		<button type="submit" name="carregar" class="btn btn-default">Carregar</button>
		<br/>
		<br/>
		<br/>
		
		</form>
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>