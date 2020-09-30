package com.mindly.utility;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindly.domain.Bid;

public class BitFinexService {

	private String currency;

	public BitFinexService(String currency) {
		this.currency = currency;
	}

	public BigDecimal getPrice() throws IOException {
		switch (currency) {
		case ("Bitcoin"):
			currency = "BTCEUR";
			break;
		case ("Ethereum"):
			currency = "ETHEUR";
			break;
		case ("Ripple"):
			currency = "XRPUSD";
			break;
		}
		String urlBitfinex = "https://api.bitfinex.com/v1/book/" + currency;

		return new BigDecimal(urlGetData(urlBitfinex));

	}

	private Double urlGetData(String url) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		Map<String, ArrayList<Bid>> bids = (Map<String, ArrayList<Bid>>) mapper.readValue(new URL(url), Object.class);

		Map x = (Map) bids.get("bids").get(0);
		return new Double(x.get("price").toString());

	}
}
