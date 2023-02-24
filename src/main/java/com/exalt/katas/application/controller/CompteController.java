package com.exalt.katas.application.controller;

import com.exalt.katas.application.mapper.ServiceToRestMapper;
import com.exalt.katas.application.response.RestPageTransaction;
import com.exalt.katas.application.response.ResultResponse;
import com.exalt.katas.domain.api.CompteServicePort;
import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
import com.exalt.katas.domain.model.PageTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/compte", produces = {MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"})
@RequiredArgsConstructor
public class CompteController {

  private final CompteServicePort compteServicePort;

  private final ServiceToRestMapper serviceToRestMapper;


  @GetMapping
  public ResponseEntity<ResultResponse> consultSolde() {
    double solde = compteServicePort.consultBalance();
    return ResponseEntity.ok(ResultResponse.builder().message("Votre solde est :" + solde).build());
  }

  @PostMapping("/withdrawal")
  public ResponseEntity<ResultResponse> withdrawalMoney(@RequestParam int amount)
      throws SoldeInsuffisantException, InvalidMontantException {
    compteServicePort.withdrawalMoney(amount);
    return ResponseEntity
        .ok(ResultResponse.builder().message("Votre retrait de montant " + amount + " a ete effectué avec succès")
            .build());
  }

  @PostMapping("/deposit")
  public ResponseEntity<ResultResponse> depositMoney(@RequestParam int amount)
      throws InvalidMontantException {
    compteServicePort.depositMoney(amount);
    return ResponseEntity
        .ok(ResultResponse.builder().message("Votre depot de montant " + amount + " a ete effectué avec succès")
            .build());
  }


  @GetMapping("/transactions")
  public ResponseEntity<RestPageTransaction> getTransactions(
      @RequestParam(value = "page", required = false, defaultValue = "0") int page,
      @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize) {

    PageTransaction transactions = compteServicePort.consultTransaction(page, pageSize);
    return ResponseEntity.ok(serviceToRestMapper.toRestPageTransaction(transactions));
  }
}