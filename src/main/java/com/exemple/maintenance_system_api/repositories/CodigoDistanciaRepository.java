package com.exemple.maintenance_system_api.repositories;

import com.exemple.maintenance_system_api.domain.model.CodigoDistancia;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoDistanciaRepository extends JpaRepository<CodigoDistancia, Long> {
}
