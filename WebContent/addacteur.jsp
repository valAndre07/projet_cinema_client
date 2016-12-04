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

<h1 style="color:white; text-align:center;">Ajout d'un acteur</h1>
<br>
<form id="formActeur" action="ControleurActeur?action=addActeur" onsubmit="return valider()" method="post" style="text-align:center;">
  <div class="form-group">
    <input type="search" class="form-control" name="add_nom" placeholder="Nom" required="true">
    <input type="search" class="form-control" name="add_prenom" placeholder="Prénom" required="true">
    <input type="search" class="form-control" id='datetimepickernaissance' name="add_date_naissance" placeholder="Date naissance" required="true">
    <input type="search" class="form-control" id='datetimepickerdeces' name="add_date_deces" placeholder="Date décès" required="true">
    
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

<script type="text/javascript">
        
        $(document).ready(function() {
        	$('#datetimepickernaissance').datepicker({
                startView: 2
            });
        	$('#datetimepickerdeces').datepicker({
                startView: 2
            });
        });
        
        function valider() {
        	if ($('#formActeur input[name=add_nom]').val() != '' && $('#formActeur input[name=add_prenom]').val() != '' && 
        		$('#formActeur input[name=add_date_naissance]').val() != '')
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