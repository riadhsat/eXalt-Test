package com.exalt.katas.domain.api;

import com.exalt.katas.domain.exception.SoldeInsuffisantException;

public interface CompteServicePort {

  void depositMoney(double amount);

  void withdrawalMoney(double amount) throws SoldeInsuffisantException;

}
