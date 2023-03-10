package com.exalt.katas.application.controller;

import static com.exalt.katas.domain.model.Status.VALID;
import static com.exalt.katas.domain.model.TypeTransaction.WITHDRAWAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.exalt.katas.application.mapper.ServiceToRestMapper;
import com.exalt.katas.domain.api.CompteServicePort;
import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.domain.model.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

class CompteControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CompteServicePort compteServicePort;

  @Spy
  private ServiceToRestMapper serviceToRestMapper = Mappers.getMapper(ServiceToRestMapper.class);

  @InjectMocks
  private CompteController compteController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    compteController = new CompteController(compteServicePort, serviceToRestMapper);
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
        .isEqualTo("{\"message\":\"Votre retrait de montant 1500 a ete effectu?? avec succ??s\"}");
  }



  @Test
  void testWithdrawalMoney_when_solde_inssufisant_then_throw_SoldeInsuffisantException() throws Exception {

    doThrow(new SoldeInsuffisantException()).when(compteServicePort).withdrawalMoney(anyDouble());
    Exception soldeInsuffisantException = assertThrows(Exception.class,
        () -> mockMvc.perform(post("/compte/withdrawal")
            .param("amount", "1500"))
            .andReturn());
    assertEquals("Votre solde est insuffisant", soldeInsuffisantException.getCause().getMessage());
  }

  @Test
  void testDepositMoney() throws Exception {

    MvcResult result = mockMvc.perform(post("/compte/deposit")
        .param("amount", "1500"))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result.getResponse().getContentAsString());
    verify(compteServicePort, only()).depositMoney(1500);
    assertThat(result.getResponse().getContentAsString())
        .isEqualTo("{\"message\":\"Votre depot de montant 1500 a ete effectu?? avec succ??s\"}");
  }

  @ParameterizedTest
  @MethodSource("useCases")
  void testWithdrawalOrDepositMoney_when_amount_invalid_then_throw_Exception(String url,String amount) throws Exception {

    doThrow(new InvalidMontantException()).when(compteServicePort).withdrawalMoney(anyDouble());
    doThrow(new InvalidMontantException()).when(compteServicePort).depositMoney(anyDouble());
    Exception exception = assertThrows(Exception.class,
        () -> mockMvc.perform(post(url)
            .param("amount", amount))
            .andReturn());
    assertEquals("Le montant doit ??tre sup??rieur ?? 0", exception.getCause().getMessage());

  }

  private static Stream<Arguments> useCases() {
    return Stream.of(
        Arguments.of("/compte/deposit", "0"),
        Arguments.of("/compte/deposit", "-1"),
        Arguments.of("/compte/deposit", "-10"),
        Arguments.of("/compte/withdrawal", "0"),
        Arguments.of("/compte/withdrawal", "-1"),
        Arguments.of("/compte/withdrawal", "-10")
    );
  }

  @Test
  void testTransactions() throws Exception {

    when(compteServicePort.consultTransaction(0,10))
        .thenReturn(PageTransaction.builder()
            .pageSize(10).page(0).totalPage(1).totalTransactions(1)
            .transactions(List.of(Transaction.builder().typeTransaction(WITHDRAWAL)
                .montant(1500).description("succes").status(VALID).creationDate(LocalDateTime.now()).build()))
        .build());

    mockMvc.perform(get("/compte/transactions")
        .param("page", "0")
        .param("page_size", "10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.page", Matchers.is(0)))
        .andExpect(jsonPath("$.pageSize", Matchers.is(10)));
  }
}