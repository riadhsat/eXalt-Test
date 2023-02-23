package com.exalt.katas.domain.spi;

import com.exalt.katas.domain.model.Compte;

public interface PersistancePort {

  Compte findCompte();

  Compte updateCompte(Compte compte);
}
