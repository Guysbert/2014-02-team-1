<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<html>
<head>
<title>Worblehat Bookmanager v1.4</title>
</head>
<body>
	<h1 id="welcome_heading">Worblehat Bookmanager</h1>
	<h3 id="build_version"><spring:eval expression="@configProperties.getProperty('application.build.version')" /></h3>
	<h3 id="build_date"><spring:eval expression="@configProperties.getProperty('application.build.date')" /></h3>
<ul>
<li><a href="<spring:url value="/bookList" htmlEscape="true" />">Show all Books</a></li>
  <li><a href="<spring:url value="/insertBooks" htmlEscape="true" />">Add a new book</a></li>
   <li><a href="<spring:url value="/borrow" htmlEscape="true" />">Borrow Book</a></li>
   <li><a id="returnAllBooks" href="<spring:url value="/returnAllBooks" htmlEscape="true" />">Return all Books</a></li>
</ul>
</body>
</html>
