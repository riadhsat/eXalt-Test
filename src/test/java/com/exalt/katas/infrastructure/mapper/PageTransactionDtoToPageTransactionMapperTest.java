package com.exalt.katas.infrastructure.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.infrastructure.model.TransactionDto;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageImpl;

class PageTransactionDtoToPageTransactionMapperTest {

  private final PageTransactionDtoToPageTransactionMapper mapper = Mappers
      .getMapper(PageTransactionDtoToPageTransactionMapper.class);

  @Test
  public void testServiceToRest() {

    List<TransactionDto> transactions = List.of(TransactionDto.builder()
            .description("test")
            .montant(500)
            .typeTransaction("WITHDRAWAL")
            .status("VALID")
            .creationDate(LocalDateTime.now()).build(),
        TransactionDto.builder()
            .description("test")
            .montant(200)
            .typeTransaction("DEPOSIT")
            .status("VALID")
            .creationDate(LocalDateTime.now()).build());

    PageTransaction pageTransaction = mapper.toPageTransaction(new PageImpl<>(transactions));
    assertThat(pageTransaction.getTransactions()).isNotEmpty();
    assertThat(pageTransaction.getTransactions().size()).isEqualTo(2);
    assertThat(pageTransaction.getPage()).isEqualTo(0);
    assertThat(pageTransaction.getTotalTransactions()).isEqualTo(2);
    assertThat(pageTransaction.getPageSize()).isEqualTo(2);
    assertThat(pageTransaction.getTotalPage()).isEqualTo(1);

  }
}