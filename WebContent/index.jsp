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

<div id="my_carousel" class="carousel slide" data-ride="carousel">
<!-- Bulles -->
<ol class="carousel-indicators">
<li data-target="#my_carousel" data-slide-to="0" class="active"></li>
<li data-target="#my_carousel" data-slide-to="1"></li>
<li data-target="#my_carousel" data-slide-to="2"></li>
<li data-target="#my_carousel" data-slide-to="3"></li>
</ol>
<!-- Slides -->
<div class="carousel-inner">
<!-- Page 1 -->
<div class="item active">  
<div class="carousel-page">
<img src="img/inception.jpg" class="img-responsive" style="margin:0px auto;max-height:100%;" />
</div> 
</div>   
<!-- Page 2 -->
<div class="item"> 
<div class="carousel-page"><img src="img/batman.jpg" class="img-responsive img-rounded" 
style="margin:0px auto;max-height:100%;"  /></div> 
</div>  
<!-- Page 3 -->
<div class="item"> 
<div class="carousel-page"><img src="img/titanic.jpg" class="img-responsive img-rounded" 
style="margin:0px auto;max-height:100%;"  /></div> 
</div> 
<!-- Page 4 -->
<div class="item">  
<div class="carousel-page">
<img src="img/avatar.jpg" class="img-responsive img-rounded" 
style="margin:0px auto;max-height:100%;"  />
</div>  
</div>     
</div>
<a class="left carousel-control" href="#my_carousel" data-slide="prev">
</a>
<a class="right carousel-control" href="#my_carousel" data-slide="next">
</a>
</div>
    
</body>
</html>