package com.exalt.katas.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultResponse {

  @Schema(description = "message de confirmation de l'operation")
  private String message;

}
