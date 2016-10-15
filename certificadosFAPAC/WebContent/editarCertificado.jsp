<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Portal de Certficados | Administrador</title>
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
						<img src="./resources/images/cd-avatar.png" alt="avatar"><font color="#000000">Administrador</font>
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
			
			<div align="center" style="margin:390px 0 10px 5px">
				<span class="style1"><font color="#000000">&copy;FAPAC 2016. Todos os direitos reservados.</font></span>			</div>
			</nav>

		<div class="content-wrapper">
		<h1>Cadastro de certificado</h1>
		<label for="eventoEdital">Evento ou edital correspondente: </label>
			<select name="editalEvento" size="1" id="editalEvento">
					<option value="0" selected>Selecione</option>
					<option value="1">Edital 1234</option>
					<option value="2">Evento 12345</option>
					<option value="2">Edital 123456</option>
	  		</select>
		<br/><br/>
		<label for="diretores">Diretores: &nbsp;</label>
		<input type="checkbox"><label for="diretor1">Diretor 1&nbsp;&nbsp;</label>
		<input type="checkbox"><label for="diretor2">Diretor 2&nbsp;&nbsp;</label>
		<input type="checkbox"><label for="diretor3">Diretor 3</label>
		<br/><br/>
		<label for="projeto">Projeto: &nbsp;</label>
			<select name="projeto" size="1" id="projeto">
					<option value="0" selected>Selecione</option>
					<option value="1">Projeto 1</option>
					<option value="2">Projeto 2</option>
					<option value="2">Projeto 3</option>
	  		</select>
		<br/><br/>
		<label for="tipoBolsista">Tipo de bolsista: </label>
		<input type="tipoBolsista" size="60">
		<br/><br/>
		<label for="profOrientador">Professor orientador: &nbsp;</label>
			<select name="profOrientador" size="1" id="profOrientador">
					<option value="0" selected>Selecione</option>
					<option value="1">Professor 1</option>
					<option value="2">Professor 2</option>
					<option value="2">Professor 3</option>
	  		</select>
		<br/><br/>
		<label for="profCoorientador">Professor coorientador: &nbsp;</label>
			<select name="profCoorientador" size="1" id="profCoorientador">
					<option value="0" selected>Selecione</option>
					<option value="1">Professor 1</option>
					<option value="2">Professor 2</option>
					<option value="2">Professor 3</option>
	  		</select>
		<br/><br/>
		<label for="dInicio">Data de início:&nbsp;&nbsp;</label><input name="dataInicio" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10"/>&nbsp;&nbsp;
		<label for="dFim">Data de fim:&nbsp;&nbsp;</label><input name="dataFim" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10"/>
		<br/><br/>
		<label for="dEmissao">Data de emissão:&nbsp;&nbsp;</label><input name="dataEmissao" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10"/>
		<br/>
		<br/>
		<button type="submit" class="btn btn-default">Salvar</button>	
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>