package com.exemple.maintenance_system_api.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Table(name = "funcionarios")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE funcionarios SET deleted_at = NOW() WHERE id = ?" )
@Where(clause = "deleted_at IS NULL")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cargo;
    private String cpf;

    @OneToOne
    @JoinColumn(name = "fk_usuario_id", nullable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "fk_endereco_id", nullable = false)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "fk_contato_id", nullable = false)
    private Contato contato;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;
}
