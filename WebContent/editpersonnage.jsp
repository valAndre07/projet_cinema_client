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

<h1 style="color:white; text-align:center;">Edition du Personnage </h1>
<br>
<form id="formFilm" action="ControleurFilm?action=editFilm" onsubmit="return valider()" method="post" style="text-align:center;">
    <input type="hidden" class="form-control" name="edit_id" placeholder="id" value="${personnage.film.noFilm}" required="true">
  
  	<input type="hidden" class="form-control" name="edit_acteur" placeholder="Nom acteur" value="${personnage.acteur.noActeur}" required="true">

	<input type="search" class="form-control" name="edit_nom" placeholder="Nom du personnage" value="${personnage.nomPers}" required="true">   
  <br>
  <button type="submit" class="btn btn-primary">Modifier</button>
</form>


<script type="text/javascript">
        
        
        function valider() {
        	if ($('#formFilm input[name=edit_film]').val() != '' && $('#formFilm input[name=edit_acteur]').val() != '' && 
        			$('#formFilm input[name=edit_nom]').val() != '')
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