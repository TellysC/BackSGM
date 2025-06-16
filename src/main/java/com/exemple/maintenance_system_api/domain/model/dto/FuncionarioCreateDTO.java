package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.enums.Cargo;

public record FuncionarioCreateDTO (String nome, Cargo cargo, String cpf, UsuarioRegisterDTO usuarioRegisterDTO, EnderecoCreateDTO enderecoCreateDTO, ContatoCreateDTO contatoCreateDTO) {
}
