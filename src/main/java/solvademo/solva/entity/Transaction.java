package solvademo.solva.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from", length = 10, nullable = false)
    private String accountFrom;

    @Column(name = "account_to", length = 10, nullable = false)
    private String accountTo;

    @Column(name = "currency_shortname", length = 3, nullable = false)
    private String currencyShortname;

    @Column(nullable = false)
    private BigDecimal sum;

    @Column(name = "expense_category", nullable = false)
    private String expenseCategory;

    @Column(nullable = false)
    private ZonedDateTime datetime;

    @Column(name = "limit_exceeded", nullable = false)
    private boolean limitExceeded;
}

