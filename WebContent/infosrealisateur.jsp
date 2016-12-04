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

<h1 style="color:white; text-align:center;">Liste des films réalisés par ${realisateur.nomRealisateur} ${realisateur.prenomRealisateur} :</h1>
<br>
<div class="container">
	<div class="row">
	    <div class="col-xs-12 col-sm-12 col-md-12">
	    	<c:forEach var="film" items="${filmsRealisateur}">
	                <h2 style="text-align:center;">- ${film.titre} (${film.categorie.libelleCat})</h2>
	       </c:forEach>
		</div>
	</div>
</div>
</body>
</html>