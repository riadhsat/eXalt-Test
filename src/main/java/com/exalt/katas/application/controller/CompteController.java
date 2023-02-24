package com.exalt.katas.application.controller;

import com.exalt.katas.application.response.ResultResponse;
import com.exalt.katas.domain.api.CompteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/compte", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class CompteController {

  private final CompteServicePort compteServicePort;


  @GetMapping
  public ResponseEntity<ResultResponse> consultSolde(){
    double solde = compteServicePort.consultBalance();
    return ResponseEntity.ok(ResultResponse.builder().message("Votre solde est :"+solde).build());
  }

  @PostMapping("/withdrawal")
  public ResponseEntity<ResultResponse> withdrawalMoney(){
    return null;
  }

}
