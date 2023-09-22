package solvademo.solva.reps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solvademo.solva.entity.CurrencyRate;

import java.time.LocalDate;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    CurrencyRate findTopByCurrencyPairOrderByDateDesc(String currencyPair);

    CurrencyRate findByCurrencyPairAndDate(String currencyPair, LocalDate date);

}
