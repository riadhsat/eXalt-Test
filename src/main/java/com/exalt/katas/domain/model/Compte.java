package com.exalt.katas.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@EqualsAndHashCode
@Setter
public class Compte {

  private String id;
  private double solde;
  private List<Transaction> transactions;
}
