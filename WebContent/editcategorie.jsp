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

<h1 style="color:white; text-align:center;">Edition d'une catégorie</h1>
<br>
<form id="formCategorie" action="ControleurCategorie?action=editCategorie" onsubmit="return valider()" method="post" style="text-align:center;">
  <div class="form-group">
    <input type="search" class="form-control" name="edit_code" placeholder="Code" value="${categorie.codeCat}" required="true" readonly>
    <input type="search" class="form-control" name="edit_libelle" placeholder="Libelle" value="${categorie.libelleCat}" required="true">
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Modifier</button>
</form>

<script type="text/javascript">
        function valider() {
        	if ($('#formCategorie input[name=edit_libelle]').val() != '' && $('#formCategorie input[name=edit_code]').val() != '')
        	{
        		return true;
        	}
        	else
        	{
        		alert("Le champ n'est pas rempli correctement !");
        		return false;
        	}
            
        }
</script>
</body>
</html>