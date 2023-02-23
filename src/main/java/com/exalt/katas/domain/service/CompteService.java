package com.exalt.katas.domain.service;

import com.exalt.katas.domain.api.CompteServicePort;
import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
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
        .montant(amount)
        .status(Status.VALID)
        .description("transaction validé")
        .creationDate(LocalDateTime.now())
        .build());
    persistancePort.updateCompte(compte);
  }

  @Override
  public void withdrawalMoney(double amount) throws SoldeInsuffisantException, InvalidMontantException {
    if(amount <= 0){
      throw new InvalidMontantException();
    }
    Compte compte = persistancePort.findCompte();
    if(compte.getSolde() < amount){
      compte.getTransactions().add(Transaction.builder()
          .typeTransaction(TypeTransaction.WITHDRAWAL)
          .montant(amount)
          .status(Status.INVALID)
          .description("transaction invalide : solde insufisant")
          .creationDate(LocalDateTime.now())
          .build());
      persistancePort.updateCompte(compte);
      throw new SoldeInsuffisantException();
    }
    compte.setSolde(compte.getSolde() - amount);
    compte.getTransactions().add(Transaction.builder()
        .typeTransaction(TypeTransaction.WITHDRAWAL)
        .montant(amount)
        .status(Status.VALID)
        .description("transaction validé")
        .creationDate(LocalDateTime.now())
        .build());
    persistancePort.updateCompte(compte);
  }
}
