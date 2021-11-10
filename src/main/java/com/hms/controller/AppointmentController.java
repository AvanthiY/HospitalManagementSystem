package com.hms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.UrlPath;
import com.hms.model.AppointmentType;
import com.hms.model.dto.AppointmentDTO;
import com.hms.model.dto.UserDTO;
import com.hms.service.AppointmentService;
import com.hms.util.ErrorMessage;

@RestController
@RequestMapping(value = UrlPath.ROOT + UrlPath.APPOINTMENT)
public class AppointmentController {

	@Autowired
	private AppointmentService apptService;
	
	@PostMapping(UrlPath.ADD)
	@PreAuthorize("hasRole('admin') or hasRole('patient')")
	public ResponseEntity<AppointmentDTO> createAppt(@RequestBody AppointmentDTO apptDtoReq){
		return new ResponseEntity<>(apptService.createAppt(apptDtoReq.getDate(), apptDtoReq.getApptType(), 
				apptDtoReq.getDoctorId(), apptDtoReq.getPatientId()), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('admin') or hasRole('patient') or hasRole('doctor')")
	@RequestMapping(value = UrlPath.GET_ID+"/{apptId}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long apptId ) {
		AppointmentDTO appDto = apptService.getById(apptId);
		if(appDto != null)
			return new ResponseEntity<> (appDto, HttpStatus.OK);
		else
			return new ResponseEntity<String> ("No appointments found", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('admin') or hasRole('doctor')")
	@RequestMapping(value = UrlPath.SHOW_ALL, method = RequestMethod.GET)
	public ResponseEntity<?> getAllAppointments(@RequestParam(required = false) String docName,
	        @RequestParam(defaultValue = UrlPath.DEFAULT_PAGE_INDEX) int page,
	        @RequestParam(defaultValue = UrlPath.DEFAULT_PAGE_SIZE) int size) {
		try {
			List<AppointmentDTO> apptDtos = new ArrayList<>();
			apptDtos = apptService.getAllAppointments(docName, page, size);
			
			if(!apptDtos.isEmpty())
				return new ResponseEntity<>(apptDtos, HttpStatus.OK);
			else
				return new ResponseEntity<String>(ErrorMessage.NO_APPTS, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
		      return new ResponseEntity<String>(ErrorMessage.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('admin') or hasRole('patient')")
	@RequestMapping(value = UrlPath.DELETE+"/{apptId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAppointment(@PathVariable Long apptId) {
		apptService.deleteAppointment(apptId);
		
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('admin') or hasRole('patient')")
	@RequestMapping(value = UrlPath.UPDATE+"/{apptId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAppointment(@PathVariable Long apptId, 
					@RequestBody AppointmentDTO apptDto) {
		
		return new ResponseEntity<AppointmentDTO>
				(apptService.updateAppointment(apptId, apptDto), HttpStatus.OK);
	}
}
