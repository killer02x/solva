package solvademo.solva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solvademo.solva.entity.CurrencyRate;
import solvademo.solva.sevice.CurrencyRateService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/currency-rates")
public class CurrencyRateController {

    @Autowired
    private CurrencyRateService currencyRateService;

    @GetMapping("/latest/{currencyPair}")
    public CurrencyRate getLatestRate(@PathVariable String currencyPair) {
        return currencyRateService.getLatestRate(currencyPair);
    }


    @GetMapping("/{currencyPair}/{date}")
    public CurrencyRate getRateForDate(@PathVariable String currencyPair, @PathVariable LocalDate date) {
        return currencyRateService.getRateForDate(currencyPair, date);
    }
}
