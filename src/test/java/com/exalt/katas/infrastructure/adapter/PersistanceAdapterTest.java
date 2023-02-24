package com.exalt.katas.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.infrastructure.mapper.CompteDtoToCompteMapper;
import com.exalt.katas.infrastructure.mapper.CompteToCompteDtoMapper;
import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.model.TransactionDto;
import com.exalt.katas.infrastructure.repository.CompteRepository;
import com.exalt.katas.infrastructure.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


class PersistanceAdapterTest {

  @Mock
  private CompteRepository compteRepository;

  @Mock
  private TransactionRepository transactionRepository;

  @Spy
  private final CompteDtoToCompteMapper compteDtoToCompteMapper = Mappers.getMapper(CompteDtoToCompteMapper.class);

  @Spy
  private final CompteToCompteDtoMapper compteToCompteDtoMapper = Mappers.getMapper(CompteToCompteDtoMapper.class);

  private PersistanceAdapter persistanceAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    persistanceAdapter = new PersistanceAdapter(compteRepository, transactionRepository, compteDtoToCompteMapper,
        compteToCompteDtoMapper);
  }

  @Test
  void testFindCompte() {
    CompteDto compte = CompteDto.builder()
        .id(14).solde(120).transactions(new ArrayList<>())
        .build();
    when(compteRepository.findAll()).thenReturn(List.of(compte));

    Compte actual = persistanceAdapter.findCompte();

    assertThat(actual.getSolde()).isEqualTo(120);

  }

  @Test
  void testUpdateCompte() {
    CompteDto compteDto = CompteDto.builder()
        .id(14).solde(120).transactions(new ArrayList<>())
        .build();
    Compte compte = Compte.builder()
        .id("14").solde(120).transactions(new ArrayList<>())
        .build();
    when(compteRepository.save(compteDto)).thenReturn(compteDto);

    Compte actual = persistanceAdapter.updateCompte(compte);

    assertThat(actual).isEqualTo(compte);
  }

  @Test
  void testConsultTransaction() {

    List<TransactionDto> transactions = List.of(TransactionDto.builder()
            .description("test")
            .montant(500)
            .status("valid")
            .creationDate(LocalDateTime.now()).build(),
        TransactionDto.builder()
            .description("test")
            .montant(200)
            .status("valid")
            .creationDate(LocalDateTime.now()).build());

    when(transactionRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(transactions));

    PageTransaction pageTransaction = persistanceAdapter.consultTransaction(0, 10);

    assertThat(pageTransaction.getPage()).isEqualTo(0);
    assertThat(pageTransaction.getPageSize()).isEqualTo(10);
    assertThat(pageTransaction.getTransactions().size()).isEqualTo(2);

  }

}