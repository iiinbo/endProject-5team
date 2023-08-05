<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="/css/admin/modal_adminOrder.css" >
    
	<div id="modal_bg"></div>
	
	<!-- 주문 접수하기 모달 -->
	
	<div class="order_accept_modal modal">
	<div id="modal_header">
		<h1>접수 대기</h1>    
		<button class="closeA"><i class="fas fa-times"></i></button>
	</div>
            
	<div class="modal_box">
		<section>
			<article class="delevery_info">
				<h2>배달 정보</h2>
				
				<button class="order_cancle_btn">주문거부하기</button>
				
                <div class="info_area">
                    <div>
                    	<div>배송지</div>
                    	<div>연락처</div>
                    </div>
                    
                    <div class="delevery_address">
                    	<div>천안시 서북구</div>
                    	<div>상세주소</div>
                    	<div>01012345678</div>
                    </div>
                </div>
            </article>

            <article class="request">
                <h2>요청사항</h2>
                <div>수저 필요없어요</div>    
            </article>
            
            <article class="menu">
                <h2>주문 상세</h2>
                <ul>
                    <!-- <li>
                    	<span>메뉴 이름  1000원 1개</span>
                    </li>  -->
                </ul>    
            </article>
        </section>
	</div>
        
    <div id="btn_box">
        <button class="closeB">취소</button>
        <button class="delevery_timer_btn">주문 접수</button>
    </div>
</div>

	<!-- 주문 접수하기 모달 -->
	


	
	<!-- 주문 거부하기 -->
	<div class="order_cancle_modal modal">
		<div id="modal_header">
			<h1>주문거부</h1>
			<button class="closeA"><i class="fas fa-times"></i></button>
		</div>
		
		<div class="modal_box">
			<h2>주문거부 사유를 선택해주세요</h2>
			<ul>
				<li data-reason="배달불가지역" >배달불가지역</li>
				<li data-reason="재료소진" >재료소진</li>
				<li data-reason="배달지연" >배달지연</li>
				<li data-reason="기타" >기타</li>
			</ul>
		</div>
		
		<div id="btn_box">
	        <button class="closeB">취소</button>
	        <button class="order_cancle">거부하기</button>
	    </div>
	</div>
		
	<!-- 주문 거부하기 -->
	
	
	
	
	<!-- 배달 시간 설정 모달 -->
		
	<div class="delevery_timer_modal modal">
		<div id="modal_header">
			<h1>접수 대기</h1>
			<button class="closeA"><i class="fas fa-times"></i></button>
		</div>
	
		<div class="modal_box">
			<h2>배달 시간을 선택해주세요</h2>
			
			<section>
				<ul>
					<li data-time=20>20분</li>
					<li data-time=30 class="select">30분</li>
					<li data-time=40>40분</li>
					<li data-time=50>50분</li>
					<li data-time=60>60분</li>
					<li data-time=90>90분</li>
				</ul>
			</section>
		</div>
		
		<div id="btn_box">
	        <button class="closeB">취소</button>
	        <button class="accept">접수하기</button>
	    </div>
		    
	</div>

	
	
	
	<!-- 배달 시간 설정 모달 -->
	
	
	
	
