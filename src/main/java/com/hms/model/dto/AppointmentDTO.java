package com.hms.model.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {

	private LocalDateTime date;
	
	private Long patientId;
	
	private Long doctorId;
	
	private int apptType;
	
	public AppointmentDTO() {
		
	}

	public AppointmentDTO(LocalDateTime date, Long patientId, Long doctorId, int apptType) {
		super();
		this.date = date;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.apptType = apptType;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public int getApptType() {
		return apptType;
	}

	public void setApptType(int apptType) {
		this.apptType = apptType;
	}
	
	
}
