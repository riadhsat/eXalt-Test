package com.exalt.katas.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@EqualsAndHashCode
@Getter
public class Transaction {

  private int id;
  private double montant;
  private TypeTransaction typeTransaction;
  private LocalDateTime creationDate;
  private Status status;
  private String description;
}
