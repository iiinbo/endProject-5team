package com.baemin.dto;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Sales {
	private String orderDate;
	private long total;
	
	public Sales() {}

	public Sales(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
