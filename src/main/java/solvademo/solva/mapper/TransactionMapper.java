package solvademo.solva.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import solvademo.solva.dto.TransactionDTO;
import solvademo.solva.entity.Transaction;

@Component
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO toDTO(Transaction transaction);

    Transaction toEntity(TransactionDTO transactionDTO);
}
