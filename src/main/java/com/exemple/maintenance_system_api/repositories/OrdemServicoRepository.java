package com.exemple.maintenance_system_api.repositories;

import com.exemple.maintenance_system_api.domain.model.OrdemServico;
import com.exemple.maintenance_system_api.domain.model.enums.StatusOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByStatus(StatusOrdemServico status);
}
