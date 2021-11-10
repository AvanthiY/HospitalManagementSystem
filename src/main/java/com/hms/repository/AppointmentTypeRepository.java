package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.model.AppointmentType;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Integer>{

}
