/* brModelo: */

CREATE TABLE contatos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    telefone VARCHAR(8) NULL,
    celular VARCHAR(9) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    fk_Codigo_Distacia_Id INT NOT NULL,
    FOREIGN KEY (fk_Codigo_Distacia_Id) REFERENCES codigo_distancia (id)

);
