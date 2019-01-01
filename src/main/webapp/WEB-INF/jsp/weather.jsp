<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<section id="main-content">
<table style="width:100%; background-color:DarkSeaGreen; margin-top:-10px; border-radius:10px; border-bottom-left-radius:0px; border-bottom-right-radius:0px;">
<tr style="border-collapse:collapse;">
<td colspan="4" style="text-align:left; vertical-align:middle;">
<h1 style="text-align:center; padding-bottom:15px;">Five Day Forecast For: </h1><h1 style="text-align:center; padding-bottom:15px;">${parkData.name}, ${parkData.state}</h1> 
</td>

<td style="width:30%">


<c:url var="parkImageUrl" value="/img/parks/${fn:toLowerCase(parkData.parkCode)}.jpg" /> 
<img style="vertical-align:top;" src="${parkImageUrl}" />

</td>
</tr>
</table>





<%------------------------------------------------------------------------------%>







<table style="width:96%;  border-collapse:collapse;">
<tr>


<td style="width:36%; text-align: center;">

<c:forEach begin="0" end="0" var="weatherDay" items="${weatherList}">

<%-- WEATHER TYPE --%>

	<h2 style="text-decoration: underline; text-transform: capitalize; padding:0px; margin:0px;">${weatherDay.forecast}</h2>
	
<%-- PICTURE --%>
	
	<c:if test="${weatherDay.forecast == 'partly cloudy'}">
		<c:url var="weatherImageUrl" value="/img/weather/partlyCloudy.png"/>
	</c:if>
	
	<c:if test="${weatherDay.forecast != 'partly cloudy'}">
		<c:url var="weatherImageUrl" value="/img/weather/${weatherDay.forecast}.png" />
	</c:if>
 
	<c:url var="weatherDetailUrl" value="weather"/>
	<img style="max-width:60%; height:auto;"  src="${weatherImageUrl}"/>
	

<%-- RECOMMENDATIONS --%>
<br>
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
<br>
<c:if test = "${weatherDay.highTemp > 75}">
<c:out value="Bring an extra gallon of water."/>
</c:if>
<c:if test = "${weatherDay.lowTemp < 20 || weatherDay.highTemp < 20}">
<c:out value="It's dangerous to be exposed to frigid temperatures."/>
</c:if>
<c:if test = "${(weatherDay.highTemp-weatherDay.lowTemp) > 20}">
<c:out value="Wear breathable layers."/>
</c:if>

<%-- TEMP --%>
	
	<c:if test="${convert == 'fahrenheit' || empty convert}">
	<h3>High: ${weatherDay.highTemp} &#176;F &nbsp;&nbsp;&nbsp;&nbsp; Low: ${weatherDay.lowTemp} &#176;F</h3>

	</c:if>
	
	<c:if test="${convert == 'celcius'}">
	<h3>High: <fmt:formatNumber type="number" pattern="#" value="${(weatherDay.highTemp - 32) / 1.8}" /> &#176;C &nbsp;&nbsp;&nbsp;&nbsp;
	 Low: <fmt:formatNumber type="number" pattern="#" value="${(weatherDay.lowTemp - 32) / 1.8}" /> &#176;C</h3>
	</c:if>
	
</c:forEach>
<c:url value="/weatherConvertToFahrenheit" var="fahrenheit"/>
<c:url value="/weatherConvertToCelcius" var="celcius"/>

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









<c:forEach begin="1" end="4" var="weatherDay" items="${weatherList}">
<td style="width: 16%; text-align: center; vertical-align:bottom">



<h2 style="text-decoration: underline; text-transform: capitalize; padding:0px; margin:0px;">${weatherDay.forecast}</h2>
	
<%-- PICTURE --%>
	
	<c:if test="${weatherDay.forecast == 'partly cloudy'}">
		<c:url var="weatherImageUrl" value="/img/weather/partlyCloudy.png"/>
	</c:if>
	
	<c:if test="${weatherDay.forecast != 'partly cloudy'}">
		<c:url var="weatherImageUrl" value="/img/weather/${weatherDay.forecast}.png" />
	</c:if>
 
	<c:url var="weatherDetailUrl" value="weather"/>
	<img style="max-width:80%; height:auto;"  src="${weatherImageUrl}"/>
	

<%-- RECOMMENDATIONS --%>
<br>

<%-- TEMP --%>
	
	<c:if test="${convert == 'fahrenheit' || empty convert}">
	<h3>High: ${weatherDay.highTemp} &#176;F </h3><h3> Low: ${weatherDay.lowTemp} &#176;F</h3>

	</c:if>
	
	<c:if test="${convert == 'celcius'}">
	<h3>High: <fmt:formatNumber type="number" pattern="#" value="${(weatherDay.highTemp - 32) / 1.8}" /> &#176;C</h3>
	<h3>Low: <fmt:formatNumber type="number" pattern="#" value="${(weatherDay.lowTemp - 32) / 1.8}" /> &#176;C</h3>
	</c:if>



</td>
</c:forEach>
</tr>
</table>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />