package com.exalt.katas.domain.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
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
  void withdrawalMoney_500_when_solde_is_800_then_update_solde_to_300()
      throws SoldeInsuffisantException, InvalidMontantException {

    Compte compte = Compte.builder()
        .id("id").solde(800).transactions(new ArrayList<>())
        .build();
    when(persistancePort.findCompte()).thenReturn(compte);
    when(persistancePort.updateCompte(compte)).thenReturn(compte);

    compteService.withdrawalMoney(500);

    assertThat(compte.getSolde()).isEqualTo(300);
  }

  @Test
  void withdrawalMoney_500_when_solde_is_800_then_update_solde_to_300_and_ADD_Transaction_with_status_valid()
      throws SoldeInsuffisantException, InvalidMontantException {

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

  @Test
  void withdrawalMoney_500_when_solde_is_400_then_throw_SoldeInsuffisantException()  {

    Compte compte = Compte.builder()
        .id("id").solde(400).transactions(new ArrayList<>())
        .build();
    when(persistancePort.findCompte()).thenReturn(compte);

    SoldeInsuffisantException soldeInsuffisantException = assertThrows(SoldeInsuffisantException.class, ()->  compteService.withdrawalMoney(500));

    assertThat(soldeInsuffisantException.getMessage()).isEqualTo("Votre solde est insuffisant");
  }

  @Test
  void withdrawalMoney_500_when_solde_is_400_then_throw_SoldeInsuffisantException_and_ADD_Transaction_with_status_invalid() {

    Compte compte = Compte.builder()
        .id("id").solde(400).transactions(new ArrayList<>())
        .build();
    when(persistancePort.findCompte()).thenReturn(compte);
    when(persistancePort.updateCompte(compte)).thenReturn(compte);

    SoldeInsuffisantException soldeInsuffisantException = assertThrows(SoldeInsuffisantException.class, ()->  compteService.withdrawalMoney(500));

    assertThat(soldeInsuffisantException.getMessage()).isEqualTo("Votre solde est insuffisant");

    assertThat(compte.getSolde()).isEqualTo(400);
    assertThat(compte.getTransactions()).isNotEmpty();
    assertThat(compte.getTransactions()).usingRecursiveComparison().ignoringFields("creationDate")
        .isEqualTo(List.of(Transaction.builder()
            .typeTransaction(TypeTransaction.WITHDRAWAL)
            .montant(500)
            .status(Status.INVALID)
            .description("transaction invalide : solde insufisant")
            .build()));
  }

  @Test
  void withdrawalMoney_when_montant_invalid_then_throw_invalidMontantException()  {

    InvalidMontantException invalidMontantException = assertThrows(InvalidMontantException.class, ()->  compteService.withdrawalMoney(-25));

    assertThat(invalidMontantException.getMessage()).isEqualTo("Le montant doit d'etre supérieur à 0");
  }

  @Test
  void depositMoney_when_montant_invalid_then_throw_invalidMontantException()  {

    InvalidMontantException invalidMontantException = assertThrows(InvalidMontantException.class, ()->  compteService.depositMoney(-25));

    assertThat(invalidMontantException.getMessage()).isEqualTo("Le montant doit d'etre supérieur à 0");
  }


}