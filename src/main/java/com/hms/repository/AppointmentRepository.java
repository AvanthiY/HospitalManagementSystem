package com.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Page<Appointment> findByDoctorName(String docName, Pageable paging);

}
