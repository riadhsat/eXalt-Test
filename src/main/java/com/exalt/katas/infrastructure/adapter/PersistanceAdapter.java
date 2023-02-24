package com.exalt.katas.infrastructure.adapter;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.domain.spi.PersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersistanceAdapter implements PersistancePort {

  @Override
  public Compte findCompte() {
    return null;
  }

  @Override
  public Compte updateCompte(Compte compte) {
    return null;
  }

  @Override
  public PageTransaction consultTransaction(int page, int pageSize) {
    return null;
  }
}
