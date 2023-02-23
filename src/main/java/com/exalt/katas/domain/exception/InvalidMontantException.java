package com.exalt.katas.domain.exception;

public class InvalidMontantException extends Exception{

  public InvalidMontantException() {
    super("Le montant doit être supérieur à 0");
  }
}
