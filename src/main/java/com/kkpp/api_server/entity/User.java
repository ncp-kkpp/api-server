package com.kkpp.api_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login_id", nullable = false, unique = true, length = 320)
	private String loginId;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "password_hash", nullable = false, columnDefinition = "TEXT")
	private String passwordHash;

	@Column(name = "del_yn", nullable = false, length = 1)
	private String delYn = "N"; // "Y" 또는 "N"

	@Column(name = "created_dt", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT now()")
	private OffsetDateTime createdDt;

	@Column(name = "updated_dt", nullable = false, columnDefinition = "TIMESTAMPTZ DEFAULT now()")
	private OffsetDateTime updatedDt;
	
	@PrePersist
	protected void onCreate() {
	OffsetDateTime now = OffsetDateTime.now();
	if (createdDt == null) createdDt = now;
	if (updatedDt == null) updatedDt = now;
	if (delYn == null) delYn = "N";
	}

	@PreUpdate
	protected void onUpdate() {
	updatedDt = OffsetDateTime.now();
	}
}
