<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>PolyCiné</title>

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap-responsive.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="css/custom-styles.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/component.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/datatables.css">


<script src="js/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        
</head>


<div class="header-wrapper">
   <div class="container">
       <div class="logo">
           <h1>PolyCiné</h1>
       </div>
   <div class="menu">
       <div class="navbar">
               <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                   <span class="glyphicon glyphicon-chevron-down"></span></i>
               </a>
               <div class="nav-collapse collapse">
                   <ul class="nav">
                       <li><a href="ControleurFilm?action=listerFilms">Films</a></li>
                       <li><a href="ControleurRealisateur?action=listerRealisateurs">Réalisateurs</a></li>
                       <li>
                           <div class="site-name">
                              <h1><a href="Controleur?action=index" style="color:white;">PolyCiné</a></h1>
                           </div>
                       </li>
                       <li><a href="ControleurCategorie?action=listerCategories">Catégories</a></li>
                       <li><a href="ControleurActeur?action=listerActeurs">Acteurs</a></li>
                   </ul>
               </div><!--/.nav-collapse -->
        </div>
    </div>
    </div>
</div>

<script src="js/jquery-1.9.1.js"></script> 

<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/masonry.pkgd.min.js"></script>
<script src="js/imagesloaded.js"></script>
<script src="js/classie.js"></script>
<script src="js/AnimOnScroll.js"></script>
<script type="text/javascript" src="js/datatables.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/convertDate.js"></script>
    
</body>
</html>