package com.exalt.katas.infrastructure.adapter;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.domain.spi.PersistancePort;
import com.exalt.katas.infrastructure.mapper.CompteDtoToCompteMapper;
import com.exalt.katas.infrastructure.mapper.CompteToCompteDtoMapper;
import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.model.TransactionDto;
import com.exalt.katas.infrastructure.repository.CompteRepository;
import com.exalt.katas.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersistanceAdapter implements PersistancePort {

  private final CompteRepository compteRepository;

  private final TransactionRepository transactionRepository;

  private final CompteDtoToCompteMapper compteDtoToCompteMapper;

  private final CompteToCompteDtoMapper compteToCompteDtoMapper;

  @Override
  public Compte findCompte() {
    return compteDtoToCompteMapper.toCompte(compteRepository.findAll().stream().findFirst().orElse(null));

  }

  @Override
  public Compte updateCompte(Compte compte) {
    CompteDto compteDto = compteRepository
        .save(compteToCompteDtoMapper.toCompteDto(compte));
    return compteDtoToCompteMapper.toCompte(compteDto);
  }

  @Override
  public PageTransaction consultTransaction(int page, int pageSize) {
    Page<TransactionDto> pageCurrent = transactionRepository
        .findAll(PageRequest.of(page, pageSize, Direction.DESC, "creationDate"));
    return null;
  }
}
