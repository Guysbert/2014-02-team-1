<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<html>
<head>
<title>Add Book - Worblehat Bookmanager</title>
</head>
<body>
	<h1>Add Book</h1>

	<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.year}</td>
				<td>${book.edition}</td>
				<td>${book.isbn}</td>
				<td>${book.currentBorrowing.borrowerEmailAddress}</td>
				<td>${book.description}</td>
			</tr>
	</c:forEach>

	<form:form commandName="bookDataFormData" method="POST">
            Title:<form:input id="title" path="title" />
		<form:errors path="title" />
		<br />
            Edition:<form:input id="edition" path="edition" />
		<form:errors path="edition" />
		<br />
            ISBN:<form:input id="isbn" path="isbn" />
		<form:errors path="isbn" />
		<br />
            Author:<form:input id="author" path="author" />
		<form:errors path="author" />
		<br />
            Year:<form:input id="year" path="year" />
		<form:errors path="year" />

		<br />
		<input type="submit" id="addBook" value="Add Book" />
		<hr/>
		<a href="<spring:url value="/" htmlEscape="true" />">Back to Home</a>
	</form:form>


</body>
</html>
