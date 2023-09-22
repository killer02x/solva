package solvademo.solva.apiserv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import solvademo.solva.entity.CurrencyRate;
import solvademo.solva.sevice.CurrencyRateService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ExternalApiService {

    @Autowired
    private CurrencyRateService currencyRateService;

    private WebClient webClient;
    @Autowired
    public ExternalApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.twelvedata.com").build();
    }


    private final String API_KEY="3b54f016e15f431fbe49c1683a07b29f";

    public CurrencyRate getExchangeRate(String fromCurrency, String toCurrency, LocalDate date) {
        String currencyPair = fromCurrency + "," + toCurrency;

        // Проверяем, существует ли курс в базе данных
        CurrencyRate currencyRate = currencyRateService.getRateForDate(currencyPair, date);
        if (currencyRate != null) {
            return currencyRate;
        }

        // Если курса нет в базе данных, получаем его с внешнего API
        var response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/time_series")
                        .queryParam("symbol", currencyPair)
                        .queryParam("interval", "1day")
                        .queryParam("apikey", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(TwelveDataResponse.class)
                .block();

        // Получаем CurrencyData из response
        TwelveDataResponse.CurrencyData currencyData = response.getCurrencyData(toCurrency);

        // Проверяем ответ API
        if (currencyData == null || currencyData.getValues() == null || currencyData.getValues().isEmpty()) {
            throw new RuntimeException("Не удалось получить курс валюты для " + fromCurrency + "/" + toCurrency);
        }

        var latestValue = currencyData.getValues().get(0);
        BigDecimal rate = new BigDecimal(latestValue.getClose());
        LocalDate responseDate = LocalDate.parse(latestValue.getDatetime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime responseDateTime = responseDate.atStartOfDay(); // или используйте другое время, если у вас есть конкретное время

        // Проверяем, совпадает ли дата ответа с запрашиваемой датой
//        if (!responseDate.equals(date)) {
//            throw new RuntimeException("Дата курса валюты не совпадает с запрашиваемой датой");
//        }

        // Сохраняем полученный курс в базе данных
        currencyRate = new CurrencyRate();
        currencyRate.setCurrencyPair(currencyPair);
        currencyRate.setRate(rate);
        currencyRate.setDate(responseDate);
        currencyRateService.saveRate(currencyRate);

        return currencyRate;
    }
}
