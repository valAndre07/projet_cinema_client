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

<h1 style="color:white; text-align:center;">Ajout du film</h1>
<br>
<form action="Controleur?action=addFilm" method="post" style="text-align:center;">
  <div class="form-group">
    <input type="search" class="form-control" name="add_titre" placeholder="Titre">
    <input type="search" class="form-control" name="add_duree" placeholder="Durée">
    <input type="search" class="form-control" name="add_date" placeholder="Date sortie">
    <input type="search" class="form-control" name="add_budget" placeholder="Budget">
    <input type="search" class="form-control" name="add_recette" placeholder="Recette">
    <div class="form-group">
    <label for="exampleSelect1">Réalisateur</label>
    <select class="form-control" name="add_realisateur">
	    <c:forEach var="realisateur" items="${mesRealisateurs}"  >
	    	<option value="${realisateur.noRealisateur}">${realisateur.prenomRealisateur} ${realisateur.nomRealisateur}</option>
	    </c:forEach>
    </select>
  
  </div>
  <div class="form-group">
    <label for="exampleSelect1">Catégorie</label>
    <select class="form-control" name="add_categorie">
      <c:forEach var="categorie" items="${mesCategories}"  >
	    	<option value="${categorie.codeCat}">${categorie.libelleCat}</option>
	    </c:forEach>
    </select>
  </div>
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

</body>
</html>