package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.enums.Cargo;

public record FuncionarioUpdateDTO  (String nome, Cargo cargo, String cpf, ContatoUpdateDTO contatoUpdateDTO, EnderecoUpdateDTO enderecoUpdateDTO, UsuarioRegisterDTO usuarioUpdateDTO) {
}