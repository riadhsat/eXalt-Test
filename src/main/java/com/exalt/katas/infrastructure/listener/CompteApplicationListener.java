package com.exalt.katas.infrastructure.listener;

import com.exalt.katas.infrastructure.model.CompteDto;
import com.exalt.katas.infrastructure.repository.CompteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompteApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

  private final CompteRepository compteRepository;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    compteRepository.save(CompteDto.builder()
        .solde(0)
        .build());
    log.info("Compte créé avec succes; l'application est pret");
  }
}
