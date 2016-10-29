<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="css/custom-styles.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/component.css">
<link rel="stylesheet" href="css/font-awesome-ie7.css">

</head>
<body style="background: url(img/banner-bg.jpg) repeat;">

<div class="header-wrapper">
   <div class="container">
       <div class="logo">
           <h1>PolyCiné</h1>
       </div>
   <div class="menu">
       <div class="navbar">
               <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                   <i class="fw-icon-th-list">▼</i>
               </a>
               <div class="nav-collapse collapse">
                   <ul class="nav">
                       <li><a href="Controleur?action=listerfilms">Films</a></li>
                       <li><a href="Controleur?action=listerrealisateurs">Réalisateurs</a></li>
                       <li>
                           <div class="site-name">
                              <h1><a href="Controleur?action=index" style="color:white;">PolyCiné</a></h1>
                           </div>
                       </li>
                       <li><a href="Controleur?action=listercategories">Catégories</a></li>
                       <li><a href="Controleur?action=listeracteurs">Catégories</a></li>
                   </ul>
               </div><!--/.nav-collapse -->
        </div>
    </div>
    </div>
</div>
        
<br><br><br><br>
</table>
	<th>
		<td>noFilm</td>
	     <td>Titre</td>
	     <td>Budget</td>
	     <td>Duree</td>
	     <td>dateSortie</td>
	     <td>budget</td>
	     <td>montantRecette</td>
	     <td>noRea</td>
	     <td>codeCat</td>
     </th>
<c:forEach  items="${mesFilms}"  var="film" >
	
  <tr>
     <td>${film.nofilm}</td>
     <td>${film.titre}</td>
     <td>${film.budget}</td>
     <td>${film.duree}</td>
     <td>${film.dateSortie}</td>
     <td>${film.budget}</td>
     <td>${film.montantRecette}</td>
     <td>${film.noRea}</td>
     <td>${film.codeCat}</td>
  </tr>
  
 </c:forEach>
 </table>
 
 
</body>
</html>