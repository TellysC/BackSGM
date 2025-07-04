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
@Table(name = "contatos")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE contatos SET deleted_at = NOW() WHERE id = ?" )
@Where(clause = "deleted_at IS NULL")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String telefone;
    private String celular;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_codigo_distacia_id", nullable = false)
    private CodigoDistancia codigoDistancia;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;
}
