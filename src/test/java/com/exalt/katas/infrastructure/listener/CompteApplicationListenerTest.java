package com.exalt.katas.infrastructure.listener;

import static org.assertj.core.api.Assertions.assertThat;

import com.exalt.katas.infrastructure.repository.CompteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompteApplicationListenerTest {

  @Autowired
  private CompteRepository compteRepository;


  @Test
  void testApplicationReadyEvent() {
    assertThat(compteRepository.count()).isEqualTo(1);
  }

}