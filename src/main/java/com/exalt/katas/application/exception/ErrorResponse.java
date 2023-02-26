package com.exalt.katas.application.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

  @Schema(description = "Error Code.",example = "4001")
  private int errorCode;
  @Schema(description = "Error message.")
  private String errorMessage;

}
