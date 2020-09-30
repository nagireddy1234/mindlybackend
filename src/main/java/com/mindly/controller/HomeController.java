package com.mindly.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindly.domain.PortFolio;
import com.mindly.repository.PortfolioRepository;
import com.mindly.utility.BitFinexService;

@RequestMapping("/portfolio")
@RestController
@CrossOrigin(origins = "*")
public class HomeController {

	

	@Autowired
	private PortfolioRepository portFolioRepository;

	@PostMapping
	public PortFolio createPortFolio(@RequestBody PortFolio portFolio) {
		return portFolioRepository.save(portFolio);
	}

	@DeleteMapping("/{id}")
	public void deletePortFolio(@PathVariable Long id) {
		try {

			portFolioRepository.deleteById(id);
			

		} catch (EmptyResultDataAccessException e) {
			
		}

	}

	@GetMapping()
	public ResponseEntity<List<PortFolio>> getPortFolioList() throws IOException {
		List<PortFolio> portFolioList= portFolioRepository.findAll();
		for (PortFolio portfolio : portFolioList) {
            portfolio.setCurrentMarketValue(portfolio.getAmount()
                    .multiply(new BitFinexService(portfolio.getCryptocurrency()).getPrice()));
        }

        return new ResponseEntity<>(portFolioList, HttpStatus.OK);
	}

}
