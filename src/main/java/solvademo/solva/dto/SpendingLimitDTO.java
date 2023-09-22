package solvademo.solva.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SpendingLimitDTO {

    private Long id;
    private BigDecimal amount;
    private String category;
    private LocalDate dateSet;
}
