<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12" style="text-align:center;">
		<a href="Controleur?action=addFilmForm"><button type="submit" class="btn btn-primary btn-large">Ajouter un film</button></a>
	</div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12" id="dataTable">

        <table class="display" id="table_id">
            <thead>
            <tr>
			     <th>Titre</th>
			     <th>Durée</th>
			     <th>Date de sortie</th>
			     <th>Budget</th>
			     <th>Recette</th>
			     <th>Réalisateur</th>
			     <th>Catégorie</th>
			     <th>Actions</th>
            </tr>
            </thead>
            <tbody>
				<c:forEach var="film" items="${mesFilms}"  >
	                <tr>
	                    <td><a href="Controleur?action=infosFilm&idFilm=${film.noFilm}">${film.titre}</a></td>
	                    <td>${film.duree} min</td>
	                    <td>${film.dateSortie}</td>
	                    <td>${film.budget} €</td>
	                    <td>${film.montantRecette} €</td>
	                    <td>${film.realisateur.nomRealisateur}</td>
	                    <td>${film.categorie.libelleCat}</td>
	                    <td style="text-align: center;">
	                        <a href="Controleur?action=editFilm&idFilm=${film.noFilm}"><span class="glyphicon glyphicon-pencil"></span></a>&nbsp;&nbsp;
	                        <a href="Controleur?action=deleteFilm&idFilm=${film.noFilm}" data-confirm="Etes vous sûr de vouloir supprimer ce film ?">
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
    	$('#table_id').DataTable(
    			{
    	            "language": {
    	                "sProcessing":     "Traitement en cours...",
    	                "sSearch":         "Rechercher&nbsp;:",
    	                "sLengthMenu":     "Afficher _MENU_ &eacute;l&eacute;ments",
    	                "sInfo":           "Affichage de l'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
    	                "sInfoEmpty":      "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ment",
    	                "sInfoFiltered":   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
    	                "sInfoPostFix":    "",
    	                "sLoadingRecords": "Chargement en cours...",
    	                "sZeroRecords":    "Aucun &eacute;l&eacute;ment &agrave; afficher",
    	                "sEmptyTable":     "Aucune donn&eacute;e disponible dans le tableau",
    	                "oPaginate": {
    	                    "sFirst":      "Premier",
    	                    "sPrevious":   "Pr&eacute;c&eacute;dent",
    	                    "sNext":       "Suivant",
    	                    "sLast":       "Dernier"
    	                }
    	            }
    	        }
    	);
    });
</script>
</body>
</html>