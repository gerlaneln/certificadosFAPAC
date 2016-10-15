<%@page import="br.acre.fapac.certificado.dao.AjudanteDAO"%>
<%@page import="br.acre.fapac.certificado.dto.EventoCoorientadorDTO"%>
<%@page import="br.acre.fapac.certificado.dto.ProjetoCoorientadorDTO"%>
<%@page import="br.acre.fapac.certificado.dto.ProjetoOrientadorDTO"%>
<%@page import="br.acre.fapac.certificado.dto.ProjetoAlunoDTO"%>
<%@page import="br.acre.fapac.certificado.dto.EventoAlunoDTO"%>
<%@page import="br.acre.fapac.certificado.dao.EventoDAO"%>
<%@page import="java.util.Arrays"%>
<%@page import="br.acre.fapac.certificado.dto.EventoAjudanteDTO"%>
<%@page import="br.acre.fapac.certificado.dto.AjudanteDTO"%>
<%@page import="br.acre.fapac.certificado.dto.ProjetoDTO"%>
<%@page import="br.acre.fapac.certificado.dto.EventoDTO"%>
<%@page import="br.acre.fapac.certificado.dto.DiretorDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="administradorLogic" class="br.acre.fapac.certificado.dto.AdministradorDTO" scope="session"></jsp:useBean>
<jsp:useBean id="diretorDB" class="br.acre.fapac.certificado.dao.DiretorDAO" scope="page"></jsp:useBean>
<jsp:useBean id="diretorLogic" class="br.acre.fapac.certificado.dto.DiretorDTO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoDB" class="br.acre.fapac.certificado.dao.EventoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoLogic" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean> 
<jsp:useBean id="projetoDB" class="br.acre.fapac.certificado.dao.ProjetoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="projetoLogic" class="br.acre.fapac.certificado.dto.ProjetoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="ajudanteDB" class="br.acre.fapac.certificado.dao.AjudanteDAO" scope="page"></jsp:useBean>
<jsp:useBean id="ajudanteLogic" class="br.acre.fapac.certificado.dto.AjudanteDTO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaDB" class="br.acre.fapac.certificado.dao.AlunoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="orientadorDB" class="br.acre.fapac.certificado.dao.OrientadorDAO" scope="page"></jsp:useBean>
<jsp:useBean id="coorientadorDB" class="br.acre.fapac.certificado.dao.CoorientadorDAO" scope="page"></jsp:useBean>

<!DOCTYPE html>
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
						<img src="./resources/images/cd-avatar.png" alt="avatar"><font color="#000000"><%= administradorLogic.getLogin() %></font>
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
			  <li class="has-children notifications">
					<a href="#0">Conta</a>
					
					<ul>
						<li><a href="redefinirSenha.jsp">Redefinir senha</a></li>
					</ul>
			  </li>

			</ul>
			
			<div align="center" style="margin:370px 0 10px 5px">
				<span class="style1"><font color="#ffffff">&copy;FAPAC 2016. Todos os direitos reservados.</font></span>
			</div>
			</nav>
			
			<%
			if(request.getParameter("gerar")!=null){
				int idEvento = Integer.parseInt(request.getParameter("eventoEdital"));
				EventoDTO evento = eventoDB.buscarEventoPoID(idEvento);
								
				String [] diretores = request.getParameterValues("diretor");
				String diretor = Arrays.toString(diretores);
								
				List<EventoAjudanteDTO>ajudanteEvento = eventoDB.listarTodosEventoAjudante(evento.getIDEvento());

				if(ajudanteEvento != null){
					ajudanteDB.gerarCertificadoAjudante(evento.getIDEvento(), diretor);
				}
				
				int idProjeto = Integer.parseInt(request.getParameter("projeto"));
				ProjetoDTO projeto = projetoDB.buscarProjetoPoID(idProjeto);
				
				List<ProjetoAlunoDTO>projetoAluno = projetoDB.listarTodosProjetoAluno(idProjeto);
				
				List<ProjetoCoorientadorDTO>projetoCoorientador = projetoDB.listarTodosProjetoCoorientador(idProjeto);
				List<EventoCoorientadorDTO> eventoCoorientador = eventoDB.listarTodosEventoCoorientador(idEvento);
				int idCoorientador = 0;
				if(projetoCoorientador == null && eventoCoorientador == null){
					idCoorientador = 1;
				}else{
					idCoorientador = 0;
				}
				System.out.println(idCoorientador);
				
				if(projetoAluno != null){
					bolsistaDB.gerarCertificadoBolsista(idProjeto, idEvento, diretor, idCoorientador);
				}else{
					%>
					<script type="text/javascript">
						alert("O projeto selecionado não possui bolsitas cadastrados.")
					</script>
					<%
				}
				
				List<ProjetoOrientadorDTO> projetoOrientador = projetoDB.listarTodosProjetoOrientador(idProjeto);				
				if(projetoOrientador != null){
					orientadorDB.gerarCertificadoOrientador(idProjeto, idEvento, diretor);
				}

				
				if(projetoCoorientador != null){
					coorientadorDB.gerarCertificadoCoorientador(idProjeto, idEvento, diretor);
				}
				
			}
			%>

		<div class="content-wrapper">
		<h1>Cadastro de Certificado</h1>
		<form action="cadastroCertificado.jsp" method="post">
		<p>Selecione dois diretores.</p><br>
		<table class="table table-hover">
				<tr>
					<th>
					Selecione
					</th>
					<th>
					Diretores
					</th>
					<th>
					Decreto
					</th>
				</tr>
		<%
		List<DiretorDTO> diretores = diretorDB.listarTodos();
		for(DiretorDTO diretor : diretores){
			%>
			<tr>
					<td>
					<input type="checkbox" name="diretor" value="<%=diretor.getIDDiretor()%>">
					</td>
					<td>
					<label><%=diretor.getNomeDiretor() %></label>
					</td>
					<td>
					<label><%=diretor.getNDecreto() %></label>
					</td>
			</tr>
			<%
		}
		%>		
			</table>
			<br><br>
			<p>Selecione o evento ou edital do certificado.</p>
			<br>
			<table class="table table-hover">
				<tr>
					<th>
					Selecione
					</th>
					<th>
					Eventos/Editais
					</th>
					<th>
					Programa
					</th>
				</tr>
		<%
		List<EventoDTO> eventos = eventoDB.listarTodos();
		for(EventoDTO evento : eventos){
			%>
			<tr>
					<td>
					<input type="checkbox" name="eventoEdital" value="<%=evento.getIDEvento()%>">
					</td>
					<td>
					<label><%=evento.getNomeEvento() %></label>
					</td>
					<td>
					<label><%=evento.getNomePrograma() %></label>
					</td>
			</tr>
			<%
		}
		%>		
			</table>
			
			<br><br>
			<p>Selecione o projeto.</p><br>
			<table class="table table-hover">
				<tr>
					<th>
					Selecione
					</th>
					<th>
					Projetos
					</th>
					<th>
					Evento/Edital
					</th>
				</tr>
		<%
		List<ProjetoDTO> projetos = projetoDB.listarTodos();
		for(ProjetoDTO projeto : projetos){
			int idEvento = eventoDB.buscarEventoProjetosPoIDP(projeto.getIDProjeto());
			eventoLogic = eventoDB.buscarEventoPoID(idEvento);
			%>
			<tr>
					<td>
					<input type="checkbox" name="projeto" value="<%=projeto.getIDProjeto()%>">
					</td>
					<td>
					<label><%=projeto.getTituloProjeto() %></label>
					</td>
					<td>
					<label><%=eventoLogic.getNomeEvento() %></label>
					</td>
			</tr>
			<%
		}
		%>		
			</table>
		
		<button type="submit" class="btn btn-default" name="gerar">Gerar certificados</button>
		</form>	
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>