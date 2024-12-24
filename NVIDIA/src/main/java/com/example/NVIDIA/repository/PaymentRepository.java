package com.example.NVIDIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NVIDIA.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
