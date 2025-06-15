CREATE TABLE relatorios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    fk_ordem_servico_Id INT NOT NULL,
    FOREIGN KEY (fk_ordem_servico_Id) REFERENCES ordem_servico(Id)
);

