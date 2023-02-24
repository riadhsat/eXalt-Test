package com.exalt.katas.application.controller;

import com.exalt.katas.domain.api.CompteServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class CompteControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CompteServicePort compteServicePort;

  @InjectMocks
  private CompteController compteController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    compteController = new CompteController(compteServicePort);
    this.mockMvc = standaloneSetup(compteController).build();
  }

  @Test
  void testConsultSolde() throws Exception {

    MvcResult result =mockMvc.perform(get("/compte")).andExpect(status().isOk()).andReturn();

    assertNotNull(result.getResponse().getContentAsString());

  }


}