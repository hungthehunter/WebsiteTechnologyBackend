package com.example.NVIDIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NVIDIA.model.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>{

}
