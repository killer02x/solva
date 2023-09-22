package solvademo.solva.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solvademo.solva.apiserv.ExternalApiService;
import solvademo.solva.dto.TransactionDTO;
import solvademo.solva.entity.CurrencyRate;
import solvademo.solva.entity.Transaction;
import solvademo.solva.mapper.TransactionMapper;
import solvademo.solva.reps.TransactionRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private ExternalApiService externalApiService;

    public void saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);

        // Получаем текущий курс валюты
        CurrencyRate currentRate = externalApiService.getExchangeRate(transactionDTO.getCurrencyShortname(), "USD", LocalDate.now());

        // Проверяем, не равен ли currentRate null
        if (currentRate == null) {
            // Обрабатываем ситуацию, когда currentRate равен null
            // Например, можно выбросить исключение или вернуть ошибку
            throw new RuntimeException("Не удалось получить курс валюты для " + transactionDTO.getCurrencyShortname() + "/USD");
        }

        // Конвертируем сумму транзакции в USD
        BigDecimal transactionSum = transactionDTO.getSum();
        BigDecimal convertedAmount = transactionSum.divide(currentRate.getRate(), MathContext.DECIMAL64);

        BigDecimal currentLimit = BigDecimal.valueOf(1000);
        if (convertedAmount.compareTo(currentLimit) > 0) {
            transaction.setLimitExceeded(true);
        }

        transactionRepository.save(transaction);
    }

    public List<TransactionDTO> getTransactionsExceedingLimit() {
        List<Transaction> transactions = transactionRepository.findByLimitExceededTrue();
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
