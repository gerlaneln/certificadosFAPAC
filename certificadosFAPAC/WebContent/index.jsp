<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

<main>

    <div align="center" style="margin-top:150px">
    <a href="impressaoDeCertificados.jsp"><button type="submit" class="btn btn-outline" style="width:300px"><font color="#000000">Impressão de Certificados</font></button></a>
    <br/>
    <br/>
    
    <a href="validaCertificado.jsp"><button class="btn btn-outline" type="button" style="width:300px"><font color="#000000">Validação de Certificados</font></button></a>
	<br/>
	<br/>
	<br/>
	</div>
    	
	<div align="center">
		<br/><br/>
		<br/><br/>

		<a href="loginAdm.jsp"><img src="./resources/images/img.png" width="21" height="22"><br/><font color="#58585b" size="-1">&nbsp;Administrador&nbsp;&nbsp;&nbsp;&nbsp;</font></a>
	</div>

</main>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/main-guide.js"></script> <!-- Resource jQuery -->
</body>
</html>