package com.hms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.model.AppointmentType;
import com.hms.repository.AppointmentTypeRepository;
import com.hms.service.AppointmentTypeService;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService{

	@Autowired
	AppointmentTypeRepository appTypeRepo;
	
	@Override
	public AppointmentType createAppointmentType(AppointmentType type) {
		AppointmentType appType = appTypeRepo.save(type);
		return appType;
	}

	@Override
	public AppointmentType getById(Integer apptTypeId) {
		Optional<AppointmentType> appType = null;
		
		if (apptTypeId != null) {
			appType = appTypeRepo.findById(apptTypeId);
		}
		
		return appType.get();
	}

	@Override
	public List<AppointmentType> getAllTypes() {
		List<AppointmentType> appTypeList = appTypeRepo.findAll();
		return appTypeList;
	}

	@Override
	public void deleteAppointmentType(Integer appTypeId) {
		appTypeRepo.deleteById(appTypeId);
	}
	
	public AppointmentType updateAppointmentType(Integer appTypeId, AppointmentType appType) {
		AppointmentType appTypeDb = appTypeRepo.getById(appTypeId);
		
		if(appTypeDb != null) {
			appTypeDb.setName(appType.getName());
			appTypeDb.setDeleted(appType.isDeleted());
		}
		
		return appTypeRepo.save(appTypeDb); 
	}

}
