package com.hms.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hms.dto.BillingRequestDto;
import com.hms.dto.BillingResponseDto;
import com.hms.entity.Billing;
import com.hms.entity.Patient;
import com.hms.exception.ResourceNotFoundException;
import com.hms.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BillingMapper 
{
    private final PatientRepository patientRepo;
    public Billing toEntity(BillingRequestDto dto) 
    {
        Billing billing = new Billing();
        billing.setBillNumber(dto.getBillNumber());
        billing.setConsultationCharges(dto.getConsultationCharges());
        billing.setMedicineCharges(dto.getMedicineCharges());
        billing.setLabCharges(dto.getLabCharges());
        billing.setRoomCharges(dto.getRoomCharges());
        billing.setDiscountAmount(dto.getDiscountAmount());
        billing.setTaxAmount(dto.getTaxAmount());
        billing.setTotalAmount(dto.getTotalAmount());
        billing.setBillStatus(dto.getBillStatus());
        billing.setGeneratedDate(dto.getGeneratedDate());
        billing.setDueDate(dto.getDueDate());
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));
        billing.setPatient(patient);
        billing.setCreatedBy(dto.getCreatedBy());
        billing.setUpdatedBy(dto.getUpdatedBy());
        return billing;
    }

    public BillingResponseDto toDto(Billing billing) 
    {
        BillingResponseDto dto = new BillingResponseDto();
        dto.setBillId(billing.getBillId());
        dto.setBillNumber(billing.getBillNumber());
        dto.setConsultationCharges(billing.getConsultationCharges());
        dto.setMedicineCharges(billing.getMedicineCharges());
        dto.setLabCharges(billing.getLabCharges());
        dto.setRoomCharges(billing.getRoomCharges());
        dto.setDiscountAmount(billing.getDiscountAmount());
        dto.setTaxAmount(billing.getTaxAmount());
        dto.setTotalAmount(billing.getTotalAmount());
        dto.setBillStatus(billing.getBillStatus());
        dto.setGeneratedDate(billing.getGeneratedDate());
        dto.setDueDate(billing.getDueDate());
        if (billing.getPatient() != null) {
            dto.setPatientId(billing.getPatient().getPatientId());
            dto.setPatientName(billing.getPatient().getFirstName()+ " " + billing.getPatient().getLastName());
        }
        dto.setCreatedBy(billing.getCreatedBy());
        dto.setUpdatedBy(billing.getUpdatedBy());
        dto.setCreatedAt(billing.getCreatedAt());
        dto.setUpdatedAt(billing.getUpdatedAt());
        return dto;
    }

    public List<BillingResponseDto> toDtoList(List<Billing> billings) 
    {
        return billings.stream().map(this::toDto).collect(Collectors.toList());
    }
}