package com.exalt.katas.infrastructure.adapter;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.domain.spi.PersistancePort;
import com.exalt.katas.infrastructure.mapper.CompteDtoToCompteMapper;
import com.exalt.katas.infrastructure.repository.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersistanceAdapter implements PersistancePort {

  private final CompteRepository compteRepository;

  private final CompteDtoToCompteMapper compteDtoToCompteMapper;

  @Override
  public Compte findCompte() {
    return compteDtoToCompteMapper.toCompte(compteRepository.findAll().stream().findFirst().orElse(null));

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
