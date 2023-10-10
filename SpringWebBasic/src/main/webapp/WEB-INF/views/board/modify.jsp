<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>${article.boardNo} 본 게시글 수정하기</h1>
  
    <form action="/basic/board/modify" method="post">
  		<input type="hidden" name="boardNo" value="${article.boardNo}">
        <p>
        	# 작성자: <input type="text" name="writer" value="${article.writer}"><br>
        	# 제목: <input type="text" name="title" value="${article.title}"><br>
            #내용:<textarea rows="3" name = "content" >${article.content}</textarea><br>
           <div class="btn-group">
                <button type="submit">수정</button>
                <button type="button" onclick="history.back()">이전으로</button>
            </div>
        </p>
    </form>

</body>
</html>