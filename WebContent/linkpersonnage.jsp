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

<h1 style="color:white; text-align:center;">Créer un personnage</h1>
<br>
<form id="formPersonnage" action="Controleur?action=linkPersonnage" onsubmit="return valider()" method="post" style="text-align:center;">
  <div class="form-group">    
    <div class="form-group">
    <label for="exampleSelect1">Film</label>
    <select class="form-control" name="add_film">
	    <c:forEach items="${mesFilms}" var="film" >
	    	<option value="${film.noFilm}">${film.titre}</option>
	    </c:forEach>
    </select>
  <br><br>
  </div>
  <div class="form-group">
    <label for="exampleSelect1">Acteur</label>
    <select class="form-control" name="add_acteur">
      <c:forEach items="${mesActeurs}" var="acteur" >
	    	<option value="${acteur.noActeur}">${acteur.nomActeur} ${acteur.prenomActeur}</option>
	    </c:forEach>
    </select>
  </div>
  <br>
  <label for="exampleSelect1">Nom du personnage</label>
  <input type="search" class="form-control" name="link_personnage" placeholder="Nom du personnage" required="true">
  
	   
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Créer un personnage</button>
</form>


<script type="text/javascript">
        
        function valider() {
        	if ($('#formPersonnage input[name=link_personnage]').val() != '')
        	{
        		return true;
        	}
        	else
        	{
        		alert("Le nom du personnage n'est pas rempli correctement !");
        		return false;
        	}
            
        }
</script>
    
</body>
</html>