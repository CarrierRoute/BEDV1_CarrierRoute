package com.grepp.carrierroute;

import com.grepp.carrierroute.util.ExceptionMessageUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarrierRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrierRouteApplication.class, args);
	}

	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("exceptions");
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	@Bean
	public MessageSourceAccessor messageSourceAccessor() {
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource());
		ExceptionMessageUtils.setExceptionMessageUtils(messageSourceAccessor);

		return messageSourceAccessor;
	}

}
