package com.exemple.maintenance_system_api.domain.model.dto;

public record FuncionarioUpdateDTO  (String nome, String cpf, ContatoUpdateDTO contatoUpdateDTO, EnderecoUpdateDTO enderecoUpdateDTO, UsuarioRegisterDTO usuarioRegisterDTO) {
}