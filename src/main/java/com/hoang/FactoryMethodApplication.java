package com.hoang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Proxy;

@SpringBootApplication
public class FactoryMethodApplication {

	private static final Logger logger = LoggerFactory.getLogger(FactoryMethodApplication.class);

	@Value("${PROXY.IFI}")
	public String PROXY_IFI;

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		if (PROXY_IFI.equalsIgnoreCase("true")) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.225.3.1", 3128));
			clientHttpRequestFactory.setProxy(proxy);
		}
		return new RestTemplate(clientHttpRequestFactory);
	}

	@PostConstruct
	public void postConstruct() {
	}

	public static void main(String[] args) {
		SpringApplication.run(FactoryMethodApplication.class, args);
	}
}
