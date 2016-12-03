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

<h1 style="color:white; text-align:center;">Edition d'un réalisateur</h1>
<br>
<form id="formRealisateur" action="ControleurRealisateur?action=editRealisateur" method="post" style="text-align:center;" onsubmit="return valider()">
  <div class="form-group">
    <input type="hidden" class="form-control" name="edit_id" value="${ realisateur.noRea }" placeholder="id" required="true">
    <input type="search" class="form-control" name="edit_nom" value="${ realisateur.nomRea }" placeholder="Nom" required="true">
    <input type="search" class="form-control" name="edit_prenom" value="${ realisateur.prenRea }" placeholder="Prenom" required="true">
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Modifier</button>
</form>

<script type="text/javascript">
        function valider() {
        	if ($('#formRealisateur input[name=edit_nom]').val() != '' && $('#formRealisateur input[name=edit_prenom]').val() != '')
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