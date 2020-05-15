package com.example.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class AppConfiguration {
	@Bean
	public LocaleResolver localeResolver()
	{
		SessionLocaleResolver resolver=new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource msg=new ResourceBundleMessageSource();
		msg.setBasename("messages");
		return msg;
	}

}
