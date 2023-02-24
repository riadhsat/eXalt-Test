package com.exalt.katas.infrastructure.mapper;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.infrastructure.model.CompteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteToCompteDtoMapper {

  CompteDto toCompteDto(Compte compte);

}
