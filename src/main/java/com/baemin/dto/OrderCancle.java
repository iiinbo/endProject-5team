package com.baemin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderCancle {

	private String OrderNum;
	private String cancleReason;
	private long userId;
	private int totaloPrice;
	private int usedPoint;
	private int deleveryTip;
	
	private String impUid; // 아임포트 결제번호
}
