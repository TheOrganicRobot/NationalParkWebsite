<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


	<section id="main-content">
	<h1 style="text-align:center; text-decoration: underline; font-size:36px;">Today's Survey</h1>
	<br>
	<br>
	
	<c:url var="addSurveyPost" value="/survey" /> <form:form
		action="${addSurveyPost}" method="POST" modelAttribute="survey">
		<div>
			<label for="parkCode">Favorite National Park</label> 
			<form:select path="parkCode" id="parkCode" name="parkCode" style="height:25px;">
				<option value=""></option>
				<option value="CVNP">Cuyahoga Valley National Park</option>
				<option value="ENP">Everglades National Park</option>
				<option value="GCNP">Grand Canyon National Park</option>
				<option value="GNP">Glacier National Park</option>
				<option value="GSMNP">Great Smoky Mountains National Park</option>
				<option value="GTNP">Grand Teton National Park</option>
				<option value="MRNP">Mount Rainier National Park</option>
				<option value="RMNP">Rocky Mountain National Park</option>
				<option value="YNP">Yellowstone National Park</option>
				<option value="YNP2">Yosemite National Park</option>
			</form:select>
			<form:errors path="parkCode" cssClass="error" style="color:red" />
		</div>
		<br>
		<div>
			<label for="email">Email</label>
			<form:input path="email" style="width:175px; border-radius:1px; height:25px;" />
			<form:errors path="email" cssClass="error" style="color:red" />
		</div>
		<br>
		<div id="textboxid">
			<label for="state">State of Residence</label> <form:select style="height:25px;" path="state" id="state" name="state">
				<option value=""></option>
				<option value="Alabama">Alabama</option>
				<option value="Alaska">Alaska</option>
				<option value="Arizona">Arizona</option>
				<option value="Arkansas">Arkansas</option>
				<option value="California">California</option>
				<option value="Colorado">Colorado</option>
				<option value="Connecticut">Connecticut</option>
				<option value="Delaware">Delaware</option>
				<option value="District of Columbia">District of Columbia</option>
				<option value="Florida">Florida</option>
				<option value="Georgia">Georgia</option>
				<option value="Hawaii">Hawaii</option>
				<option value="Idaho">Idaho</option>
				<option value="Illinois">Illinois</option>
				<option value="Indiana">Indiana</option>
				<option value="Iowa">Iowa</option>
				<option value="Kansas">Kansas</option>
				<option value="Kentucky">Kentucky</option>
				<option value="Louisiana">Louisiana</option>
				<option value="Maine">Maine</option>
				<option value="Maryland">Maryland</option>
				<option value="Massachusetts">Massachusetts</option>
				<option value="Michigan">Michigan</option>
				<option value="Minnesota">Minnesota</option>
				<option value="Mississippi">Mississippi</option>
				<option value="Missouri">Missouri</option>
				<option value="Montana">Montana</option>
				<option value="Nebraska">Nebraska</option>
				<option value="Nevada">Nevada</option>
				<option value="New Hampshire">New Hampshire</option>
				<option value="New Jersey">New Jersey</option>
				<option value="New Mexico">New Mexico</option>
				<option value="New York">New York</option>
				<option value="North Carolina">North Carolina</option>
				<option value="North Dakota">North Dakota</option>
				<option value="Ohio">Ohio</option>
				<option value="Oklahoma">Oklahoma</option>
				<option value="Oregon">Oregon</option>
				<option value="Pennsylvania">Pennsylvania</option>
				<option value="Rhode Island">Rhode Island</option>
				<option value="South Carolina">South Carolina</option>
				<option value="South Dakota">South Dakota</option>
				<option value="Tennessee">Tennessee</option>
				<option value="Texas">Texas</option>
				<option value="Utah">Utah</option>
				<option value="Vermont">Vermont</option>
				<option value="Virginia">Virginia</option>
				<option value="Washington">Washington</option>
				<option value="West Virginia">West Virginia</option>
				<option value="Wisconsin">Wisconsin</option>
				<option value="Wyoming">Wyoming</option>
			</form:select>
			<form:errors path="state" cssClass="error" style="color:red" />
		</div>
		<br>
		<div>
			<label for="activityLevel">Activity Level</label> 
			<form:select style="height:25px;" path="activityLevel" id="activityLevel" name="activityLevel">
				<option value=""></option>
				<option value="inactive">Inactive</option>
				<option value="sedentary">Sedentary</option>
				<option value="active">Active</option>
				<option value="extremely active">Extremely Active</option>
			</form:select>
			<form:errors path="activityLevel" cssClass="error" style="color:red" />
		</div>
		<br>
		<div>
		<label> </label>
			<input type="submit" value="Submit Survey" />
		</div>
	</form:form> </section>

</body>
</html>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />