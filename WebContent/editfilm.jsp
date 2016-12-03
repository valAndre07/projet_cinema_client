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

<h1 style="color:white; text-align:center;">Edition du film</h1>
<br>
<form id="formFilm" action="ControleurFilm?action=editFilm" onsubmit="return valider()" method="post" style="text-align:center;">
  <div class="form-group">
    <input type="hidden" class="form-control" name="edit_id" placeholder="id" value="${film.noFilm}" required="true">
    <input type="search" class="form-control" name="edit_titre" placeholder="Titre" value="${film.titre}" required="true">
    <input type="search" class="form-control" name="edit_duree" placeholder="Durée en minutes" value="${film.duree}" required="true">
    <input type='search' class="form-control" name="edit_date" id='datetimepicker' placeholder="Date de sortie" value="${film.dateSortie}" required="true">
    <input type="search" class="form-control" name="edit_budget" placeholder="Budget en €" value="${film.budget}" required="true">
    <input type="search" class="form-control" name="edit_recette" placeholder="Recette en €" value="${film.montantRecette}" required="true">
    <div class="form-group">
    <label for="exampleSelect1">Réalisateur</label>
    <select class="form-control" name="edit_realisateur">
	    <c:forEach items="${mesRealisateurs}" var="realisateur" >
	    	<option value="${realisateur.noRealisateur}">${realisateur.prenomRealisateur} ${realisateur.nomRealisateur}</option>
	    </c:forEach>
    </select>
  <br><br>
  </div>
  <div class="form-group">
    <label for="exampleSelect1">Catégorie</label>
    <select class="form-control" name="edit_categorie">
      <c:forEach items="${mesCategories}" var="categorie" >
	    	<option value="${categorie.codeCat}">${categorie.libelleCat}</option>
	    </c:forEach>
    </select>
  </div>
	   
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Ajouter</button>
</form>


<script type="text/javascript">
        
        $(document).ready(function() {
        	$('#datetimepicker').datepicker({
                startView: 2
            });
        });
        
        function valider() {
        	if ($('#formFilm input[name=edit_titre]').val() != '' && $('#formFilm input[name=edit_duree]').val() != '' && 
        			$('#formFilm input[name=date]').val() != '' && $('#formFilm input[name=edit_budget]').val() != '' && 
        			$('#formFilm input[name=edit_recette]').val() != '' && $.isNumeric($('#formFilm input[name=edit_recette]').val())
        			&& $.isNumeric($('#formFilm input[name=edit_duree]').val()) && $.isNumeric($('#formFilm input[name=edit_budget]').val()))
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