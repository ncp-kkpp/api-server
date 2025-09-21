package com.kkpp.api_server.entity;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(
    name = "freq_search_ingredient",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_fsi_user_name", columnNames = {"user_id", "name"})
    }
)
public class FreqIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 320)
    private String userId;

    @Column(name = "name", nullable = false, columnDefinition = "text")
    private String name;

    @Column(name = "search_count", nullable = false)
    private Integer searchCount;

    @Column(name = "create_dt", nullable = false, updatable = false)
    private OffsetDateTime createDt;

    @Column(name = "update_dt", nullable = false)
    private OffsetDateTime updateDt;

}