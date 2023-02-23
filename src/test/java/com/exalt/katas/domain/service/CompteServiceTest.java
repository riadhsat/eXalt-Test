package com.exalt.katas.domain.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.spi.PersistancePort;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class CompteServiceTest {

  @Mock
  private PersistancePort persistancePort;

  private CompteService compteService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    compteService = new CompteService(persistancePort);
  }

  @Test
  void depositMoney_with_120_solde_is_0_update_solde_to_120() {

    Compte compte = Compte.builder()
        .id("id").solde(0).transactions(new ArrayList<>())
        .build();
    when(persistancePort.findCompte()).thenReturn(compte);
    when(persistancePort.updateCompte(compte)).thenReturn(compte);

    compteService.depositMoney(120);

    assertThat(compte.getSolde()).isEqualTo(120);
  }
}