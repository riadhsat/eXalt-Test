package com.exalt.katas.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.model.TransactionDto;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TransactionRepositoryTest {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private TestEntityManager entityManager;


  @Test
  public void testfindTransactionsByPage() {

    CompteDto compte = CompteDto.builder()
        .solde(1500).transactions(List.of(TransactionDto.builder()
                .description("test")
                .montant(500)
                .status("valid")
                .creationDate(LocalDateTime.now()).build(),
            TransactionDto.builder()
                .description("test")
                .montant(200)
                .status("valid")
                .creationDate(LocalDateTime.now()).build()
        )).build();
    entityManager.persist(compte);
    entityManager.flush();

    Page<TransactionDto> transactions = transactionRepository.findAll(PageRequest.of(0, 1, DESC, "creationDate"));

    assertThat(transactions.getContent()).hasSize(1);
    assertThat(transactions.getContent().get(0)).isEqualTo(compte.getTransactions().get(0));
  }
}