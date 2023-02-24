package com.exalt.katas.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.exalt.katas.domain.api.CompteServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    when(compteServicePort.consultBalance()).thenReturn(15.0);

    MvcResult result = mockMvc.perform(get("/compte"))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result.getResponse().getContentAsString());
    assertThat(result.getResponse().getContentAsString()).isEqualTo("{\"message\":\"Votre solde est :15.0\"}");

  }

  @Test
  void testWithdrawalMoney() throws Exception {

    MvcResult result = mockMvc.perform(post("/compte/withdrawal")
        .param("amount", "1500"))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result.getResponse().getContentAsString());
    assertThat(result.getResponse().getContentAsString())
        .isEqualTo("{\"message\":\"Votre retrait de montant 1500 a ete effectué avec succès\"}");
    verify(compteServicePort, only()).withdrawalMoney(1500);

  }


}