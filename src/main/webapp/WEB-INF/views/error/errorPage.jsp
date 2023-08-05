<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/link.jsp" %>
<style>
body {
	text-align: center;
	font-size: 3rem;
}

img {
	width: 400px;
	max-width: 70%;
	display: block;
	margin: 0 auto;
}

div {
	margin: 0 0 20px 0;
}

button {
	border: 1px solid #999;
	background: #fff;
	padding: 10px 30px;
	cursor: pointer;
}
</style>

</head>
<body>
 	<img alt="이미지" src="/img/error.png">
	<div>${errorCode }</div>
	<button>홈으로 돌아가기</button>
</body>

<script>
	const btn = document.querySelector("button");

	btn.addEventListener('click', function() {
		location.href = "/";
	});
</script>



</html>