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

<h1 style="color:white; text-align:center;">Ajout d'une catégorie</h1>
<br>
<form action="Controleur?action=" method="post" style="text-align:center;">
  <div class="form-group">
    <input type="search" class="form-control" id="add_libelle" placeholder="Libelle">
  
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

</body>
</html>