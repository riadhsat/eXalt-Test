package com.exalt.katas.domain.api;

import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;

public interface CompteServicePort {

  void depositMoney(double amount) throws InvalidMontantException;

  void withdrawalMoney(double amount) throws SoldeInsuffisantException, InvalidMontantException;

  double consultBalance();

}
