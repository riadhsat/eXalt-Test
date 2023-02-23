package com.exalt.katas.domain.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.Status;
import com.exalt.katas.domain.model.Transaction;
import com.exalt.katas.domain.model.TypeTransaction;
import com.exalt.katas.domain.spi.PersistancePort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

  @Test
  void depositMoney_120_when_solde_is_0_then_update_solde_to_120_and_ADD_Transaction_with_status_valid() {

    Compte compte = Compte.builder()
        .id("id").solde(0).transactions(new ArrayList<>())
        .build();
    when(persistancePort.findCompte()).thenReturn(compte);
    when(persistancePort.updateCompte(compte)).thenReturn(compte);

    compteService.depositMoney(120);

    assertThat(compte.getSolde()).isEqualTo(120);
    assertThat(compte.getTransactions()).isNotEmpty();
    assertThat(compte.getTransactions()).usingRecursiveComparison().ignoringFields("creationDate")
        .isEqualTo(List.of(Transaction.builder()
            .typeTransaction(TypeTransaction.DEPOSIT)
            .montant(120)
            .status(Status.VALID)
            .description("transaction validé")
            .build()));
  }

  @Test
  void withdrawalMoney_500_when_solde_is_800_then_update_solde_to_300() {

    Compte compte = Compte.builder()
        .id("id").solde(800).transactions(new ArrayList<>())
        .build();
    when(persistancePort.findCompte()).thenReturn(compte);
    when(persistancePort.updateCompte(compte)).thenReturn(compte);

    compteService.withdrawalMoney(500);

    assertThat(compte.getSolde()).isEqualTo(300);
  }

  @Test
  void withdrawalMoney_500_when_solde_is_800_then_update_solde_to_300_and_ADD_Transaction_with_status_valid() {

    Compte compte = Compte.builder()
        .id("id").solde(800).transactions(new ArrayList<>())
        .build();
    when(persistancePort.findCompte()).thenReturn(compte);
    when(persistancePort.updateCompte(compte)).thenReturn(compte);

    compteService.withdrawalMoney(500);

    assertThat(compte.getSolde()).isEqualTo(300);
    assertThat(compte.getTransactions()).isNotEmpty();
    assertThat(compte.getTransactions()).usingRecursiveComparison().ignoringFields("creationDate")
        .isEqualTo(List.of(Transaction.builder()
            .typeTransaction(TypeTransaction.WITHDRAWAL)
            .montant(500)
            .status(Status.VALID)
            .description("transaction validé")
            .build()));
  }
}