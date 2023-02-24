package com.exalt.katas.application.api;

import com.exalt.katas.application.response.RestPageTransaction;
import com.exalt.katas.application.response.ResultResponse;
import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
import org.springframework.http.ResponseEntity;

public interface CompteApi {

  ResponseEntity<ResultResponse> consultSolde();

  ResponseEntity<ResultResponse> withdrawalMoney(int amount) throws SoldeInsuffisantException, InvalidMontantException;

  ResponseEntity<ResultResponse> depositMoney(int amount) throws InvalidMontantException;

  ResponseEntity<RestPageTransaction> getTransactions(int page, int pageSize);

}
