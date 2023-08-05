$(document).ready(function(){

const pathArr = location.pathname.split("/");
const storeId = pathArr[pathArr.length-1];
const listView = 10; // 주문 목록 최대 갯수

$(".move_top").click(function(){
	$("html").animate({ scrollTop: 0 }, 200);
})
	

function errMsg(status){
	if(status.status == 401) {
		alert("권한이 없습니다");
	} else {
		alert("에러");
	}
}

const listInfo = (function(){
	const listArr = ["주문접수 대기 중", "배달 준비 중", "완료"];
	let nowList = listArr[0];
	let page = 1;
	let runNextPage = false; // false일때만 다음페이지 불러올수있다
	let waitCount = 0;
	let procCount = 0;
	let orderList = [];
	let cartList = [];
	
	const getNowList = function(){
		return nowList;
	}
	const setNowList = function(set){
		nowList = listArr[set];
	}
	const resetPage = function(){
		page = 1;
	}
	const nextPage = function(){
		page++;
	}
	const setPage = function(set){
		page = set;
	}
	const nowPage = function(){
		return page;
	}
	const getRunNextPage = function(){
		return runNextPage;
	}
	const setRunNextPage = function(set){
		runNextPage = set;
	}
	const setWaitCount = function(set){
		waitCount = set;
	} 
	const getWaitcount = function() {
		return waitCount;
	}
	const setProcCount = function(set){
		procCount = set;	
	}
	const getProcCount = function(){
		return procCount;
	}
	const getOrderList = function(index){
		return orderList[index];
	}
	const setOrderList = function(set){
		orderList = set;
	}
	const concatOrderList = function(set){
		orderList = orderList.concat(set);
	}
	const getCartList = function(index){
		return cartList[index];
	}
	const setCartList = function(set){
		cartList = set;
	}
	const concatCartList = function(set){
		cartList = cartList.concat(set);
	}
	const resetList = function(){
		cartList = [];
		orderList = [];
	}
	const removeCartList = function(index) {
		cartList.splice(index, 1);
	}
	const removeOrderList = function(index) {
		orderList.splice(index, 1);
	}
	
	return {
		getNowList : getNowList,
		setNowList : setNowList,
		resetPage : resetPage,
		nextPage : nextPage,
		setPage : setPage,
		nowPage : nowPage,
		getRunNextPage : getRunNextPage,
		setRunNextPage : setRunNextPage,
		setWaitCount : setWaitCount,
		getWaitcount : getWaitcount,
		setProcCount : setProcCount,
		getProcCount : getProcCount,
		getOrderList : getOrderList,
		setOrderList : setOrderList,
		getCartList : getCartList,
		setCartList : setCartList,
		concatOrderList : concatOrderList,
		concatCartList : concatCartList,
		resetList : resetList,
		removeCartList : removeCartList,
		removeOrderList : removeOrderList,
		
	}
})();




function htmlWrite(result){
	let html = "";
	for(var i=0;i<result.cartList.length;i++) {
		const orderList = result.orderList[i];
		const cartList = result.cartList[i];
		
		let foodInfo = [];
		for(var j=0;j<cartList.length;j++) {
			foodInfo.push(foodHtml(cartList[j]));	
		}
		
		let btnValue = "";
		let btnClass = "";
		if(listInfo.getNowList() == '주문접수 대기 중') {
			btnValue = "주문 접수";
			btnClass = "order_accept";
		} else {
			btnValue = "완료";
			btnClass = "complete";
		}
		
		html += 
			`<li class="order_box">
				<div class="time">
	    			<div>${moment(orderList.orderDate ).format("MM월 DD일")}</div>
	    			<div>${moment(orderList.orderDate ).format("HH시 mm분")}</div>
	    		</div>
   	
	    		<div class="info">
              		<div style="font-weight: bold;">
               			<span>
              				<span>[메뉴  ${cartList.length }개] ${orderList.totalPrice }원</span> 
              				<span class="payMethod"> ${orderList.payMethod }</span>
            			</span>
           			</div>
                        		
               		<div>${foodInfo } </div>
               		<div>${orderList.deleveryAddress2 }</div>
               		
               		<div>${orderList.storeName }</div> 
	            </div>     	
	            		
                <div class="button_box">
                 	<input type="button" value="${btnValue}" class="${btnClass} btn">
                 </div>
			</li>`;
	}
	return html;
}



function foodHtml(cart){
	let food = cart.foodName;
	
	let option = [];
	if(cart.optionName != null) {
		for(var i=0;i<cart.optionName.length;i++) {
			option.push(cart.optionName[i]);
		}	
	}
	
	if(option != "") {
		option = '[' + option + ']';
	}
	
	return food + option;
}




function orderList(){
	const page = listInfo.nowPage();
	const list = listInfo.getNowList();
	// listInfo.setRunNextPage(true);
	
	$.ajax({
		url: "/admin/management/orderList",
		type: "get",
		data: {
			storeId : storeId,
			list : list,
			page : page
		}	
	})
	.done(function(result){
		const count1 = result.orderList[0].count1;
		const count2 = result.orderList[0].count2;
		
		listInfo.setWaitCount(count1);
		listInfo.setProcCount(count2);
		$(".wait_count").text(count1);
		$(".processing_count").text(count2);
			
			
		const html = htmlWrite(result, list);
		
		$(".order_list").html(html);	
		listInfo.setCartList(result.cartList);
		listInfo.setOrderList(result.orderList);
			
		
	})
	.fail(function(data){
		errMsg(data);
	})	 
}	




// 주문 완료 메세지 받기
const socket = new SockJS('/websocket');
const stompClient = Stomp.over(socket);
stompClient.debug = null; // stomp 콘솔출력 X

stompClient.connect({}, function() {

	stompClient.subscribe('/topic/order-complete/' + storeId, function(message) {
		// 화면에 출력중인 view 갯수 
		const list = $(".order_list li").length;
		
		if(list == listInfo.getWaitcount()) {
			orderList();
		}
	});
});



// 접수대기, 처리 중 목록 클릭
$(".aside_tab li").click(function(){
	$(".order_list").html("");
	$(".aside_tab li").removeClass("active");
	$(this).addClass("active");
	
	const index = $(this).index();
	listInfo.setNowList(index);
	listInfo.resetPage();
	listInfo.setRunNextPage(false);
	
	orderList();
})



// 스크롤시 다음 페이지
$(window).scroll(function(){
	const winHeight = $(window).height();
	const docHeight = $(document).height();
	const top = $(window).scrollTop();
	
	if(docHeight <= winHeight + top + 10) {
		if(!listInfo.getRunNextPage()) {
			
			const list = $(".order_list li").length;
			if(list == 0) {
				listInfo.resetPage();
			} else {
				if(list == (listView * listInfo.nowPage())) {
					listInfo.setPage(Math.floor((list - 1) / listView) + 2);
				}
			}
			orderList();
			listInfo.setRunNextPage(true);
			
			setTimeout(function(){
				listInfo.setRunNextPage(false);
			},2000);
			
			return;
		}
		
	} 
}) // scroll


orderList();



function listRefresh(index, count){
	listInfo.removeCartList(index);
	listInfo.removeOrderList(index);
	$(".order_box").eq(index).remove();
	
	
	const list = $(".order_list li").length;
	if(list == 0) {
		listInfo.resetPage();
	} else {
		listInfo.setPage(Math.floor((list - 1) / listView) + 1);
	}
	
	if(list < count) {
		orderList();
	}
}



// 주문수락 시 
function accept(index){
	const waitCount = listInfo.getWaitcount() - 1;
	const procCount = listInfo.getProcCount() + 1;
	$(".wait_count").text(waitCount);
	$(".processing_count").text(procCount);
	listInfo.setWaitCount(waitCount);
	listInfo.setProcCount(procCount);
	
	const count = listInfo.getWaitcount();
	listRefresh(index, count);
	
}

// 주문취소 시
function cancle(index) {
	const waitCount = listInfo.getWaitcount() - 1;
	$(".wait_count").text(waitCount);
	listInfo.setWaitCount(waitCount);
	
	const count = listInfo.getWaitcount();
	listRefresh(index, count);
}



// 주문완료 시
function complete(index) {
	const procCount = listInfo.getProcCount() - 1;
	$(".processing_count").text(procCount);
	listInfo.setProcCount(procCount);
	
	const count = listInfo.getProcCount();
	listRefresh(index, count);
}


// 주문접수 모달 
$(document).on("click", ".order_accept", function(){
	const modal = $(".order_accept_modal");
	const orderIndex = $(this).parents("li").index();
	
	const orderInfo = listInfo.getOrderList(orderIndex);
	const foodInfo = listInfo.getCartList(orderIndex);
	
	const orderNum = orderInfo.orderNum;
	const userId = orderInfo.userId;
	const deleveryAddress2 = orderInfo.deleveryAddress2;
	const deleveryAddress3 = orderInfo.deleveryAddress3 ? orderInfo.deleveryAddress3 : "";
	const request = orderInfo.request ? orderInfo.request : ""; 
	const phone = orderInfo.phone;
	
	let food = "";
	for(i=0;i<foodInfo.length;i++) {
		food += `<li>${ foodHtml(foodInfo[i]) }  ${ foodInfo[i].amount }개</li>`
	}
	
	
	const addressHtml = `<div>${deleveryAddress2}</div>
                    	<div>${deleveryAddress3}</div>
                    	<div>${phone}</div>`
                  	
	
	modal.find(".delevery_address").html(addressHtml);
	modal.find(".request > div").text(request);
	modal.find(".menu ul").html(food);

	openModal(modal);
	
	
	 
	 const timerModal = $(".delevery_timer_modal"); 
	 
	// 배달시간 설정 모달
	$(".delevery_timer_btn").off().click(function(){
		timerModal.find("li").removeClass("select");
		timerModal.find("li[data-time=30]").addClass("select");
		openModal(timerModal);
	})
 		
 		
	// 시간 설정	
	$(".delevery_timer_modal li").off().click(function(){
		timerModal.find("li").removeClass("select");
		$(this).addClass("select");
	})
		
		
	// 주문수락 완료	
	$(".accept").off().click(function(){
		const time = $(".delevery_timer_modal .select").data("time");
		
		if(!time) {
			swal("시간을설정해주세요");
			return;
		}
		
		const data = {
			orderNum : orderNum,
			time : time,
			userId : userId
		}
		
		$.ajax({
			url: "/admin/management/orderAccept",
			data: data,
			type: "PATCH"
		})
		.done(function(){
			swal("주문접수완료");
			closeModal();
			accept(orderIndex);
			
			
		})
		.fail(function(data){
			errMsg(data);
		})
		
	})
	
	
	// 주문 거부하기
	$(".order_cancle_btn").off().click(function(){
		const cancleModal = $(".order_cancle_modal");
		openModal(cancleModal);
		cancleModal.find("li").removeClass("select");
		
		let cancleReason = "";
		
		// 거부사유 선택
		cancleModal.find("li").off().click(function(){
			cancleModal.find("li").removeClass("select");
			$(this).addClass("select");
			cancleReason = $(this).data("reason");
		})


			
		// 거부하기
		$(".order_cancle").off().click(function(){
			const impUid = orderInfo.impUid;
			const totalPrice = orderInfo.totalPrice;
			const usedPoint = orderInfo.usedPoint;
			const deleveryTip = orderInfo.deleveryTip;
			
			if(!cancleReason) {
				swal('주문거부 사유를 선택해주세요');
				return;
			}
			
			const data = {
				orderNum : orderNum,
				cancleReason : cancleReason,
				userId : userId,
				impUid : impUid,
				totalPrice : totalPrice,
				usedPoint : usedPoint,
				deleveryTip : deleveryTip
			}
			
			$.ajax({
				url: "/admin/management/orderCancle",
				type: "PATCH",
				data: data
			})
			.done(function(){
				cancle(orderIndex); 
				swal("취소완료");
				// 결제 취소하기 추가
				
				closeModal();
				
			})
			.fail(function(data){
				errMsg(data);
			})
		})
	})
})



	

// 배달 완료	
$(document).on("click", ".complete", function(){
	const orderIndex = $(this).parents("li").index();
	const orderInfo = listInfo.getOrderList(orderIndex);
	const orderNum = orderInfo.orderNum;
	
	const userId =  orderInfo.userId;
	const data = {
		userId : userId,
		orderNum : orderNum
	}
	
	swal("배달 완료후 눌러주세요", {
		  buttons: ["취소", "완료"],
	})
	.then(function(value){
		if(!value) {
			return;
		}
		$.ajax({
			url: "/admin/management/orderComplete",
			type: "PATCH",
			data: data
		})
		.done(function(result){
			complete(orderIndex);
		})
		.error(function(){
			swal("에러");
		})
	}) 
})
	
	
	
})