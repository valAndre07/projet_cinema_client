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
<br>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12">
        <table class="display" id="table_id">
            <thead>
            <tr>
			     <th>Titre</th>
			     <th>Durée</th>
			     <th>Date de sortie</th>
			     <th>Budget</th>
			     <th>Recette</th>
			     <th>Realisateur</th>
			     <th>Categorie</th>
			     <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${mesFilms}" var="film" >
                <tr>
                    <td>${film.titre}</td>
                    <td>${film.duree}</td>
                    <td>${film.dateSortie}</td>
                    <td>${film.budget}</td>
                    <td>${film.montantRecette}</td>
                    <td>${film.realisateur.nom}</td>
                    <td>${film.categorie.libelleCat}</td>
                    <td style="text-align: center;">
                        <a href="/Controleur?action=editFilm&idFilm=${film.noFilm}"><span class="glyphicon glyphicon-pencil"></span></a>&nbsp;&nbsp;
                        <a href="/Controleur?action=deleteFilm&idFilm=${film.noFilm}" data-confirm="Etes vous sûr de vouloir supprimer cette oeuvre ? (Veuillez supprimer la réservation de cette oeuvre s'il y en a une avant de supprimer l'oeuvre)">
                            <span class="glyphicon glyphicon-trash"></span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>

    $(document).ready(function(){
    	$('#table_id').DataTable();
    });
</script>
</body>
</html>