package com.exalt.katas.domain.service;

import com.exalt.katas.domain.api.CompteServicePort;
import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.Status;
import com.exalt.katas.domain.model.Transaction;
import com.exalt.katas.domain.model.TypeTransaction;
import com.exalt.katas.domain.spi.PersistancePort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompteService implements CompteServicePort {

  private final PersistancePort persistancePort;

  @Override
  public void depositMoney(double amount) {
    Compte compte = persistancePort.findCompte();
    compte.setSolde(compte.getSolde() + amount);
    compte.getTransactions().add(Transaction.builder()
        .typeTransaction(TypeTransaction.DEPOSIT)
        .montant(120)
        .status(Status.VALID)
        .description("transaction validé")
        .creationDate(LocalDateTime.now())
        .build());
    persistancePort.updateCompte(compte);
  }

  @Override
  public void withdrawalMoney(double amount) {
    Compte compte = persistancePort.findCompte();
    compte.setSolde(compte.getSolde() - amount);
    persistancePort.updateCompte(compte);
  }
}