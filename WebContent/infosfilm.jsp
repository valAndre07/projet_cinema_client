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

<h1 style="color:white; background-color:#f9b701; text-align:center;">${film.titre}</h1>
<br>
	<div class="row" style="background-color:#2d6e84;">
	    <div class="span6">
	    	<h2 style="text-align:center;">Informations générales</h2><br>
	    	<p style="text-align:center;">Durée : ${film.duree} minutes</p>
	    	<p style="text-align:center;">Date de sortie : ${film.dateSortie}</p>
	    	<p style="text-align:center;">Budget : ${film.budget}</p>
	    	<p style="text-align:center;">Recette : ${film.montantRecette} €</p>
	    	<p style="text-align:center;">Réalisateur : ${film.realisateur.nomRealisateur} ${film.realisateur.prenomRealisateur}</p>
	    	<p style="text-align:center;">Catégorie : ${film.categorie.libelleCat}</p>
		</div>
		<div class="span6">
			<h2 style="text-align:center;">Acteurs - Personnages</h2><br>
			<c:forEach var="personnage" items="${mesPersonnages}"  >
			    <p style="text-align:center;">${personnage.acteur.nomActeur} - ${personnage.nomPersonnage}&nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="ControleurPersonnage?action="><span class="glyphicon glyphicon-pencil" style="color:white;"></span></a>&nbsp;&nbsp;
                <a href="ControleurPersonnage?action=deletePersonnage&idActeur=${personnage.acteur.noActeur}&idFilm=${personnage.film.noFilm}"><span class="glyphicon glyphicon-trash" style="color:white;"></span></a></p>
			    
           	</c:forEach>
		</div>
	</div>

</body>
</html>