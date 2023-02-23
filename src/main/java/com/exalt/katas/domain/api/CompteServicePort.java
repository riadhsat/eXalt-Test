package com.exalt.katas.domain.api;

import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.domain.model.Transaction;

public interface CompteServicePort {

  void depositMoney(double amount) throws InvalidMontantException;

  void withdrawalMoney(double amount) throws SoldeInsuffisantException, InvalidMontantException;

  double consultBalance();

  PageTransaction consultTransaction(int page, int pageSize);

}
