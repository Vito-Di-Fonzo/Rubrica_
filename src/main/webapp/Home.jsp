<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="model.*"%>
<head>
<meta charset="ISO-8859-1">
<title>Benvenuto</title>
</head>
<body bgcolor="aliceblue">
	<%
	List<User> listaUsers = (List<User>) request.getAttribute("listaUser");
	User utenteTrovato = (User) request.getAttribute("utenteLoggato");
	%>





	<h1>
		Benvenuto
		<%=utenteTrovato.getUsername()%>
		nella tua rubrica
	</h1>

	<form action="<%=request.getContextPath()%>/InsertServlet"
		method="post">

		<table style="with: 100%">


			<table border="1">
				<tr>
					<td>Nome</td>
					<td>Cognome</td>
					<td>Telefono</td>
					<td>Modifica</td>




					<%
					List<Contatto> listaContatti = (List<Contatto>) request.getAttribute("listaContatti");

					for (int i = 0; i < listaContatti.size(); i++) {

						Contatto contattoTrovato = listaContatti.get(i);
					%>
				
				<tr>

					<td><%=contattoTrovato.getNome()%></td>
					<td><%=contattoTrovato.getCognome()%></td>
					<td><%=contattoTrovato.getNumeroTelefono()%></td>
					<td><a
						href="<%=request.getContextPath()%>/DeleteServlet?id=<%=contattoTrovato.getId()%>&id_utente=<%=utenteTrovato.getId()%>"><input
							type="button" value="Delete" /></a> </tdi> <%
 }
 %>
			</table>

			<!-- inserimento contatti  -->


			<h2>Inserisci un altro contatto in rubrica</h2>

			<table style="with: 100%">
				<tr>
					<td>Nome</td>
					<td><input type="text" name="nome" /></td>
				</tr>
				<tr>
					<td>Cognome</td>
					<td><input type="text" name="cognome" /></td>
				</tr>
				<td>Telefono</td>
				<td><input type="text" name="telefono" /></td>
				</tr>
				<tr style="visibility: hidden">
					<td><input type="text" name="id_utente"
						value="<%=utenteTrovato.getId()%>" /></td>
				</tr>
			</table>
			<input type="submit" value="Aggiungi" />
			</form>
</body>
</html>