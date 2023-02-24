package com.exalt.katas.infrastructure.mapper;

import static com.exalt.katas.domain.model.Status.VALID;
import static com.exalt.katas.domain.model.TypeTransaction.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.Transaction;
import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.model.TransactionDto;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CompteToCompteDtoMapperTest {

  private final CompteToCompteDtoMapper mapper = Mappers.getMapper(CompteToCompteDtoMapper.class);

  @Test
  public void testServiceToRest() {
    // Given
    final LocalDateTime now = LocalDateTime.now();
    Compte compte = Compte.builder()
        .solde(12)
        .id("1")
        .transactions(List.of(Transaction.builder()
            .id(1)
            .typeTransaction(DEPOSIT)
            .status(VALID)
            .description("valid")
            .montant(50)
            .creationDate(now)
            .build()))
        .build();

    // When
    CompteDto compteDto = mapper.toCompteDto(compte);

    // Then
    assertNotNull(compteDto);
    assertThat(compteDto).isEqualTo(CompteDto.builder()
        .solde(12)
        .id(1)
        .transactions(List.of(TransactionDto.builder()
            .id(1)
            .creationDate(now)
            .status("VALID")
            .typeTransaction("DEPOSIT")
            .description("valid")
            .montant(50)
            .build()))
        .build());
  }

}