package com.example.demo.infrastructure.configurations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	@Bean
	ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Converter<String, Date> toDate = new AbstractConverter<String, Date>() {
			@Override
			protected Date convert(String source) {
				try {
					return new SimpleDateFormat(DATE_FORMAT).parse(source);
				} catch (Exception e) {
					return null;
				}
			}
		};

		Converter<Date, String> toString = new AbstractConverter<Date, String>() {
			@Override
			protected String convert(Date source) {
				return new SimpleDateFormat(DATE_FORMAT).format(source);
			}
		};

		modelMapper.addConverter(toDate);
		modelMapper.addConverter(toString);

		return modelMapper;
	}
}
