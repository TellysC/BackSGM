CREATE TABLE ordem_servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    status VARCHAR(60) NOT NULL,
    tipo_manuntencao ENUM('PREVENTIVA', 'CORRETIVA') NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    fk_Funcionario_Id INT NOT NULL,
    fk_Equipamento_Id INT NOT NULL,
    FOREIGN KEY (fk_Funcionario_Id) REFERENCES funcionarios(Id),
    FOREIGN KEY (fk_Equipamento_Id) REFERENCES equipamentos(Id)
);




