<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Park Geek</title>
    <c:url value="/css/npgeek.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
    <header>
    		<c:url value="/homepage" var="homepage" />
    		<c:url value="/img/logo.png" var="logoSrc" />
        <a href="${homepage}">
        		<img src="${logoSrc}" alt="National Park Geek logo" width="40%" height="auto"/>
        </a>
        
       
    </header>
    <nav>
        
       
        <c:url value="homepage" var="homepage"></c:url>
        <c:url value="survey" var="survey"></c:url>
        
        
        <ul>
            <li><a href="${homepage}">Home</a></li>
            <li><a href="${survey}">Survey</a></li>     
        </ul>
    </nav>