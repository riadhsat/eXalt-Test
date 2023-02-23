package com.exalt.katas.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.exalt.katas.application.response.RestPageTransaction;
import com.exalt.katas.application.response.RestTransaction;
import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.domain.model.Status;
import com.exalt.katas.domain.model.Transaction;
import com.exalt.katas.domain.model.TypeTransaction;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ServiceToRestMapperTest {

  private final ServiceToRestMapper mapper = Mappers.getMapper(ServiceToRestMapper.class);

  @Test
  public void testServiceToRest() {
    // Given
    final LocalDateTime now = LocalDateTime.now();
    PageTransaction pageTransaction = PageTransaction.builder()
        .totalPage(3)
        .pageSize(10)
        .page(0)
        .totalTransactions(1)
        .transactions(List.of(Transaction.builder()
            .id(1)
            .typeTransaction(TypeTransaction.DEPOSIT)
            .status(Status.VALID)
            .description("valid")
            .montant(50)
            .creationDate(now)
            .build()))
        .build();

    // When
    RestPageTransaction restPageTransaction = mapper.toRestPageTransaction(pageTransaction);

    // Then
    assertNotNull(restPageTransaction);
    assertThat(restPageTransaction).isEqualTo(RestPageTransaction.builder()
        .page(0)
        .totalPage(3)
        .pageSize(10)
        .totalTransactions(1)
        .transactions(List.of(RestTransaction.builder()
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