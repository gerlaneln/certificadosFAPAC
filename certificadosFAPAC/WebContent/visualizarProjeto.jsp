<%@page import="br.acre.fapac.certificado.dto.ProjetoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="administradorDB" class="br.acre.fapac.certificado.dao.AdministradorDAO" scope="session"></jsp:useBean>
<jsp:useBean id="administradorLogic" class="br.acre.fapac.certificado.dto.AdministradorDTO" scope="session"></jsp:useBean>
<jsp:useBean id="projetoDB" class="br.acre.fapac.certificado.dao.ProjetoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="projetoLogic" class="br.acre.fapac.certificado.dto.ProjetoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="projetoEvento" class="br.acre.fapac.certificado.dto.EventoProjetoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoDB" class="br.acre.fapac.certificado.dao.EventoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoLogic" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>
<html>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,700' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="./resources/css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="./resources/css/style.css"> <!-- Resource style -->
	<script src="./resources/modernizr.js"></script> <!-- Modernizr -->
  	
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
		<nav class="cd-side-nav" style="position: fixed;">
			<ul>
				<li class="cd-label"><font color="#ffffff">Menu</font></li>
				<li class="has-children">
					<a href="administrador.jsp">In�cio</a>				</li>
				<li class="has-children notifications">
					<a href="#0">Cadastrar</a>
					
					<ul>
						<li><a href="cadastroEvento.jsp">Evento/Edital</a></li>
						<li><a href="cadastroProjeto.jsp">Projeto</a></li>
						<li><a href="cadastroProfessorOrientador.jsp">Professor</a></li>
						<li><a href="cadastroDiretor.jsp">Diretor</a></li>
						<li><a href="cadastroBolsistas.jsp">Bolsistas</a></li>
						<li><a href="cadastroAjudantes.jsp">Ajudante</a></li>
						<li><a href="carregarImagens.jsp">Imagens</a></li>
						<li><a href="cadastroCertificado.jsp">Certificado</a></li>
					</ul>
			  </li>
				<li class="has-children notifications">
					<a href="#0">Visualizar</a>
					
					<ul>
						<li><a href="visualizarEvento.jsp">Eventos/Editais</a></li>
						<li><a href="visualizarProjeto.jsp">Projetos</a></li>
						<li><a href="visualizarProfessor.jsp">Professores</a></li>
						<li><a href="visualizarDiretor.jsp">Diretores</a></li>
						<li><a href="visualizarBolsistas.jsp">Bolsistas</a></li>
						<li><a href="visualizarAjudantes.jsp">Ajudantes</a></li>
						<li><a href="visualizarCertificado.jsp">Certificados</a></li>
					</ul>
			  </li>
			  <li class="has-children notifications">
					<a href="#0">Conta</a>
					
					<ul>
						<li><a href="redefinirSenha.jsp">Redefinir senha</a></li>
					</ul>
			  </li>
				<!--<li class="has-children comments">
					<a href="#0">Cadastrar</a>
					
					<ul>
						<li><a href="#0">Bolsistas</a></li>
						<li><a href="#0">Editais</a></li>
						<li><a href="#0">Eventos</a></li>
						<li><a href="#0">Professor Orientador</a></li>
						<li><a href="#0">Diretor</a></li>
					</ul>
			  </li>-->
			</ul>
			
			<div align="center" style="margin:390px 0 10px 5px">
				<span class="style1"><font color="FFFFFF">&copy;FAPAC 2016. Todos os direitos reservados.</font></span>
			</div>

			<!--
			<ul
				<li class="cd-label">Secondary</li>
				<li class="has-children bookmarks">
					<a href="#0">Bookmarks</a>
					
					<ul>
						<li><a href="#0">All Bookmarks</a></li>
						<li><a href="#0">Edit Bookmark</a></li>
						<li><a href="#0">Import Bookmark</a></li>
					</ul>
			  </li>
				<li class="has-children images">
					<a href="#0">Images</a>
					
					<ul>
						<li><a href="#0">All Images</a></li>
						<li><a href="#0">Edit Image</a></li>
					</ul>
			  </li>

				<li class="has-children users">
					<a href="#0">Users</a>
					
					<ul>
						<li><a href="#0">All Users</a></li>
						<li><a href="#0">Edit User</a></li>
						<li><a href="#0">Add User</a></li>
					</ul>
			  </li>
			</ul>
			

			<ul>
				<li class="cd-label">Action</li>
				<li class="action-btn"><a href="#0">+ Button</a></li>
			</ul>
			-->
			</nav>

		<div class="content-wrapper">
		<br/><br/><br/>
		<div id="projeto">
			<table class="table table-hover">
				<tr>
					<th width="1000">
					Projeto
					<a href="cadastroEvento.jsp"><input type="image" src="./resources/images/add-to-queue-button.png" width="20" height="20"></a>
					</th>
					<th width="300">
					Edital/Evento
					</th>
					<th>
					</th>
					<th>
					</th>
				</tr>
				<%
				List<ProjetoDTO>projetos = projetoDB.listarTodos();
				for(ProjetoDTO projeto : projetos){
					int idEvento = eventoDB.buscarEventoProjetosPoIDP(projeto.getIDProjeto());
					eventoLogic = eventoDB.buscarEventoPoID(idEvento);
					String nomeEvento = eventoLogic.getNomeEvento();
					%>
					<tr>
					<td width="1000">
					<label for="professor"><%=projeto.getTituloProjeto() %></label>
					</td>
					<td>
					<label for="editalEvento"><%= nomeEvento.toString()%></label>
					</td>
					<td>
					<a href="editarProjeto.jsp?n=<%=projeto.getIDProjeto()%>"><img src="./resources/images/editar.png" width="25" height="25"></a>
					</td>
					<td>
					<a href="deletarProjeto.jsp?n=<%=projeto.getIDProjeto()%>"><img src="./resources/images/excluir.png" width="25" height="25"></a>
					</td>
				</tr>
					<%
				}
				%>
			</table>
		<br/><br/><br/>
		</div>	
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>