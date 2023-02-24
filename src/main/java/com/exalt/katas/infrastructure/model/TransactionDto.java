package com.exalt.katas.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Builder
@Entity
@Data
@Getter
@Table(name = "Transaction")
public class TransactionDto {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private int id;
  @Column(name = "montant")
  private double montant;
  @Column(name = "type_transaction")
  private String typeTransaction;
  @Column(name = "creation_date")
  private LocalDateTime creationDate;
  @Column(name = "status")
  private String status;
  @Column(name = "description")
  private String description;
  @ManyToOne
  @JoinColumn(name = "compte_id")
  private CompteDto compteDto;
}
