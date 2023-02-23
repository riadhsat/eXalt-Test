package com.exalt.katas.application.mapper;

import com.exalt.katas.application.response.RestPageTransaction;
import com.exalt.katas.domain.model.PageTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceToRestMapper {

  RestPageTransaction toRestPageTransaction(PageTransaction pageTransaction);

}
