<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="administradorLogic" class="br.acre.fapac.certificado.dto.AdministradorDTO" scope="session"></jsp:useBean>
<jsp:useBean id="eventoDB" class="br.acre.fapac.certificado.dao.EventoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoLogic" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="evento" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>

<html lang="pt-br">
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
function formatar(src, mask) 
{
  var i = src.value.length;
  var saida = mask.substring(0,1);
  var texto = mask.substring(i)
  
if (texto.substring(0,1) != saida) 
  {
    src.value += texto.substring(0,1);
  }
}
</script>

	<script type="text/javascript">
	function Nova(){
	location.href="visualizarEvento.jsp"
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
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("cancelar") != null){
				response.sendRedirect("visualizarEvento.jsp");
		}
		if(request.getParameter("n")!=null){
			
			evento = eventoDB.buscarEventoPoID(Integer.parseInt(request.getParameter("n")));
		}
		if(request.getParameter("salvar")!=null){
		try{
			
		
			evento = eventoDB.buscarEventoPoID(Integer.parseInt(request.getParameter("id")));

			
			eventoLogic.setIDEvento(evento.getIDEvento());
			eventoLogic.setNomeEvento(request.getParameter("nomeEvento"));
			eventoLogic.setCargaHoraria(request.getParameter("cHoraria"));
			eventoLogic.setNomePrograma(request.getParameter("programa"));
			eventoLogic.setCidadeEvento(request.getParameter("cidade"));
			eventoLogic.setEstadoEvento(request.getParameter("estado"));
			eventoLogic.setDataInicio(request.getParameter("dataInicio"));
			eventoLogic.setDataFinal(request.getParameter("dataFim"));
			
			String patro="", patro1="", patro2="", patro3="", patro4="";
			 
			if(request.getParameter("fapac")!=null){
				patro1 = Arrays.toString(request.getParameterValues("fapac")); 
			}
			if(request.getParameter("capes")!=null){
				patro2 = Arrays.toString(request.getParameterValues("capes")); 
			}
			if(request.getParameter("cnpq")!=null){
				patro3 = Arrays.toString(request.getParameterValues("cnpq")); 
			}
			if(request.getParameter("icj")!=null){
				patro4 = Arrays.toString(request.getParameterValues("icj")); 
			}
			
			if(patro1 != null){
				patro = patro + patro1 + ";";
			}
			if(patro2 != null){
				patro = patro + patro2 + ";";
			}
			if(patro3 != null){
				patro = patro + patro3 + ";";
			}
			if(patro4 != null){
				patro = patro + patro4;
			}
			eventoLogic.setPatrocinadores(patro);
			
			eventoDB.atualizar(eventoLogic);
			response.sendRedirect("visualizarEvento.jsp");
		}catch(NullPointerException npe){
			npe.getMessage();
		}

			%>
			<script type="text/javascript">
				alert("Evento atualizado com sucesso")
			</script>
			<%
		}
		%>
			
		<div class="content-wrapper">
		<form action="editarEvento.jsp" accept-charset="iso-8859-1,utf-8" method="post">
		<h1>Editar evento/edital: <%=evento.getNomeEvento()%> </h1>
		<element hidden><input type="text" name="id" value="<%=evento.getIDEvento()%>"></element>
		<label for="nome">Nome: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="75" name="nomeEvento" required="required" value="<%=evento.getNomeEvento()%>">
		<br/><br/>
				
		<label for="patrocinadores">Patrocinadores: &nbsp;&nbsp;&nbsp;&nbsp;</label>
	<% 
	try{
		String pat = evento.getPatrocinadores();
		String um = pat.replace(";", "");
		String dois = um.replace("[", "");
		String tres = dois.replace("]", "/");
		String[] div = tres.split("/");
		for(int i = 0; i < div.length; i++) {
			if(div[i].equalsIgnoreCase("FAPAC")){
	%>
			<input type="checkbox" name="fapac" value="FAPAC" checked>FAPAC&nbsp;&nbsp;&nbsp;&nbsp;
        <%
			}else{
				if(div[i].equalsIgnoreCase("CAPES")){
		%>	
				<input type="checkbox" name="capes" value="CAPES" checked>CAPES&nbsp;&nbsp;&nbsp;&nbsp;
			<%	
				}else{
					if(div[i].equalsIgnoreCase("CNPQ")){
			%>	
					<input type="checkbox" name="cnpq" value="CNPQ" checked>CNPQ&nbsp;&nbsp;&nbsp;&nbsp;
				<%
					}if(div[i].equalsIgnoreCase("ICJ")){
				%>			
					<input type="checkbox" name="icj"  value="ICJ" checked>ICJ
					<%			
				}
			}
		}
	}
					%>
					
				 	<%
					 	if(request.getParameter("fapac")== null){
						%>
						<input type="checkbox" name="fapac" value="FAPAC">FAPAC&nbsp;&nbsp;&nbsp;&nbsp;
						<%
						}
						if(request.getParameter("capes") == null){
						%>
							<input type="checkbox" name="capes" value="CAPES">CAPES&nbsp;&nbsp;&nbsp;&nbsp;
						<%
						}
						if(request.getParameter("cnpq") == null){
						%>
							<input type="checkbox" name="cnpq" value="CNPQ">CNPQ&nbsp;&nbsp;&nbsp;&nbsp;
						<%
						}
						if(request.getParameter("icj") == null){
						%>
							<input type="checkbox" name="icj" value="ICJ">ICJ&nbsp;&nbsp;&nbsp;&nbsp;
						<%						
						}
	}catch(NullPointerException npe){
		npe.getMessage();
	}
					  %>
				<br/><br/>
		
		<label for="cHoraria">Carga hor�ria: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="4" name="cHoraria" required="required" autofocus="autofocus" value="<%=evento.getCargaHoraria()%>"> horas
		<br/><br/>
		
		<label for="programa">Nome do programa: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="63" name="programa" required="required" autofocus="autofocus" value="<%=evento.getNomePrograma()%>">
		<br/><br/>
		
		<label for="cidade">Cidade: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="40" name="cidade" required="required" autofocus="autofocus" value ="<%=evento.getCidadeEvento()%>">&nbsp;&nbsp;
		<label for="estado">Estado: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="20" name="estado" required="required" autofocus="autofocus" value ="<%=evento.getEstadoEvento()%>">
		<br/><br/>
		
		<label for="dInicio">Data de in�cio: </label>
		<input name="dataInicio" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10" required="required" autofocus="autofocus" value ="<%=evento.getDataInicio()%>"/>&nbsp;&nbsp;&nbsp;
		<label for="dInicio">Data de finaliza��o: </label>
		<input name="dataFim" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10" required="required" autofocus="autofocus" value ="<%=evento.getDataFinal()%>"/>
		
		<br/><br/><br/>
		<button type="submit" class="btn btn-default" name="salvar">Salvar</button>
		<button type="submit" class="btn btn-default" name="cancelar" onclick="Nova()">Cancelar</button>	
		</form>
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>