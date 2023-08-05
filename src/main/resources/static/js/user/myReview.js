$(document).ready(function(){
	
	
	$(".modify_btn").click(function(){
		const orderNum = $(this).siblings(".review_order_num").val();
		const reviewImg = $(this).siblings(".review_img").val();
		const storeId = $(this).siblings(".review_store_id").val();
		const reviewContent = $(this).siblings(".review_review_content").val();
		const modal = $(".review_modify_modal")
		
		// 기본 상태 초기화
		modal.find("textarea").val(reviewContent);
		modal.find(".order_num").val(orderNum);
		modal.find(".store_id").val(storeId);
		modal.find(".score").val(0);
		modal.find(".score_box i").removeClass("fas");
		
		const previewBox = $(".preview_box");
		const preview = previewBox.find(".preview");
		
		if(reviewImg) {
			modal.find(previewBox).show();
			preview.attr("src", reviewImg);
		} else {
			modal.find(previewBox).hide();
			preview.attr("src", "");
		}
		
		openModal(modal);
		
		
		
		
		// 별점주기
		let score = 0;
	
		$(".score_box i").off().click(function() {
			score = $(this).index() + 1;
				
			$(".score_box i").removeClass("fas");
			$(this).addClass("fas").prevAll().addClass("fas");
	
			modal.find(".score").val(score);
	
			inputCheck(modal);
		});
		
		
		
		$(".review_text textarea").off().keyup(function() {
			inputCheck(modal);
		})
		
		
		
		// 리뷰 작성, 별점 체크 했는지 확인
		function inputCheck(modal) {
			let text = modal.find(".review_text textarea").val();
			let score = modal.find(".score").val();
			
			if(text.length == 0 || score == "" || score == null) {
				modal.find(".review_submit_btn").css("background", "#ddd");
				modal.find(".review_submit_btn").attr("disabled", true);
			} else {
				modal.find(".review_submit_btn").attr("disabled", false);
				modal.find(".review_submit_btn").css("background", "#30DAD9");
			}
		}
		
		
	})
	
	
	

	$(".delete_btn").click(function() {
		const orderNum = $(this).siblings(".review_order_num").val();
		const target = $(this).parents("li");
		
		swal({
			buttons: ["취소", "삭제"],
			text: "리뷰를 삭제합니다",
		})
		.then(function(value) {
			if (value) {
				
				 $.ajax({
					url: "/user/review",
					type: "DELETE",
					data: {orderNum : orderNum},
				})
				.done(function(){
					target.remove();
				})
				.fail(function(){
					alert("에러");
				})
			}
		});	
		
	})
	
	
	
	
	$(".img").change(function(e){
		imgPreview(e, $(this));
	})
	
	     
})