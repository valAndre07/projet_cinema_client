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

<h1 style="color:white; text-align:center;">Édition du Personnage </h1>
<br>

<form id="formPersonnage" action="ControleurPersonnage?action=editPersonnage" onsubmit="return valider()" method="post" style="text-align:center;">
  <div class="form-group">
    <input type="hidden" class="form-control" name="edit_id_film" placeholder="id" value="${personnage.film.noFilm}" required="true">
    <input type="hidden" class="form-control" name="edit_id_acteur" placeholder="id" value="${personnage.acteur.noActeur}" required="true">
    <input type="search" class="form-control" value="${personnage.film.titre}" name="edit_film" placeholder="Titre du film" required="true" disabled="disabled">
    <input type="search" class="form-control" value="${personnage.acteur.prenomActeur} ${personnage.acteur.nomActeur}" name="edit_acteur" placeholder="Acteur" required="true" disabled="disabled">
    <input type="search" class="form-control" value="${personnage.nomPersonnage}" name="edit_nom_personnage" placeholder="Nom du personnage" required="true">
    
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Modifier</button>
</form>
    
    <script>
    function valider() {
    	if ($('#formPersonnage input[name=edit_film]').val() != '' && $('#formPersonnage input[name=edit_acteur]').val() != '' && 
    		$('#formPersonnage input[name=edit_nom_personnage]').val() != '')
    	{
    		return true;
    	}
    	else
    	{
    		alert("Un des champs n'est pas rempli correctement !");
    		return false;
    	}
        
    }
    </script>
    
</body>
</html>