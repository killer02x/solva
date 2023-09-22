package solvademo.solva.dto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class TransactionDTO {

    private Long id;
    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    private String expenseCategory;
    private ZonedDateTime datetime;
    private boolean limitExceeded;
    private BigDecimal limitSum;
    private ZonedDateTime limitDatetime;
    private String limitCurrencyShortname;
}
