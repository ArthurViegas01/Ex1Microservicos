package com.engsoft2.currencyconversionservice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyCollectorController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    private List<Information> informationList = new ArrayList<>();

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyCollector calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyCollector> responseEntity =
                new RestTemplate().getForEntity(
                        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyCollector.class,
                        uriVariables);

                        CurrencyCollector currencyConversion = responseEntity.getBody();

        // Coletar informações da consulta
        Information information = new Information();
        information.setCurrency(currencyConversion.getFrom() + " to " + currencyConversion.getTo());
        information.setQuantity(quantity);
        information.setValue(currencyConversion.getConversionMultiple());
        information.setTimestamp(LocalDateTime.now());

        informationList.add(information);

        return new CurrencyCollector(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " " + "rest template"
        );
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyCollector calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
            CurrencyCollector currencyConversion = proxy.retrieveExchangeValue(from, to);

        // Coletar informações da consulta
        Information information = new Information();
        information.setCurrency(currencyConversion.getFrom() + " to " + currencyConversion.getTo());
        information.setQuantity(quantity);
        information.setValue(currencyConversion.getConversionMultiple());
        information.setTimestamp(LocalDateTime.now());

        informationList.add(information);

        return new CurrencyCollector(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " " + "feign"
        );
    }

    @GetMapping("/information")
    public List<Information> getAllInformation() {
        return informationList;
    }
}