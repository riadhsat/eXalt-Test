package com.exalt.katas.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.infrastructure.mapper.CompteDtoToCompteMapper;
import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.repository.CompteRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


class PersistanceAdapterTest {

  @Mock
  private CompteRepository compteRepository;

  @Spy
  private final CompteDtoToCompteMapper compteDtoToCompteMapper = Mappers.getMapper(CompteDtoToCompteMapper.class);

  private PersistanceAdapter persistanceAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    persistanceAdapter = new PersistanceAdapter(compteRepository, compteDtoToCompteMapper);
  }

  @Test
  void testFindCompte() {
    CompteDto compte = CompteDto.builder()
        .id(14).solde(120).transactions(new ArrayList<>())
        .build();
    when(compteRepository.findAll()).thenReturn(List.of(compte));

    Compte actual = persistanceAdapter.findCompte();

    assertThat(actual.getSolde()).isEqualTo(120);

  }

  @Test
  void testUpdateCompte() {
    CompteDto compteDto = CompteDto.builder()
        .id(14).solde(120).transactions(new ArrayList<>())
        .build();
    Compte compte = Compte.builder()
        .id("14").solde(120).transactions(new ArrayList<>())
        .build();
    when(compteRepository.save(compteDto)).thenReturn(compteDto);

    Compte actual = persistanceAdapter.updateCompte(compte);

    assertThat(actual).isEqualTo(compte);

  }
}