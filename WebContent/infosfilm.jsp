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

<h1 style="color:white; text-align:center;">${film.titre}</h1>
<br>
<div class="container">
	<div class="row">
	    <div class="col-xs-6 col-sm-6 col-md-6">
	    	<p>Durée : ${film.duree} minutes</p>
	    	<p>Date de sortie : ${film.dateSortie}</p>
	    	<p>Budget : ${film.budget}</p>
	    	<p>Recette : ${film.montantRecette} €</p>
	    	<p>Réalisateur : ${film.realisateur.nomRealisateur} ${film.realisateur.prenomRealisateur}</p>
	    	<p>Catégorie : ${film.categorie.libelleCat}</p>
		</div>
		<div class="col-xs-6 col-sm-6 col-md-6">
			<h2>Acteurs - Personnages</h2>
				<c:forEach var="personnage" items="${mesPersonnages}"  >
					    <p>${personnage.acteur.nomActeur} ${personnage.nomPersonnage}</p>
            	</c:forEach>
		</div>
	</div>
</div>
</body>
</html>