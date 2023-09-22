package solvademo.solva.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solvademo.solva.entity.CurrencyRate;
import solvademo.solva.reps.CurrencyRateRepository;

import java.time.LocalDate;

@Service
public class CurrencyRateService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    public CurrencyRate getLatestRate(String currencyPair) {
        return currencyRateRepository.findTopByCurrencyPairOrderByDateDesc(currencyPair);
    }

    public CurrencyRate getRateForDate(String currencyPair, LocalDate date) {
        return currencyRateRepository.findByCurrencyPairAndDate(currencyPair, date);
    }

    // Добавьте метод для сохранения курса валюты, если он еще не существует в базе данных
    public void saveRate(CurrencyRate rate) {
        if (currencyRateRepository.findByCurrencyPairAndDate(rate.getCurrencyPair(), rate.getDate()) == null) {
            currencyRateRepository.save(rate);
        }
    }

    // Дополнительные методы по мере необходимости
}

