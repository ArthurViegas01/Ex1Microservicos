package com.engsoft2.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class CurrencyCollectorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyCollectorServiceApplication.class,args);

	}
}