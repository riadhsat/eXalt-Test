package com.exalt.katas.infrastructure.mapper;

import com.exalt.katas.domain.model.PageTransaction;
import com.exalt.katas.infrastructure.model.TransactionDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageTransactionDtoToPageTransactionMapper {

  PageTransaction toPageTransaction(Page<TransactionDto> pageTransactionDto);

}
