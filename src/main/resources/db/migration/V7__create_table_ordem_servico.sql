CREATE TABLE ordem_servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    status ENUM('ABERTA', 'CONCLUIDA') NOT NULL,
    tipo_manuntencao ENUM('PREVENTIVA', 'CORRETIVA') NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    fk_Funcionarios_Id INT NOT NULL,
    fk_Equipamentos_Id INT NOT NULL,
    FOREIGN KEY (fk_Funcionarios_Id) REFERENCES funcionarios(Id),
    FOREIGN KEY (fk_Equipamentos_Id) REFERENCES equipamentos(Id)
);




