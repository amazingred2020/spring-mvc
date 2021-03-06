<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Storage App</title>
<script ENGINE="text/javascript" src="https://code.jquery.com/jquery-1.11.2.js"></script>
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
	<div align="center">
		<h1>Хранилище продуктов</h1>
		<table cellpadding="10">
			<tr>
				<th>№</th>
				<th>Название</th>
				<th>Описание</th>
				<th>Дата добавления</th>
				<th>№ ячейки хранения</th>
				<th>Флаг резервирования</th>
				<th>Действия</th>
			</tr>
			<c:forEach items="${productList}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.description}</td>
					<td>${product.creationDate}</td>
					<td>${product.placeStorage}</td>
					<td>${product.reversed}</td>
					<td>
						<input type="button" class="edit" value="Редактировать">
						<input type="button" class="delete" value="Удалить">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6"></td>
				<td style="text-align: center">
					<input type="button" class="create" value="Создать">
				</td>
			</tr>
		</table>
	</div>
	
	<script src="<c:url value="/resources/js/script.js"/>"/></script>
</body>
</html>