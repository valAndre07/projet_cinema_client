<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</table>
	<th>
		<td>noFilm</td>
	     <td>Titre</td>
	     <td>Budget</td>
	     <td>Duree</td>
     </th>
<c:forEach  items="${mesFilms}"  var="film" >
	
  <tr>
     <td>${film.nofilm}</td>
     <td>${film.titre}</td>
     <td>${film.budget}</td>
     <td>${film.duree}</td>
  </tr>
  
 </c:forEach>
 </table>
 
 
</body>
</html>