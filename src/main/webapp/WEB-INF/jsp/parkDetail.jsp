<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section id="main-content">
<table style="border-bottom: 1px solid #ddd;">
<tr>
<td>
<c:if test="${parkData.parkCode == 'GNP'}">
<c:url var="parkImageUrl" value="/img/parks/${fn:toLowerCase(parkData.parkCode)}.jpg" /> 
<center><img style="padding:10px;" src="${parkImageUrl}" id="detailImg"/></center>
</c:if>
<c:if test="${parkData.parkCode != 'GNP'}">
<c:url var="parkImageUrl" value="/img/parks/${fn:toLowerCase(parkData.parkCode)}.jpg" /> 
<center><img src="${parkImageUrl}" id="detailImg"/></center>
</c:if>


<h2 style="text-align:center">${parkData.name}, ${parkData.state}</h2>
<div style="text-align:center; word-wrap:break-word; margin: 0px 300px 0px 300px;">"${parkData.quote}" <br><br> ~${parkData.quoteAuthor}~</div>

<%--  --%>

<div style="padding: 30px 40px 40px 40px; text-align:center;">${parkData.description}</div>
</td>
</tr>
</table>
<%-- LABEL HERE  --%>

<table width="95%" style="margin:30px; border-collapse:collapse; vertical-align: top;">

<tr>

<td style=" width: 20%; vertical-align: middle; padding-top:40px;">
<div><b>Entry Fee: </b></div>
<div><b>Acreage:</b></div>
<div><b>Elevation: </b></div>
<div><b>Miles of Trail: </b></div>
<div><b>Number of Campsites: </b></div>
<div><b>Year Founded: </b></div>
<div><b>Annual Visitors: </b></div>
<div><b>Number of Active Species: </b></div>
</td>

<%-- VALUES HERE --%>

<td style=" width:10%; vertical-align: middle; padding-top:40px;">
<div><c:if test="${parkData.entryFee == 0}">
	<c:out value="No Charge"/>
</c:if>
<c:if test="${parkData.entryFee != 0}">
	<fmt:formatNumber value = "${parkData.entryFee}" type = "currency"/>
</c:if></div>
<div><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${parkData.acreage}" /></div>
<div><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${parkData.elevationInFt}" /></div>
<div><fmt:formatNumber type = "number" maxFractionDigits = "1" value = "${parkData.milesOfTrail}" /></div>
<div><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${parkData.numOfCampsites}" /></div>
<div>${parkData.yearFounded}</div>
<div><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${parkData.annualVisitors}" /></div>
<div>${parkData.speciesCount}</div>
</td>

<%-- RECOMMENDATIONS HERE --%>
<td style="width:40%;">&nbsp;</td>

<td style="vertical-align:middle; text-align: center;">
<div >
<c:forEach begin="0" end="0" var="weatherDay" items="${weatherList}">

<c:choose>
<c:when test="${weatherDay.forecast == 'rain'}">
<c:out value="Pack rain gear and wear waterproof shoes."/>
</c:when>
<c:when test="${weatherDay.forecast == 'snow'}">
<c:out value="Pack snow shoes."/>
</c:when>
<c:when test="${weatherDay.forecast == 'thunderstorms'}">
<c:out value="Seek shelter and avoid hiking on exposed ridges."/>
</c:when>
<c:when test="${weatherDay.forecast == 'sunny'}">
<c:out value="Pack sunblock."/>
</c:when>
<c:otherwise>
<c:out value="Have Fun!"/>
</c:otherwise>
</c:choose>
<div>
<c:if test = "${weatherDay.highTemp > 75}">
<c:out value="Bring an extra gallon of water."/>
</c:if>
<c:if test = "${weatherDay.lowTemp < 20 || weatherDay.highTemp < 20}">
<c:out value="It's dangerous to be exposed to frigid temperatures."/>
</c:if>
<c:if test = "${(weatherDay.highTemp-weatherDay.lowTemp) > 20}">
<c:out value="Wear breathable layers."/>
</c:if>
</div>

</c:forEach>
</div>

<%-- PICTURE HERE --%>


<c:forEach begin="0" end="0" var="weatherDay" items="${weatherList}">

	<c:if test="${weatherDay.forecast == 'partly cloudy'}">
		<c:url var="weatherImageUrl" value="/img/weather/partlyCloudy.png"/>
	</c:if>
	
	<c:if test="${weatherDay.forecast != 'partly cloudy'}">
		<c:url var="weatherImageUrl" value="/img/weather/${weatherDay.forecast}.png" />
	</c:if>
 
	<c:url var="weatherDetailUrl" value="weather">
	<c:param name="parkCode" value="${weatherDay.parkCode}" /></c:url> 
	<a href="${weatherDetailUrl}"><img style="max-width:60%; height:auto;"  src="${weatherImageUrl}"/></a>
	
	<div><a href="${weatherDetailUrl}">Five Day Forecast</a></div>
	</c:forEach>
</td>
<%-- TEMP HERE --%>

<td style=" width:10%; vertical-align:bottom; padding-bottom:20px;">
<c:forEach begin="0" end="0" var="weatherDay" items="${weatherList}">
	<h2 style="text-decoration: underline; text-transform: capitalize; padding-top:5px;">${weatherDay.forecast}</h2>
	
	<c:if test="${convert == 'fahrenheit' || empty convert}">
	<h3>High: ${weatherDay.highTemp} &#176;F</h3>
	<h3>Low: ${weatherDay.lowTemp} &#176;F</h3>
	</c:if>
	
	<c:if test="${convert == 'celcius'}">
	<h3>High: <fmt:formatNumber type="number" pattern="#" value="${(weatherDay.highTemp - 32) / 1.8}" /> &#176;C</h3>
	<h3>Low: <fmt:formatNumber type="number" pattern="#" value="${(weatherDay.lowTemp - 32) / 1.8}" /> &#176;C</h3>
	</c:if>
	
</c:forEach>
<c:url value="/convertToFahrenheit" var="fahrenheit"/>
<c:url value="/convertToCelcius" var="celcius"/>

<c:if test="${convert == 'fahrenheit' || empty convert}">
<form action="${celcius}" method="POST">
<input type="hidden" id="parkCode" name="parkCode" value="${parkData.parkCode}">
<button class="button button2" type="submit" name=celcius value="celcius">Celcius</button></form>
</c:if>

<c:if test="${convert == 'celcius'}">
<form action="${fahrenheit}" method="POST">
<input type="hidden" id="parkCode" name="parkCode" value="${parkData.parkCode}">
<button class="button button3" type="submit" name=fahrenheit value="fahrenheit">Fahrenheit</button></form>
</c:if>
</td>


</tr>
</table>
<%--  --%> 
</section>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />