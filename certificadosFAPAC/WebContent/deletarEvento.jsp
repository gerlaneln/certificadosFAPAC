<%@page import="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="administradorLogic" class="br.acre.fapac.certificado.dto.AdministradorDTO" scope="session"></jsp:useBean>
<jsp:useBean id="eventoDB" class="br.acre.fapac.certificado.dao.EventoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoLogic" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="evento" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>
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
					<a href="administrador.jsp">Início</a>				</li>
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
			if(request.getParameter("n")!=null){
				
				evento = eventoDB.buscarEventoPoID(Integer.parseInt(request.getParameter("n")));
			}
			if(request.getParameter("cancelar") != null){
				response.sendRedirect("visualizarEvento.jsp");
			}
			if(request.getParameter("deletar")!=null){
                try{
                	
                	evento = eventoDB.buscarEventoPoID(Integer.parseInt(request.getParameter("id").toString().trim()));
                	eventoDB.deletar(evento.getIDEvento());
    			//	response.sendRedirect("visualizarEvento.jsp");
    				
    				%>
    				<script type="text/javascript">
    					alert("Evento removido com sucesso")
    					<%
        				response.sendRedirect("visualizarEvento.jsp");
    					%>
    				</script>
    				<%
    				
                }catch(NullPointerException npe){
                	npe.getMessage();             
                }catch(Exception e){
                	  if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException){
                			%>
            				<script type="text/javascript">
            					alert("Falha ao remover evento. Ele encontra-se ligado a projetos/alunos/professores!")
            				</script>
            				<%
                	  }
                 }
				}
				
				%>
				
			}
			
			%>

		<div class="content-wrapper">
		<form action="deletarEvento.jsp" accept-charset="iso-8859-1,utf-8" method="post">
		<h1>Deletar evento/edital: <%=evento.getNomeEvento()%> </h1>
		<element hidden><input type="text" name="id" value="<%=evento.getIDEvento()%> "></element>
		<label for="nome">Nome: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="75" name="nomeEvento" value="<%=evento.getNomeEvento()%>" disabled>
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
			<input type="checkbox" name="fapac" value="FAPAC" checked onclick="return false;" readonly>FAPAC&nbsp;&nbsp;&nbsp;&nbsp;
        <%
			}else{
				if(div[i].equalsIgnoreCase("CAPES")){
		%>	
				<input type="checkbox" name="capes" value="CAPES" checked onclick="return false;" readonly>CAPES&nbsp;&nbsp;&nbsp;&nbsp;
			<%	
				}else{
					if(div[i].equalsIgnoreCase("CNPQ")){
			%>	
					<input type="checkbox" name="cnpq" value="CNPQ" checked onclick="return false;" readonly>CNPQ&nbsp;&nbsp;&nbsp;&nbsp;
				<%
					}else{
				%>			
					<input type="checkbox" name="icj"  value="ICJ" checked onclick="return false;" readonly>ICJ
					<%			
				}
			}
		}
	}
		}catch(NullPointerException npe){
			npe.getMessage();
		}
		%>
				
		<br/><br/>		
		<label for="cHoraria">Carga horária: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="4" name="cHoraria" value="<%=evento.getCargaHoraria()%>" disabled> horas
		<br/><br/>
		
		<label for="programa">Nome do programa: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="63" name="programa" value="<%=evento.getNomePrograma()%>" disabled>
		<br/><br/>
		
		<label for="cidade">Cidade: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="40" name="cidade" value ="<%=evento.getCidadeEvento()%>" disabled>&nbsp;&nbsp;
		<br/><br/>
		
		<label for="estado">Estado: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="20" name="estado" value ="<%=evento.getEstadoEvento()%>" disabled>
		<br/><br/>
		
		<label for="dInicio">Data de início: </label>
		<input name="dataInicio" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10" value ="<%=evento.getDataInicio()%>" disabled/>&nbsp;&nbsp;&nbsp;
		<label for="dInicio">Data de finalização: </label>
		<input name="dataFim" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10" value ="<%=evento.getDataFinal()%>" disabled/>
		
		<br/><br/><br/>
		<button type="submit" class="btn btn-default" name="deletar">Deletar</button>
		<button type="submit" class="btn btn-default" name="cancelar" onclick="visualizarEvento.jsp">Cancelar</button>	
		</form>
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>