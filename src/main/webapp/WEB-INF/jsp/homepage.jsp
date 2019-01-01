<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section id="main-content">
<table style="border-collapse: collapse;">

<c:forEach var="parks" items="${parksList}">
<tr style ="border-bottom: 1px solid #ddd; ">
<td>

<c:url var="parkImageUrl" value="/img/parks/${fn:toLowerCase(parks.parkCode)}.jpg" /> 
<c:url var="parkDetailUrl" value="/parkDetail">
<c:param name="parkCode" value="${parks.parkCode}" /></c:url> 
<a href="${parkDetailUrl}"><img src="${parkImageUrl}" /></a>
</td>

<td style="vertical-align: top;">
<h2>${parks.name}, ${parks.state}</h2>
<br>
<div>${parks.description}</div>

</td>
</tr>
</c:forEach>
</table>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />