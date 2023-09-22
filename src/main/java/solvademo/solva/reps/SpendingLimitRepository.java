package solvademo.solva.reps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solvademo.solva.entity.SpendingLimit;

@Repository
public interface SpendingLimitRepository extends JpaRepository<SpendingLimit,Long> {
    SpendingLimit findTopByOrderByDateSetDesc();
}
