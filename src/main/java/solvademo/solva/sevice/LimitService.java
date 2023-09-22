package solvademo.solva.sevice;

import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solvademo.solva.entity.SpendingLimit;
import solvademo.solva.reps.SpendingLimitRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class LimitService {

    @Autowired
    private SpendingLimitRepository limitRepository;

    public void setNewLimit(double amount) {
        SpendingLimit limit = new SpendingLimit();
        limit.setAmount(BigDecimal.valueOf(amount));
        limit.setDateSet(LocalDate.from(LocalDateTime.now()));
        limitRepository.save(limit);
    }

    public SpendingLimit getCurrentLimit() {
        return limitRepository.findTopByOrderByDateSetDesc();
    }
}

