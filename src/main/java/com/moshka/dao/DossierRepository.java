package com.moshka.dao;

import com.moshka.model.Dossier;
import org.springframework.data.repository.CrudRepository;

public interface DossierRepository  extends CrudRepository<Dossier,Long> {
}
