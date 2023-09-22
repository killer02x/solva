package solvademo.solva.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CurrencyRateDTO {

    private Long id;
    private String currencyPair;
    private BigDecimal rate;
    private LocalDate date;
}
