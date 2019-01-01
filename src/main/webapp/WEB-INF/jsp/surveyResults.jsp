<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section id="main-content">
	<br>
	<h1 style="text-align:center; text-decoration: underline; font-size:36px;">Survey Results</h1>
	<br>
	<br>
	<table style="margin-left:197px; padding-bottom:130px; ">
	<tr>
	<td>&nbsp;</td>
	<th style="font-size:20px;">Park</th>
	<th style="font-size:20px;">Surveys per Park</th>
	</tr>
	<c:forEach var="survey" items="${surveyList}">
	<tr>
	<td>
	<c:url var="parkImageUrl" value="/img/parks/${fn:toLowerCase(survey.parkCode)}.jpg" /> 
<img src="${parkImageUrl}" alt="Nation Park Picturre" />
</td>
<td style="background-color:gainsboro;">
	${survey.parkName}
	</td>
	<td style="text-align:center; background-color:gainsboro;">
	${survey.survey_total}
	</td>
	</tr>
	</c:forEach>
	</table>
 </section>





<c:import url="/WEB-INF/jsp/common/footer.jsp" />