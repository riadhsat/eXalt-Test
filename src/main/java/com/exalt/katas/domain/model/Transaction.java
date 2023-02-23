package com.exalt.katas.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Transaction {

  private int id;
  private double montant;
  private TypeTransaction typeTransaction;
  private LocalDateTime creationDate;
  private Status status;
  private String description;
}
