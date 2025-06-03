package com.exemple.maintenance_system_api.repositories;

import com.exemple.maintenance_system_api.domain.equipment.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
