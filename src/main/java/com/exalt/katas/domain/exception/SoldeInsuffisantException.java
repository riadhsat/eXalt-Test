package com.exalt.katas.domain.exception;

public class SoldeInsuffisantException extends Exception{

  public SoldeInsuffisantException() {
    super("Votre solde est insuffisant");
  }
}
