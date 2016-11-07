<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PolyCiné</title>

<link rel="stylesheet" href="css/data_tables.css">

</head>
<body style="background: url(img/banner-bg.jpg) repeat;">

<jsp:include page="header.jsp"/>
        
<br><br><br><br>
<div class="row">
    <div class="col-xs-1 col-sm-1 col-md-1">
    </div>
    <div class="col-xs-10 col-sm-10 col-md-10">
        <table class="display hover stripe row-border" id="table_list">
            <thead>
            <tr>
			     <th>Titre</th>
			     <th>Durée</th>
			     <th>Date de sortie</th>
			     <th>Budget</th>
			     <th>Recette</th>
			     <th>noRea</th>
			     <th>codeCat</th>
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
                    <td>${film.noRea}</td>
                    <td>${film.codeCat}</td>
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
    <div class="col-xs-1 col-sm-1 col-md-1">
    </div>
</div>

<script src="js/data_tables.js"></script>

<script>

    $(document).ready(function(){
        $('#table_list').dataTable( {
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
        } );
    });
</script>
</body>
</html>