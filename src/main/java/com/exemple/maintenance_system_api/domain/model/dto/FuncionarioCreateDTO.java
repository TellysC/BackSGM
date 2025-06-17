package com.exemple.maintenance_system_api.domain.model.dto;


public record FuncionarioCreateDTO (String nome, String cpf, UsuarioRegisterDTO usuarioRegisterDTO, EnderecoCreateDTO enderecoCreateDTO, ContatoCreateDTO contatoCreateDTO) {

    public FuncionarioCreateDTO {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        if (usuarioRegisterDTO == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (enderecoCreateDTO == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        if (contatoCreateDTO == null) {
            throw new IllegalArgumentException("Contato não pode ser nulo");
        }
    }

}
