package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
	
}
