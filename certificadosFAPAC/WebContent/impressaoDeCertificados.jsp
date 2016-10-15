<%@page import="br.acre.fapac.certificado.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="usuarioDB" class="br.acre.fapac.certificado.dao.UsuarioDAO" scope="session"></jsp:useBean>
<jsp:useBean id="usuarioLogic" class="br.acre.fapac.certificado.dto.UsuarioDTO" scope="session"></jsp:useBean>
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
.style9 {font-size: large}
.style10 {font-size: medium}
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
	int conta = 0;
	if(request.getParameter("entrar")!=null){
		System.out.println(request.getParameter("cpf"));
		System.out.println(request.getParameter("senha"));
		List<UsuarioDTO> usuarios = usuarioDB.listarLoginSenha();
	
		for(UsuarioDTO usuario : usuarios){
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getSenha());
			System.out.println(conta);
			if(usuario.getLogin().equalsIgnoreCase(request.getParameter("cpf"))){
				if(usuario.getSenha().equalsIgnoreCase(request.getParameter("senha"))){
					response.sendRedirect("certificados.jsp?n="+usuario.getLogin());
				}else{
					%>
					<script type="text/javascript">
						alert("Senha incorreta.")
					</script>
					<%
				}
			}else{
				conta++;
				System.out.println(conta);
			}
		}
		
		if(conta != 0){
			System.out.println(conta);
			%>
			<script type="text/javascript">
				alert("Usuário não encontrado.")
			</script>
			<%
		}
		
	}
	%>

	<main>
	<br/><br/><br/><br/><br/>
	<form action="impressaoDeCertificados.jsp" method="post">
	<h3 class="style10">Realize o login para visualizar e imprimir seus certificados.</h3>
	<div align="center" style="margin-top:100px">
		<label for="cpf" style="size:50"><span class="style1"><font color="#000000">CPF: &nbsp;&nbsp;</font></span></label>
		<input type="text" name="cpf" size="20" OnKeyPress="formatar(this, '###.###.###-##')" maxlength="14">
		<br/>
		<br/>
        
		<label for="senha" style="size:50">
		<span class="style1"><font color="#000000">Senha: </font></span>
		</label>
		<input type="password" name="senha" size="20" maxlength="5">
		<br/>
		<br/>
      	<br/>
		<button class="btn btn-outline" type="submit" name="entrar"><font color="#000000">Entrar</font></button>
		<br/>
   </form>
	  </div>
</main>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/main-guide.js"></script> <!-- Resource jQuery -->
</body>
</html>