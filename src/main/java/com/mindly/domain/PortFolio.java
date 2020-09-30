package com.mindly.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
@Entity
public class PortFolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1569539276877896354L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
    private String cryptocurrency;
	
	@NotNull(message = "Amount name is required")
	private BigDecimal amount;
	
	@Future
	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfPurchase;
	
	@NotNull
    @NotBlank(message = "Wallet location is required")
	private String walletLocation;
	
	private BigDecimal currentMarketValue;
	
	

}
