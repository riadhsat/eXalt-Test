package com.exalt.katas.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.model.TransactionDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CompteRepositoryTest {

  @Autowired
  private CompteRepository compteRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testfindCompte() {

    CompteDto compte = CompteDto.builder()
        .solde(1500).transactions(List.of(TransactionDto.builder()
            .description("test")
            .status("valid")
            .creationDate(LocalDateTime.now()).build())).build();
    entityManager.persist(compte);
    entityManager.flush();


    Optional<CompteDto> savedCompte = compteRepository.findAll().stream().findFirst();
    assertThat(savedCompte).isPresent();
    assertEquals(compte.getSolde(),savedCompte.get().getSolde());
    assertEquals(compte.getTransactions(),savedCompte.get().getTransactions());
    assertEquals(compte.getTransactions().size(),savedCompte.get().getTransactions().size());
  }

}