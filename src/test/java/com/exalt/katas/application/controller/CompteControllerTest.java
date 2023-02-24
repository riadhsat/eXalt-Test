package com.exalt.katas.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.exalt.katas.domain.api.CompteServicePort;
import com.exalt.katas.domain.exception.InvalidMontantException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    verify(compteServicePort, only()).withdrawalMoney(1500);
    assertThat(result.getResponse().getContentAsString())
        .isEqualTo("{\"message\":\"Votre retrait de montant 1500 a ete effectué avec succès\"}");
  }

  @ParameterizedTest
  @ValueSource(strings = {"0", "-1", "-10"})
  void testWithdrawalMoney_when_amount_invalid_then_throw_invalidMontantException(String amount) throws Exception {

    doThrow(new InvalidMontantException()).when(compteServicePort).withdrawalMoney(anyDouble());
    Exception invalidMontantException = assertThrows(Exception.class,
        () -> mockMvc.perform(post("/compte/withdrawal")
            .param("amount", amount))
            .andReturn());
    assertEquals("Le montant doit être supérieur à 0", invalidMontantException.getCause().getMessage());
  }
}