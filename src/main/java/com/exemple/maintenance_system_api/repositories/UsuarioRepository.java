package com.exemple.maintenance_system_api.repositories;

import com.exemple.maintenance_system_api.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

   UserDetails findByEmail(String email);
}
