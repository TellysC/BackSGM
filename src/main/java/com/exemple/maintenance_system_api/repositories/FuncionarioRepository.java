package com.exemple.maintenance_system_api.repositories;

import com.exemple.maintenance_system_api.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByUsuarioId(Long usuarioId);
}
