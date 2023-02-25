package com.exalt.katas.application.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

  private int errorCode;
  private String errorMessage;

}
