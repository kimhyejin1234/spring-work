<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>request-self 컨트롤러를 이용한 요청 처리 작업 중!</h1>
	<form action="/basic/request/basic01_self">
		<input type="submit" value="GET요청">
	</form>
	<form action="/basic/request/basic01_self" method="post">
		<input type="submit" value="POST요청">
	</form>
</body>
</html>