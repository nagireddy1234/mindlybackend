package com.mindly.domain;

import java.util.Map;

import lombok.Data;

@Data
public class Response {

	Map<String,Bid[]> bids;
	
}
