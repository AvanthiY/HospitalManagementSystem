package com.hms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hms.model.Appointment;
import com.hms.model.AppointmentType;
import com.hms.model.Doctor;
import com.hms.model.Patient;
import com.hms.model.User;
import com.hms.model.dto.AppointmentDTO;
import com.hms.model.dto.UserDTO;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.AppointmentTypeRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import com.hms.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository apptRepo;
	
	@Autowired
	private DoctorRepository docRepo;
	
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private AppointmentTypeRepository apptTypeRepo;
	
	@Override
	public AppointmentDTO createAppt(LocalDateTime apptDate, int apptTypeId, Long docId, Long patientId) {
		
		Appointment appt = new Appointment();
		AppointmentDTO apptDto= null; 
		
		if(!apptDate.isBefore(LocalDateTime.now())) {
			Doctor doc = docRepo.getById(docId);		
			Patient patient = patientRepo.getById(patientId);		
			AppointmentType apptType = apptTypeRepo.getById(apptTypeId);
			
			if(doc != null && patient != null) {
				appt.setAppointmentType(apptType);
				appt.setDoctor(doc);
				appt.setPatient(patient);
				appt.setDate(apptDate);
				
				Appointment apptRes = apptRepo.save(appt);
				
				apptDto = updateDto(apptRes);
			}
		}	
		return apptDto;
	}

	@Override
	public List<AppointmentDTO> getAllAppointments(String docName, int page, int size) {

	    	List<Appointment> appointments = new ArrayList<>();
	    	List<AppointmentDTO> apptDtos = new ArrayList<>();
	    	
	    	Pageable paging = PageRequest.of(page, size);
	      
	    	Page<Appointment> pageAppts;
	    	
	    	if (docName == null)
	    		pageAppts = apptRepo.findAll(paging);
	    	else
	    		pageAppts = apptRepo.findByDoctorName(docName, paging);

	    	if(pageAppts.hasContent()) {
		    	appointments = pageAppts.getContent();
		
		    	appointments.stream().forEach(appt-> {
			    	  AppointmentDTO apptDto = updateDto(appt);
			    	  
					  apptDtos.add(apptDto);
			      });
	    	}
	    	
	    	return apptDtos;
		}

	@Override
	public AppointmentDTO getById(Long apptId) {
		
		Optional<Appointment> appt = apptRepo.findById(apptId);

		if (appt.isPresent()) {
			 AppointmentDTO apptDto = updateDto(appt.get());
	    	 
			return apptDto;
		}

		return null;

	}

	@Override
	public void deleteAppointment(Long apptId) {
		apptRepo.deleteById(apptId);
	}

	@Override
	public AppointmentDTO updateAppointment(Long apptId, AppointmentDTO apptDto) {
		Appointment appt = apptRepo.getById(apptId);
		
		if(appt != null) {
			appt.setDate(apptDto.getDate());
			
			if(appt.getAppointmentType().getId() != apptDto.getApptType())
			{
				appt.setAppointmentType(apptTypeRepo.findById((Integer)apptDto.getApptType()).get());
			}
			if(appt.getDoctor().getId() != apptDto.getDoctorId())
			{
				appt.setDoctor(docRepo.findById(apptDto.getDoctorId()).get());
			}
			if(appt.getPatient().getId() != apptDto.getPatientId())
			{
				appt.setPatient(patientRepo.findById(apptDto.getPatientId()).get());
			}
		}
		
		Appointment apptRes = apptRepo.save(appt);
		
		return updateDto(apptRes);
	}
	
	private AppointmentDTO updateDto (Appointment appt) {
		AppointmentDTO apptDto = new AppointmentDTO();
		
		apptDto.setDoctorId(appt.getDoctor().getId());
  	  	apptDto.setPatientId(appt.getPatient().getId());
  	  	apptDto.setApptType(appt.getAppointmentType().getId());
  	    apptDto.setDate(appt.getDate());
		
		return apptDto;
	}
}
