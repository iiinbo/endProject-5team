<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/link.jsp" %>
<link rel="stylesheet" href="/css/layout/nav.css">
<link rel="stylesheet" href="/css/home.css">
 
<%@ include file="/WEB-INF/views/include/header.jsp" %>
 	
 
 
 <div class="title">제목</div>
 <div class="content">내용</div>
 
 
 <input type=text class="input">
 <button>버튼</button>
 
 
 
 
 
<script>

$("button").click(function(){
	const data = {
		input : $(".input").val()
	}
	console.log(data);
	$.ajax({
		url: "/test2",
		type: "get",
		data : data,
	})
	.done(function(result){
		$(".title").text(result);
		$(".content").text(result);	
		
		
		
	})
	.fail(function(){
		
	})
})

	
 
</script>
 
 
</body>
 
</html>