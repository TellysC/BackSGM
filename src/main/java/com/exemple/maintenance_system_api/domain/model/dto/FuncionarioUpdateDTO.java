package com.exemple.maintenance_system_api.domain.model.dto;


public record FuncionarioUpdateDTO  (String nome, String cpf, ContatoUpdateDTO contatoUpdateDTO, EnderecoUpdateDTO enderecoUpdateDTO, UsuarioRegisterDTO usuarioRegisterDTO) {

    public FuncionarioUpdateDTO {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        if (usuarioRegisterDTO == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (enderecoUpdateDTO == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        if (contatoUpdateDTO == null) {
            throw new IllegalArgumentException("Contato não pode ser nulo");
        }
    }

}