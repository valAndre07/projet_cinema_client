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