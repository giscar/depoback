package service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
	
	@Bean
	public RestClient getRestClient() {
		return RestClient.create("https://e-beta.sunat.gob.pe:443");
	}

}
