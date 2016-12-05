<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PolyCiné</title>

</head>
<body style="background: url(img/banner-bg.jpg) repeat;">

<jsp:include page="header.jsp"/>
        
<br><br><br><br>

<h1 style="color:white; background-color:#f9b701; text-align:center;">${acteur.prenom} ${acteur.nom}</h1>
<br>
	<div class="row" style="background-color:#2d6e84;">
		<div class="span6">
			<h2 style="text-align:center;">Personnage</h2><br>
			<c:forEach var="personnage" items="${personnages}"  >
			    <p style="text-align:center;">${personnage.nomPersonnage} dans ${personnage.film.titre}</p>
           	</c:forEach>
		</div>
	</div>

</body>
</html>