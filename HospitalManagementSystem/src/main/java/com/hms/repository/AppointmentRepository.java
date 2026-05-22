package com.hms.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> 
{
    @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor")
    List<Appointment> findByDoctor(@Param("doctor") Doctor doctor);
   
    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient")
    List<Appointment> findByPatient(@Param("patient") Patient patient);

    @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor AND a.appointmentDate = :date")
    List<Appointment> findByDoctorAndAppointmentDate( @Param("doctor") Doctor doctor, @Param("date") LocalDate date);

    @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctor = :doctor " +"AND a.appointmentDate = :date AND a.appointmentTime = :time")
    boolean existsByDoctorAndAppointmentDateAndAppointmentTime( @Param("doctor") Doctor doctor, @Param("date") LocalDate date, @Param("time") LocalTime time);

}
