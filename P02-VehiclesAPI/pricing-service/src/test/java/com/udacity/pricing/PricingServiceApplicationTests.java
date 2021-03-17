package com.udacity.pricing;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private PriceRepository priceRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPrices(){
		ResponseEntity<Price> response = this.testRestTemplate.getForEntity("http://localhost:"+port+"/prices/", Price.class);
		assert(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void getPriceById(){
		ResponseEntity<Price> response = this.testRestTemplate.getForEntity("http://localhost:"+port+"/prices/2", Price.class);
		assert(response.getStatusCode().equals(HttpStatus.OK));
	}

}
