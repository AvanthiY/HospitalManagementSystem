package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hms.UrlPath;
import com.hms.model.AppointmentType;
import com.hms.service.AppointmentTypeService;

@RestController
@RequestMapping(value = UrlPath.ROOT + UrlPath.APPOINTMENT_TYPE)
public class AppointmentTypeController {
	
	@Autowired
	private AppointmentTypeService appTypeService;
	
	@PostMapping(UrlPath.ADD)
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<AppointmentType> createApptType(@RequestBody AppointmentType apptType){
		return new ResponseEntity<>(appTypeService.createAppointmentType(apptType), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = UrlPath.GET_ID+"/{apptTypeId}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Integer apptTypeId ) {
		AppointmentType appType = appTypeService.getById(apptTypeId);

		if(appType != null)
			return new ResponseEntity<> (appType, HttpStatus.OK);
		else
			return new ResponseEntity<String> ("No appointments types found", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = UrlPath.SHOW_ALL, method = RequestMethod.GET)
	public ResponseEntity<?> getAllAppointmentTypes() {
		List<AppointmentType> typesList = appTypeService.getAllTypes();
		
		if(typesList != null) 
			return new ResponseEntity<>(typesList, HttpStatus.OK);
		else
			return new ResponseEntity<String> ("No appointments types found", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = UrlPath.DELETE+"/{appTypeId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteApptType(@PathVariable Integer appTypeId) {
		appTypeService.deleteAppointmentType(appTypeId);
		
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = UrlPath.UPDATE+"/{appTypeId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateApptType(@PathVariable Integer appTypeId, 
			@RequestBody AppointmentType apptType) {
		
		return new ResponseEntity<AppointmentType> 
					(appTypeService.updateAppointmentType(appTypeId, apptType), HttpStatus.OK);
		
	}
}
