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
		<h1>Cadastro de edital</h1>
		<label for="nome">Nome: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="75" name="nomeEdital">
		<br/><br/>
		
		<label for="patrocinadores">Patrocinadores: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="checkbox" name="fapac">FAPAC&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="capes">CAPES&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="cnpq">CNPQ&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="icj">ICJ
		<br/><br/>
		
		<label for="cHoraria">Carga horária: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="4" name="cHoraria"> horas
		<br/><br/>
		
		<label for="programa">Nome do programa: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="63" name="programa">
		<br/><br/>
		
		<label for="cidade">Cidade: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="40" name="cidade">&nbsp;&nbsp;
		<label for="estado">Estado: &nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input type="text" size="20" name="estado">
		<br/><br/>
		
		<label for="dInicio">Data de início: </label>
		<input name="dataInicio" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10"/>&nbsp;&nbsp;&nbsp;
		<label for="dInicio">Data de finalização: </label>
		<input name="dataFim" id="data"  OnKeyPress="formatar(this, '##/##/####')" maxlength="10"/>
		
		<br/><br/><br/>
		<button type="submit" class="btn btn-default">Salvar</button>
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.menu-aim.js"></script>
<script src="js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>