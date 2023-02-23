package com.exalt.katas.application.response;

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

  List<RestTransaction> transactions;
  int page;
  int pageSize;
  int totalPage;
  int totalTransactions;

}
