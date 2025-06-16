package com.exemple.maintenance_system_api.domain.model;

import com.exemple.maintenance_system_api.domain.model.enums.TipoManuntencao;
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
@Table(name = "ordem_servico")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE ordem_servico SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String status;

    @Column(name = "tipo_manuntencao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoManuntencao tipoManuntencao;

    @ManyToOne
    @JoinColumn(name = "fk_funcionarios_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "fk_equipamentos_id", nullable = false)
    private Equipamento equipamento;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;
}