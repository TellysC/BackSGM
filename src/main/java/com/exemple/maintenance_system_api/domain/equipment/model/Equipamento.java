package com.exemple.maintenance_system_api.domain.equipment.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;


@Getter
@Entity
@Table(name = "equipamentos")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @Where(clause = "deleted_at IS NULL")
    private OffsetDateTime deletedAt;

}
