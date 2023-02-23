/**
 * Copyright (c) ${YEAR} Carrefour, All rights reserved.
 * <p>
 * 9fbef606107a605d69c0edbcd8029e5d
 */
package com.exalt.katas.domain.service;

import com.exalt.katas.domain.api.CompteServicePort;
import com.exalt.katas.domain.spi.PersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompteService implements CompteServicePort {

  private final PersistancePort persistancePort;

  @Override
  public void depositMoney(double amount) {

  }
}
