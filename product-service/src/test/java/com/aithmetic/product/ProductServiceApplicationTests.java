package com.aithmetic.product;

import com.aithmetic.product.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceApplicationTests.class);

	@Container
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0")
			.withDatabaseName("ecommerce")
			.withUsername("root")
			.withPassword("Root@123");

	@Autowired
	private MockMvc mockMvc;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		String jdbcUrl = "jdbc:mysql://localhost:" + mySQLContainer.getMappedPort(3306) + "/ecommerce";

		//String jdbcUrl = mySQLContainer.getJdbcUrl();
		String username = mySQLContainer.getUsername();
		String password = mySQLContainer.getPassword();

		dynamicPropertyRegistry.add("spring.datasource.url", () -> jdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", () -> username);
		dynamicPropertyRegistry.add("spring.datasource.password", () -> password);

		logger.info("Setting dynamic properties for MySQLContainer - JDBC URL: {}, Username: {}", jdbcUrl, username);
	}

	@Test
	void shouldCreateProductTest() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = new ObjectMapper().writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());

		logger.info("Product creation test completed successfully.");
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.productId("123")
				.name("iphone 13")
				.description("Iphone 13 black")
				.price(BigDecimal.valueOf(50000))
				.quantity(10)
				.build();
	}
}
