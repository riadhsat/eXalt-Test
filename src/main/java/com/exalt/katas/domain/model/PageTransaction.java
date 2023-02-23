package com.exalt.katas.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageTransaction {

  List<Transaction> transactions;
  int page;
  int pageSize;
  int totalPage;
  int totalTransactions;

}
