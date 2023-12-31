<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/link.jsp" %>
<!-- sock js -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>
<!-- STOMP -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>	
<!-- 날짜 포맷 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js" ></script>

<link rel="stylesheet" href="/css/admin/order.css" >
<link rel="stylesheet" href="/css/modal.css" >
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="move_top"><div></div></div>


<div class="wrap">
	<input type="hidden" value="${id }" id="store_id">
	  <section class="tab">
	     <ul class="box">
    	 	<li><a href="/admin/myStore">운영중인가게</a></li>
			<li><a href="/admin/management/sales/${id }">매출 확인</a></li>
			<li><a href="/">홈으로</a></li>
		</ul>
	</section>

    <aside>
        <ul class="aside_tab">
            <li class="active">
            	<div>접수 대기</div>
            	<div class="wait_count"></div>
           	</li>
            <li>
            	<div>처리 중</div>
            	<div class="processing_count"></div>
            </li>
            <!-- <li>주문 조회</li> -->
         </ul>
    </aside>



    <main>
        <div id="cont_box">
            <ul class="order_list">
                <!-- 주문접수 > 접수 대기 -->
                <!-- <li> -->
                    <!-- <ul> -->
                        <!-- 주문목록 1개 -->
                        <!-- <li class="order_box">
						</li> -->
		 						
                        <!-- 주문목록 1개 -->
                    <!-- </ul> -->
                <!-- </li> -->
            </ul>
        </div>
	</main>
</div>




	

<%@ include file="/WEB-INF/views/modal/modal_adminOrder.jsp" %>



<script type="text/javascript" src="/js/util/util.js" ></script>
<script type="text/javascript" src="/js/admin/adminOrder.js" ></script>
</body>
</html>