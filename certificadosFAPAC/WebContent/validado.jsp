<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="eventoDB" class="br.acre.fapac.certificado.dao.EventoDAO" scope="page"></jsp:useBean>
<jsp:useBean id="eventoLogic" class="br.acre.fapac.certificado.dto.EventoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="ajudanteLogic" class="br.acre.fapac.certificado.dto.AjudanteDTO" scope="page"></jsp:useBean>
<jsp:useBean id="ajudanteDB" class="br.acre.fapac.certificado.dao.AjudanteDAO" scope="page"></jsp:useBean>
<jsp:useBean id="coorientadorLogic" class="br.acre.fapac.certificado.dto.CoorientadorDTO" scope="page"></jsp:useBean>
<jsp:useBean id="coorientadorDB" class="br.acre.fapac.certificado.dao.CoorientadorDAO" scope="page"></jsp:useBean>
<jsp:useBean id="orientadorLogic" class="br.acre.fapac.certificado.dto.OrientadorDTO" scope="page"></jsp:useBean>
<jsp:useBean id="orientadorDB" class="br.acre.fapac.certificado.dao.OrientadorDAO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaLogic" class="br.acre.fapac.certificado.dto.AlunoDTO" scope="page"></jsp:useBean>
<jsp:useBean id="bolsistaDB" class="br.acre.fapac.certificado.dao.AlunoDAO" scope="page"></jsp:useBean>
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
	
	<%
	
	String aux = request.getParameter("j");
	String k [] = aux.split("");
	String cpf = "";
	int idEvento = 0;
	
	for(int i = 0; i <= 13; i++){
		cpf = cpf + k[i];
	}
	System.out.println(cpf);
	
	String id = "";
	for(int j = 14; j < k.length; j++){
		id = id + k[j];
	}
	System.out.println(id);
	
	eventoLogic = eventoDB.buscarEventoPoID(idEvento);
	String nome = "";
	idEvento = Integer.parseInt(id);
	
	ajudanteLogic = ajudanteDB.buscarAjudantePorCPF(cpf);
	if(ajudanteLogic == null){
		bolsistaLogic = bolsistaDB.buscarAlunoPorCPF(cpf);
		if(bolsistaLogic == null){
			orientadorLogic = orientadorDB.buscarOrientadorPorCPF(cpf);
			if(orientadorLogic == null){
				coorientadorLogic = coorientadorDB.buscarCoorientadorPorCPF(cpf);
				if(coorientadorLogic == null){
					%>
					<script type="text/javascript">
						alert("Erro ao buscar dados, contate o administrador.")
					</script>
					<%
				}else{
					nome = coorientadorLogic.getNomeCoorientador();
				}
				}else{
					nome = orientadorLogic.getNomeOrientador();
				}
			}else{
				nome = bolsistaLogic.getNomeAluno();
			}
		}else{
			nome = ajudanteLogic.getNomeAjudante();
		}
	
	eventoLogic = eventoDB.buscarEventoPoID(idEvento);
	
	
	%>

	<main>
	
	<form action="validado.jsp" method="post">
	<div align="justify" style="margin-top:150px">
		<label for="editalEvento"><span class="style1"><font color="#000000">&nbsp;Edital/Evento: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></span></label>
		<input type="text" name="editalEvento" size="50" disabled="disabled" value="<%=eventoLogic.getNomeEvento()%>">
		<br/><br/>
		<label for="programa" style="size:50"><span class="style1"><font color="#000000">&nbsp;Nome do programa: &nbsp;&nbsp;&nbsp;&nbsp;</font></span></label>
		<input type="text" name="programa" size="50" disabled="disabled" value="<%=eventoLogic.getNomePrograma()%>">
		<br/><br/>
		<label for="cHoraria" style="size:50"><span class="style1"><font color="#000000">&nbsp;Carga Horária: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></span></label>
		<input type="text" name="cHoraria" size="10" disabled="disabled" value="<%=eventoLogic.getCargaHoraria()%>">
		<br/><br/>
		<label for="nome" style="size:50"><span class="style1"><font color="#000000">&nbsp;Nome do participante:&nbsp;&nbsp;</font></span>
		</label>
		<input type="text" name="nome" size="50" disabled="disabled" value="<%=nome%>">
		<br/><br/>
		<label for="data" style="size:50"><span class="style1"><font color="#000000">&nbsp;Data do evento/edital: &nbsp;&nbsp;</font></span></label>
		<input type="text" name="codigo" size="10" disabled="disabled" value="<%=eventoLogic.getDataFinal()%>">
		<br/><br/>
		<br/><br/>
		
	</div>
	</form>
	</main>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/main-guide.js"></script> <!-- Resource jQuery -->
</body>
</html>