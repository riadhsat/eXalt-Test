package com.exalt.katas.domain.spi;

import com.exalt.katas.domain.model.Compte;
import com.exalt.katas.domain.model.PageTransaction;

public interface PersistancePort {

  Compte findCompte();

  Compte updateCompte(Compte compte);

  PageTransaction consultTransaction(int page, int pageSize);
}
