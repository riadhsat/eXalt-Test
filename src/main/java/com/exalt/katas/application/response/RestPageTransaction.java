package com.exalt.katas.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
@Getter
public class RestPageTransaction {

  @Schema(description = "List des transactions")
  List<RestTransaction> transactions;
  @Schema(description = "le numero de la page")
  int page;
  @Schema(description = "le nombre  de transaction par page")
  int pageSize;
  @Schema(description = "le nombre des pages")
  int totalPage;
  @Schema(description = "le nombre tous les transactions")
  int totalTransactions;

}
