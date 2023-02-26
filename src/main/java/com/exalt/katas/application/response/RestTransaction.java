package com.exalt.katas.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
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

  @Schema(description = "id de la transaction")
  private int id;
  @Schema(description = "le montant de la transaction")
  private double montant;
  @Schema(description = "type de transaction", example = "Deposit")
  private String typeTransaction;
  @Schema(description = "date de transaction")
  private LocalDateTime creationDate;
  @Schema(description = "Status de transaction", example = "VALID")
  private String status;
  @Schema(description = "Description ou remarque sur la transaction")
  private String description;

}
