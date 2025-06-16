package com.exemple.maintenance_system_api.domain.model.dto;

public record FuncionarioCreateDTO (String nome, String cpf, UsuarioRegisterDTO usuarioRegisterDTO, EnderecoCreateDTO enderecoCreateDTO, ContatoCreateDTO contatoCreateDTO) {
}
