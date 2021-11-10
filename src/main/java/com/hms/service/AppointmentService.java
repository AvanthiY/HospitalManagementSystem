package com.hms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.model.dto.AppointmentDTO;

@Service
public interface AppointmentService {
	
	AppointmentDTO createAppt(LocalDateTime apptdate, int apptType, Long docId, Long patientId);

	List<AppointmentDTO> getAllAppointments(String docName, int page, int size);

	AppointmentDTO getById(Long apptId);

	void deleteAppointment(Long apptId);

	AppointmentDTO updateAppointment(Long apptId, AppointmentDTO apptDto);

}
