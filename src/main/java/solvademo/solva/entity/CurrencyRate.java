package solvademo.solva.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "currency_rates")
@Data
@NoArgsConstructor
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "currency_pair", nullable = false)
    private String currencyPair;

    @Column(nullable = false)
    private BigDecimal rate;

    @Column(nullable = false)
    private LocalDate date;
}

