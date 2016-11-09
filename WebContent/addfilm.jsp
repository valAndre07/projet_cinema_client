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
<form action="Controleur?action=" method="get" style="text-align:center;">
  <div class="form-group">
    <input type="search" class="form-control" id="add_titre" placeholder="Titre">
    <input type="search" class="form-control" id="add_duree" placeholder="Durée">
    <input type="search" class="form-control" id="add_date" placeholder="Date sortie">
    <input type="search" class="form-control" id="add_budget" placeholder="Budget">
    <input type="search" class="form-control" id="add_recette" placeholder="Recette">
    <div class="form-group">
    <label for="exampleSelect1">Réalisateur</label>
    <select class="form-control" id="add_realisateur">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleSelect1">Catégorie</label>
    <select class="form-control" id="add_categorie">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
  </div>
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

</body>
</html>