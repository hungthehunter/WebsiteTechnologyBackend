package com.example.NVIDIA.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.NVIDIA.dto.BannerDTO;
import com.example.NVIDIA.model.Banner;

@Component
public class BannerDTOMapper implements Function<Banner,BannerDTO>{

	@Override
	public BannerDTO apply(Banner banner) {
		return new BannerDTO(
				banner.getId(),
				banner.isStatus(),
				banner.getName(),
				banner.getSpecification(),
				banner.getImageCloud()
				);
	}

}
