package com.exalt.katas.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageTransaction {

  private List<Transaction> transactions;
  private int page;
  private int pageSize;
  private int totalPage;
  private int totalTransactions;

}
