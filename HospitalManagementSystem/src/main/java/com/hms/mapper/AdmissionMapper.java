package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import com.hms.dto.AdmissionRequestDto;
import com.hms.dto.AdmissionResponseDto;
import com.hms.entity.Admission;
import com.hms.entity.Bed;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.repository.BedRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdmissionMapper
{

	private final PatientRepository patientRepo;
	private final DoctorRepository doctorRepo;
	private final BedRepository bedRepo;

   //Will convert requestDto to entity
	public Admission toEntity(AdmissionRequestDto dto)
	{
		Admission admission=new Admission();
		admission.setAdmissionDate(dto.getAdmissionDate());
		admission.setDischargeDate(dto.getDischargeDate());
		admission.setAdmissionReason(dto.getAdmissionReason());
		admission.setAdmissionStatus(dto.getAdmissionStatus());
		admission.setGuardianName(dto.getGuardianName());
		admission.setGuardianContact(dto.getGuardianContact());
		Patient patient = patientRepo.findById(dto.getPatientId())
		        .orElseThrow(() -> new RuntimeException("Patient not found"));
		Doctor doctor = doctorRepo.findById(dto.getDoctorId())
		        .orElseThrow(() -> new RuntimeException("Doctor not found"));
		Bed bed = bedRepo.findById(dto.getBedId())
		        .orElseThrow(() -> new RuntimeException("Bed not found"));
         admission.setPatient(patient);
		admission.setDoctor(doctor);
		admission.setBed(bed);
		return admission;		
	}
	
	public AdmissionResponseDto toDto(Admission admission)
	{
	    AdmissionResponseDto dto = new AdmissionResponseDto();
	    dto.setAdmissionId(admission.getAdmissionId());
	    dto.setAdmissionDate(admission.getAdmissionDate());
	    dto.setDischargeDate(admission.getDischargeDate());
	    dto.setAdmissionReason(admission.getAdmissionReason());
	    dto.setAdmissionStatus(admission.getAdmissionStatus());
	    dto.setGuardianName(admission.getGuardianName());
	    dto.setGuardianContact(admission.getGuardianContact());

	    if (admission.getPatient() != null)
	    {
	        dto.setPatientId(admission.getPatient().getPatientId());
	        dto.setPatientName(admission.getPatient().getFirstName()+ " " + admission.getPatient().getLastName());
	    }

	    if (admission.getDoctor() != null)
	    {
	        dto.setDoctorId(admission.getDoctor().getDoctorId());
	        dto.setDoctorName(admission.getDoctor().getFirstName()+ " " + admission.getDoctor().getLastName());
	    }

	    if (admission.getBed() != null) {
	        dto.setBedId(admission.getBed().getBedId());
	        dto.setBedNumber(admission.getBed().getBedNumber());
	    }
        dto.setCreatedAt(admission.getCreatedAt());
        dto.setUpdatedAt(admission.getUpdatedAt());
        dto.setCreatedBy(admission.getCreatedBy());
	    return dto; //just return the dto
	}
	 public List<AdmissionResponseDto> toDtoList(List<Admission> admission)
	    {
	        return admission.stream().map(this::toDto).collect(Collectors.toList());
	    }
}
