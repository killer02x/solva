package solvademo.solva.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;
import solvademo.solva.dto.CurrencyRateDTO;
import solvademo.solva.entity.CurrencyRate;

@Mapper(componentModel = "spring")
public interface CurrencyRateMapper {

    CurrencyRateDTO toDTO(CurrencyRate currencyRate);

    CurrencyRate toEntity(CurrencyRateDTO currencyRateDTO);
}
