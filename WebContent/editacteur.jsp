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

<h1 style="color:white; text-align:center;">Edition d'un acteur</h1>
<br>
<form id="formActeur" action="ControleurActeur?action=editActeur" onsubmit="return valider()" method="post" style="text-align:center;">
  <div class="form-group">
    <input type="hidden" class="form-control" name="edit_id" placeholder="id" value="${acteur.noActeur}" required="true">
    <input type="search" class="form-control" value="${acteur.nomActeur}" name="edit_nom" placeholder="Nom" required="true">
    <input type="search" class="form-control" value="${acteur.prenomActeur}" name="edit_prenom" placeholder="Prénom" required="true">
    <input type="search" class="form-control" value="${acteur.dateNaissance}" id='datetimepickernaissance' name="edit_date_naissance" placeholder="Date naissance" required="true">
    <input type="search" class="form-control" value="${acteur.dateDeces}" id='datetimepickerdeces' name="edit_date_deces" placeholder="Date décès" required="false">
    
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Modifier</button>
</form>

<script type="text/javascript">
        
        $(document).ready(function() {
        	$('#datetimepickernaissance').datepicker({
                startView: 2
            });
        	$('#datetimepickerdeces').datepicker({
                startView: 2
            });
        	convertDateInput("#datetimepickernaissance");
        	convertDateInput("#datetimepickerdeces");
        });
        
        function valider() {
        	if ($('#formActeur input[name=edit_nom]').val() != '' && $('#formActeur input[name=edit_prenom]').val() != '' && 
        		$('#formActeur input[name=edit_date_naissance]').val() != '')
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