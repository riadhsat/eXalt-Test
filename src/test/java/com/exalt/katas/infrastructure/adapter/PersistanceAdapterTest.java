package com.exalt.katas.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.repository.CompteRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class PersistanceAdapterTest {

  @Mock
  private CompteRepository compteRepository;

  private PersistanceAdapter persistanceAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    persistanceAdapter = new PersistanceAdapter(compteRepository);
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
}