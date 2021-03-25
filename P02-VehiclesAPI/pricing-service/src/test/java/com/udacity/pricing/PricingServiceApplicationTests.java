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

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


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
		assertThat(response.getStatusCode().equals(HttpStatus.OK));

	}

	@Test
	public void getPriceById(){
		ResponseEntity<Price> response = this.testRestTemplate.getForEntity("http://localhost:"+port+"/prices/2", Price.class);
		HttpStatus statusCode = response.getStatusCode();
		assertThat(statusCode.value()).isEqualTo(200);
		assertThat(response.getBody().getCurrency()).isEqualTo("USD");
		assertThat(response.getBody().getPrice()).isBetween(new BigDecimal("22000.33"), new BigDecimal("27000.33"));
	}

}
