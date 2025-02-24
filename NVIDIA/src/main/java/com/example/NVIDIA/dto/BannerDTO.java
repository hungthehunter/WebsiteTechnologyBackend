package com.example.NVIDIA.dto;

import java.util.List;

import com.example.NVIDIA.model.ImageCloud;
import com.example.NVIDIA.model.Specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerDTO {
private Long id;
private boolean status;
private String name;
private List<Specification> specification;
private ImageCloud imageCloud;
}
