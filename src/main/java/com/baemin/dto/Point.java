package com.baemin.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Point {
	private long userId;
	private Date usedDate;
	private String info;
	private int point;
}