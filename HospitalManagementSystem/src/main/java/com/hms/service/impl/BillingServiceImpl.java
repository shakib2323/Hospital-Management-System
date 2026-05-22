package com.hms.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.dto.BillingResponseDto;
import com.hms.dto.BillingUpdateRequestDto;
import com.hms.entity.Admission;
import com.hms.entity.Appointment;
import com.hms.entity.Billing;
import com.hms.entity.LabTest;
import com.hms.entity.Medicine;
import com.hms.entity.Patient;
import com.hms.entity.Prescription;
import com.hms.enums.BillStatus;
import com.hms.exception.ResourceNotFoundException;
import com.hms.mapper.BillingMapper;
import com.hms.repository.AdmissionRepository;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.BillingRepository;
import com.hms.repository.LabTestRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.PrescriptionRepository;
import com.hms.service.BillingService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService
{
   private final BillingRepository billingRepo;
   private final BillingMapper billingMapper;
   private final PatientRepository patientRepo;
   private final AppointmentRepository appointmentRepo;  
   private final LabTestRepository labTestRepo;          
   private final PrescriptionRepository prescriptionRepo;
   private final AdmissionRepository admissionRepo;
	@Override
	public BillingResponseDto generateBill(Long patientId) {
		Patient patient = patientRepo.findById(patientId)
	            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
		List<Appointment> appointments = appointmentRepo.findByPatient(patient);
	    Double consultationCharges = appointments.stream()
	            .mapToDouble(a -> a.getDoctor().getConsultationFee())
	            .sum();
	    //Calculate lab charges
	    List<LabTest> labTests = labTestRepo.findByPatient(patient);
	    Double labCharges = labTests.stream()
	            .mapToDouble(LabTest::getTestCost)
	            .sum();
	    //Calculate medicine charges from prescriptions
	    List<Prescription> prescriptions = prescriptionRepo.findByPatient(patient);
	    Double medicineCharges = prescriptions.stream().flatMap(p -> p.getMedicines().stream())
	            .mapToDouble(Medicine::getUnitPrice)
	            .sum();
	    //Calculate room charges from active admission
	    Double roomCharges = 0.0;
	    Admission admission = admissionRepo.findActiveAdmissionByPatient(patient);
	    if (admission != null && admission.getDischargeDate() != null) {
	        long days = ChronoUnit.DAYS.between(
	                admission.getAdmissionDate(),
	                admission.getDischargeDate());
	        roomCharges = days * admission.getBed().getRoom().getDailyCharge();
	    }
	    //Apply discount and tax
	    Double discountAmount = 0.0;   // can be made dynamic later
	    Double taxAmount = (consultationCharges + labCharges
	            + medicineCharges + roomCharges) * 0.05;
	    Double totalAmount = consultationCharges + labCharges
	            + medicineCharges + roomCharges
	            - discountAmount + taxAmount;
	 //Create and save billing record
	    Billing billing = new Billing();
	    billing.setBillNumber("BILL-" + patientId + "-" + System.currentTimeMillis());
	    billing.setConsultationCharges(consultationCharges);
	    billing.setMedicineCharges(medicineCharges);
	    billing.setLabCharges(labCharges);
	    billing.setRoomCharges(roomCharges);
	    billing.setDiscountAmount(discountAmount);
	    billing.setTaxAmount(taxAmount);
	    billing.setTotalAmount(totalAmount);
	    billing.setBillStatus(BillStatus.PENDING);
	    billing.setGeneratedDate(LocalDate.now());
	    billing.setDueDate(LocalDate.now().plusDays(7)); // due in 7 days
	    billing.setPatient(patient);

	    billingRepo.save(billing);

	    //Return response
	    return billingMapper.toDto(billing);
		
	}

	@Override
	public BillingResponseDto updateBill(Long billId, BillingUpdateRequestDto dto) {
		Billing bill=billingRepo.findById(billId).orElseThrow(()->
		new ResourceNotFoundException("Bill not found with Id: "+billId));
		bill.setConsultationCharges(dto.getConsultationCharges());
		bill.setMedicineCharges(dto.getMedicineCharges());
		bill.setLabCharges(dto.getLabCharges());
		bill.setRoomCharges(dto.getRoomCharges());
		bill.setDiscountAmount(dto.getDiscountAmount());
		bill.setTaxAmount(dto.getTaxAmount());
		bill.setTotalAmount(dto.getTotalAmount());
		bill.setBillStatus(dto.getBillStatus());
		bill.setGeneratedDate(dto.getGeneratedDate());
		bill.setDueDate(dto.getDueDate());
		bill.setUpdatedBy(dto.getUpdatedBy());
		billingRepo.save(bill);
		return billingMapper.toDto(bill);
	}

	@Override
	public BillingResponseDto getBillById(Long billId) {
		Billing bill=billingRepo.findById(billId).orElseThrow(()->
		new ResourceNotFoundException("Bill not found with Id: "+billId));
		return billingMapper.toDto(bill);
	}

	@Override
	public List<BillingResponseDto> getAllBills() {
		List<Billing> bills=billingRepo.findAll();
		return billingMapper.toDtoList(bills);
	}

	@Override
	public Double calculateTotalBill(Long patientId) {
	    List<Billing> bills = billingRepo.findByPatientPatientId(patientId);
	    return bills.stream()
	                .mapToDouble(Billing::getTotalAmount)
	                .sum();
	}
	@Override
	public void applyDiscount(Long billId, Double discountPercentage) {
	    Billing bill = billingRepo.findById(billId)
	                   .orElseThrow(() -> new ResourceNotFoundException("Bill not found with Id: " + billId));

	    Double discountAmount = bill.getTotalAmount() * discountPercentage / 100;
	    bill.setTotalAmount(bill.getTotalAmount() - discountAmount);
	    billingRepo.save(bill);
	}

	@Override
	public void cancelBill(Long billId) {
		Billing bill=billingRepo.findById(billId).orElseThrow(()->
		new ResourceNotFoundException("Bill not found with Id: "+billId));
		bill.setBillStatus(BillStatus.CANCELLED);
		billingRepo.save(bill);
	}

}
