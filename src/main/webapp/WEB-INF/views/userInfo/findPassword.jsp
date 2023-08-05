<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/link.jsp" %>
<link rel="stylesheet" href="/css/layout/nav.css">
<link rel="stylesheet" href="/css/userInfo/find.css">
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	
	<main class="find_password_page">
		<div class="find_info">
			<h3>가입하신 아이디를 입력해주세요</h3>
			<input type="text" name="username" class="username">
			<button type="button" class="next_page">다음</button>
		</div>
	</main>
	
	
	
<%@ include file="/WEB-INF/views/include/nav.jsp" %>	
<%@ include file="/WEB-INF/views/include/footer.jsp" %>	

	<script src="/js/userInfo/findPassword.js"></script>


<script>

</script>
</body>
</html>