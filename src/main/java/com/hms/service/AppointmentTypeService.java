package com.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.model.AppointmentType;

@Service
public interface AppointmentTypeService {

	AppointmentType createAppointmentType(AppointmentType type);

	AppointmentType getById(Integer apptTypeId);

	List<AppointmentType> getAllTypes();

	void deleteAppointmentType(Integer appTypeId);

	AppointmentType updateAppointmentType(Integer appTypeId, AppointmentType apptType);
}
