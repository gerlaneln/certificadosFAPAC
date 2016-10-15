<%@page import="java.sql.SQLException" %>
<jsp:useBean id="administrador" class="br.acre.fapac.certificado.dao.AdministradorDAO" scope="session"></jsp:useBean>
<jsp:useBean id="administradorLogic" class="br.acre.fapac.certificado.dto.AdministradorDTO" scope="session"></jsp:useBean>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">

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
		<a href="#0" class="cd-nav-trigger">Menu<span></span></a>
	</header>
	
	<%
		
		if(request.getParameter("logar") != null){
	
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
	%>
		
	<%
		if(administradorLogic.validaAdministrador(login, senha)){
		
		
	%>
		<jsp:setProperty property="login" name="administradorLogic"/>
		<jsp:setProperty property="senha" name="administradorLogic"/>
		
		<jsp:forward page="administrador.jsp" />
	
	<%
		}
		}
	%>

	<main>
	<br/><br/><br/><br/><br/>
		<h3>Administrador, faça o seu login.</h3>
		<div align="center" style="margin-top:100px">
		
		<form action="loginAdm.jsp" method="post">
			<label for="login" style="size:50"><span class="style1"><font color="#000000">Login: </font></span></label>
			<input type="text" name="login" size="20">
			<br/>
			<br/>
        
		    <label for="senha" style="size:50">
			<span class="style1"><font color="#000000">Senha: </font></span></label>
			
			<input type="password" name="senha" size="20">
			<br/>
			<br/><br/>
			     
			<button class="btn btn-outline" type="submit" name ="logar"><font color="#000000">Logar</font></button>
			<br/><br/>
			
		</form>
			
      </div>
	
	</main>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/main-guide.js"></script> <!-- Resource jQuery -->
</body>
</html>