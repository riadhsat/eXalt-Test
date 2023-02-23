package com.exalt.katas.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Compte {

  private String id;
  private double solde;
  private List<Transaction> transactions;
}
