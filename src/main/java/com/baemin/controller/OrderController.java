package com.baemin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baemin.dto.Cart;
import com.baemin.dto.CartList;
import com.baemin.dto.OrderInfo;
import com.baemin.dto.OrderList;
import com.baemin.login.LoginService;
import com.baemin.service.OrderService;
import com.baemin.service.PaymentService;
import com.baemin.util.FoodInfoFromJson;
import com.baemin.util.Page;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentService;
	
	
	@GetMapping("/order/{storeId}")
	public String order(@PathVariable long storeId ,HttpSession session, Model model, @AuthenticationPrincipal LoginService user) {
		
		// 장바구니 없거나 다른가게일때 뒤로가기 구현
		CartList cartList = (CartList) session.getAttribute("cartList");
		model.addAttribute("cartList", cartList);

		if (user != null) {
			model.addAttribute("user", user.getUser());
		}
		return "order/order";
	}
	
	
	// 현장에서 결제
	@PostMapping("/order/payment-cash")
	public ResponseEntity<String> payment(HttpSession session, OrderInfo orderInfo, long totalPrice, @AuthenticationPrincipal LoginService user) throws IOException {
		
		CartList cartList = (CartList) session.getAttribute("cartList");
		// 실제 계산 금액 가져오기
		long orderPriceCheck = orderService.orderPriceCheck(cartList);
		
//		System.out.println("계산금액 = " + totalPrice + " 실제 계산해야할 금액 = " + orderPriceCheck );
		
		if(orderPriceCheck == totalPrice) {
			orderService.order(cartList, orderInfo, user);
			session.removeAttribute("cartList");
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	// 카드 결제 성공 후
	@PostMapping("/order/payment/complete")
	public ResponseEntity<String> paymentComplete(HttpSession session, OrderInfo orderInfo, long totalPrice, @AuthenticationPrincipal LoginService user) throws IOException {
		
		String token = paymentService.getToken();
		
		// 결제 완료된 금액
		int amount = paymentService.paymentInfo(orderInfo.getImpUid(), token);
		
		try {
			// 주문 시 사용한 포인트
			int usedPoint = orderInfo.getUsedPoint();
			
			if (user != null) {
				int point = user.getUser().getPoint();
				
				// 사용된 포인트가 유저의 포인트보다 많을 때
				if (point < usedPoint) {
					paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "유저 포인트 오류");
					return new ResponseEntity<String>("유저 포인트 오류", HttpStatus.BAD_REQUEST);
				}

			} else {
				// 로그인 하지않았는데 포인트사용 되었을 때
				if (usedPoint != 0) {
					paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "비회원 포인트사용 오류");
					return new ResponseEntity<String>("비회원 포인트 사용 오류", HttpStatus.BAD_REQUEST);
				}
			}
			
			CartList cartList = (CartList) session.getAttribute("cartList");
			// 실제 계산 금액 가져오기
			long orderPriceCheck = orderService.orderPriceCheck(cartList)  - usedPoint;
			
			// 계산 된 금액과 실제 금액이 다를 때
			if (orderPriceCheck != amount) {
				paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "결제 금액 오류");
				return new ResponseEntity<String>("결제 금액 오류, 결제 취소", HttpStatus.BAD_REQUEST);
			}
			
			orderService.order(cartList, orderInfo, user);
			session.removeAttribute("cartList");
			
			return new ResponseEntity<>("주문이 완료되었습니다", HttpStatus.OK);
			
		} catch (Exception e) {
			paymentService.payMentCancle(token, orderInfo.getImpUid(), amount, "결제 에러");
			return new ResponseEntity<String>("결제 에러", HttpStatus.BAD_REQUEST);
		}
		
		
	}	
	
	
	
	
	
	@GetMapping({"/orderList", "/orderList/{page}"})
	public String orderList(@AuthenticationPrincipal LoginService user, Model model, @PathVariable(required = false) Integer page) {
		if (user == null) {
//			System.out.println("주문목록 비로그인");
		} else {
			long userId = user.getUser().getId();

			Page p = new Page(page);
			List<OrderList> orderList = orderService.orderList(userId, p);
			
			if (orderList.size() == 0) {
				return "order/orderList";
			}

			List<List<Cart>> cartList = new ArrayList<>();

			for (int i=0;i<orderList.size();i++) {
				cartList.add(FoodInfoFromJson.foodInfoFromJson(orderList.get(i).getFoodInfo()));
			}
			p.totalPage(orderList.get(0).getListCount());
			model.addAttribute("page", p);
			model.addAttribute("user", user.getUser());
			model.addAttribute("cartList", cartList);
			model.addAttribute("orderList", orderList);
		}

		return "order/orderList";
	}
	

	
	@GetMapping("/orderListDetail/{orderNum}")
	public String orderDetail(@PathVariable String orderNum, Model model, @AuthenticationPrincipal LoginService user) {

		OrderList orderDetail = orderService.orderListDetail(orderNum);
		
		if (user != null && (user.getUser().getId() != orderDetail.getUserId())) {
			return "redirect:/";
		} else if (user == null) {
			return "redirect:/";
		}
		
		List<Cart> list = FoodInfoFromJson.foodInfoFromJson(orderDetail.getFoodInfo());

		model.addAttribute("orderDetail", orderDetail);
		model.addAttribute("cart", list);

		return "order/orderListDetail";
	}
	
	
}
