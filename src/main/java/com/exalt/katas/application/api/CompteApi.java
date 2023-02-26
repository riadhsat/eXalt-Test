package com.exalt.katas.application.api;

import com.exalt.katas.application.exception.ErrorResponse;
import com.exalt.katas.application.response.RestPageTransaction;
import com.exalt.katas.application.response.ResultResponse;
import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


@ApiResponses(value = {
    @ApiResponse(responseCode = "500", description = "Internal Server Error.",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(value = "{\n"
                + "  \"errorCode\": 500,\n"
                + "  \"errorMessage\": \"Internal ERROR\"\n"
                + "}"))})
})
public interface CompteApi {

  @Operation(tags = "Compte Information", description = "Consultation de solde ")
  @ApiResponses(value = @ApiResponse(responseCode = "200", description = "ok.", content = {
      @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ResultResponse.class))}))
  ResponseEntity<ResultResponse> consultSolde();

  @Operation(tags = "Compte Operation", description = "Retrait d'un montant")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "ok.", content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResultResponse.class))}),
      @ApiResponse(responseCode = "400",
          description = "BadRequest : lorsque le montant de la transaction est invalide",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class),
              examples = {
                  @ExampleObject(name = "Montant invalide",
                      value = "{\n  \"errorCode\": 4000,\n  \"errorMessage\": \"Le montant doit être supérieur à 0\"\n}"),
                  @ExampleObject(name = "Solde insuffisant",
                      value = "{\n  \"errorCode\": 4001,\n  \"errorMessage\": \"Votre solde est insuffisant\"\n}")
              }
          )
      )
  })
  ResponseEntity<ResultResponse> withdrawalMoney(
      @Parameter(description = "le montant a retirer")
      @RequestParam int amount) throws SoldeInsuffisantException, InvalidMontantException;

  @Operation(tags = "Compte Operation", description = "Deposer un montant")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "ok.", content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ResultResponse.class))}),
      @ApiResponse(responseCode = "400",
          description = "BadRequest : lorsque le montant de la transaction est invalide",
          content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class),
              examples = @ExampleObject(value = ("{\n"
                  + "  \"errorCode\": 4000,\n"
                  + "  \"errorMessage\": \"Le montant doit être supérieur à 0\"\n"
                  + "}")))})})
  ResponseEntity<ResultResponse> depositMoney(
      @Parameter(description = "le montant a deposer")
      @RequestParam int amount) throws InvalidMontantException;

  @Operation(tags = "Compte Information", description = "Consultation des Transactions ")
  @ApiResponses(value = @ApiResponse(responseCode = "200", description = "ok.", content = {
      @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = RestPageTransaction.class))}))
  ResponseEntity<RestPageTransaction> getTransactions(
      @Parameter(description = "le numero de page courant")
      @RequestParam(value = "page", required = false, defaultValue = "0")
          int page,
      @Parameter(description = "le nombre d'element par page ")
      @RequestParam(value = "page_size", required = false, defaultValue = "10")
          int pageSize);
}
