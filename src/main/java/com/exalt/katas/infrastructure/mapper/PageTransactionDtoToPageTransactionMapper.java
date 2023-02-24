package com.exalt.katas.infrastructure.mapper;

import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.domain.model.Transaction;
import com.exalt.katas.infrastructure.model.TransactionDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageTransactionDtoToPageTransactionMapper {

  default PageTransaction toPageTransaction(Page<TransactionDto> pageTransactionDto) {
    return PageTransaction.builder()
        .transactions(mapTransactions(pageTransactionDto.getContent()))
        .page(pageTransactionDto.getNumber())
        .pageSize(pageTransactionDto.getSize())
        .totalPage(pageTransactionDto.getTotalPages())
        .totalTransactions(Math.toIntExact(pageTransactionDto.getTotalElements()))
        .build();
  }

  List<Transaction> mapTransactions(List<TransactionDto> transactions);

}
