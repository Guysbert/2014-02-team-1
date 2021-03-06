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

	<c:forEach items="${books}" begin="0" end="1" var="book">
	ISBN is already in use. If you want to add another copy of this book, all fields have to be the same.
		<table>
	<thead>  
		<tr>
			<th>Title</th>
			<th>Author</th>
			<th>Year</th>
			<th>Edition</th>
			<th>ISBN</th>
			<th>Borrower</th>
			<th>Description</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.year}</td>
				<td>${book.edition}</td>
				<td>${book.isbn}</td>
				<td>${book.currentBorrowing.borrowerEmailAddress}</td>
				<td>${book.description}</td>
			</tr>
				</tbody>
	</table>
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
			Description:<form:input id="description" path="description" />
		<form:errors path="description" />
		<br />
		<input type="submit" id="addBook" value="Add Book" />
		<hr/>
		<a href="<spring:url value="/" htmlEscape="true" />">Back to Home</a>
	</form:form>


</body>
</html>
