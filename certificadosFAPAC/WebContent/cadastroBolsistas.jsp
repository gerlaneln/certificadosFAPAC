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
	
	<script type="text/javascript">
	function Nova(){
	location.href="visualizarBolsistas.jsp"
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
			
			<%
			if(request.getParameter("cancelar") != null){
				response.sendRedirect("visualizarBolsistas.jsp");
			}
			if(request.getParameter("cadastrar")!=null){
				bolsistaLogic.setNomeAluno(request.getParameter("nomeBolsista"));
				bolsistaLogic.setCPFAluno(request.getParameter("cpfBolsista"));
				
				String a = request.getParameter("cpfBolsista").toString();
				String g = a.replace(".", "");
				String [] t = g.split("");
				String senha = t[0]+t[1]+t[2]+t[3]+t[4];
				bolsistaLogic.setSenhaAluno(senha);
				
				int chave = bolsistaDB.inserir(bolsistaLogic);
				
				int idEvento = Integer.parseInt(request.getParameter("editalEvento"));
				bolsistaEvento.setIdAluno(chave);
				bolsistaEvento.setIdEvento(idEvento);
				eventoDB.inserirEventoAluno(bolsistaEvento);
				
				int idProjeto = Integer.parseInt(request.getParameter("projeto"));
				bolsistaProjeto.setIdAluno(chave);
				bolsistaProjeto.setIdProjeto(idProjeto);
				projetoDB.inserirProjetoAluno(bolsistaProjeto);
				
				response.sendRedirect("visualizarBolsistas.jsp");

				%>
				<script type="text/javascript">
					alert("Bolsista cadastrado com sucesso.")
				</script>
				<%
			}
			%>

		<div class="content-wrapper">
		<h1>Cadastro de bolsistas</h1>
		<form action="cadastroBolsistas.jsp" method="post">
		<label for="editalEvento">Edital/Evento: </label>
		<select name="editalEvento" size="1" id="editalEvento" required="required">
		<%
		List<EventoDTO>eventos = eventoDB.listarTodos();
		for(EventoDTO evento : eventos){
			%>
			<option value="<%=evento.getIDEvento() %>"><%=evento.getNomeEvento() %></option>
			<%
		}
		%>
	  	</select>
		<br/><br/>
		<label for="projeto">Projeto: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<select name="projeto" size="1" id="projeto" required="required" >
		<%
		List<ProjetoDTO>projetos = projetoDB.listarTodos();
		for(ProjetoDTO projeto : projetos){
			%>
			<option value="<%=projeto.getIDProjeto()%>"><%=projeto.getTituloProjeto() %></option>
			<%
		}
		%>
		</select><br><br>
		<label for="nome">Nome: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" name="nomeBolsista" size="60" required="required" autofocus="autofocus">
		<br/>
		<br/>
		<label for="cpf">CPF: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" name="cpfBolsista" size="60" onkeypress="formatar(this, '###.###.###-##')" maxlength="14" required="required" autofocus="autofocus">
		<br/>
		<br/>
		<!-- <label for="importar">Importar planilha: </label>
		<br/><br/>
		<input name="planilha" type="file"><br/>
		<button name="carregar" class="btn btn-default">Carregar</button>
		<br/>
		<br/>
		<br/>
		<table class="table table-hover">
			<tr>
				<th>
				Nome
				</th>
				<th>
				CPF
				</th>
			</tr>
			<tr>
				<td>
				<label for="nome">Gerl�ne Lima do Nascimento</label>
				</td>
				<td>
				<label for="cpf">97648507234</label>
				</td>
			</tr>
		</table>-->
		<button type="submit" class="btn btn-default" name="cadastrar">Salvar</button>
		<button type="submit" class="btn btn-default" name="cancelar" onclick="Nova()">Cancelar</button>
		</form>
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>