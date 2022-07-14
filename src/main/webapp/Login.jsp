<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body bgcolor="green">
 <div align="center">
  <h1>Inserisci dati di accesso</h1>
  <form action="<%=request.getContextPath()%>/servletLogin" method="post">
   <table style="with: 100%">
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>
   </table>
	 <input type="submit" value="Submit" />

  </form>
 </div>
 <% 
 	String parametro = (String)request.getAttribute("errore");
 	if (null != parametro){
 %>
<h1 style="color: red">
  I DATI DI ACCESSO ALLA RUBRICA SONO SBAGLIATI
</h1>
 
  <%
  }
  %>
  
  
  <%
  
	  
	  
  
  %>
	
</body>
</html>