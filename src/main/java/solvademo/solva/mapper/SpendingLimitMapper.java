package solvademo.solva.mapper;

import org.mapstruct.Mapper;
import solvademo.solva.dto.SpendingLimitDTO;
import solvademo.solva.entity.SpendingLimit;

@Mapper(componentModel = "spring")
public interface SpendingLimitMapper {

    SpendingLimitDTO toDTO(SpendingLimit spendingLimit);

    SpendingLimit toEntity(SpendingLimitDTO spendingLimitDTO);
}
