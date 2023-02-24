package com.exalt.katas.application.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Builder
public class RestTransaction {

  private int id;
  private double montant;
  private String typeTransaction;
  private LocalDateTime creationDate;
  private String status;
  private String description;

}
