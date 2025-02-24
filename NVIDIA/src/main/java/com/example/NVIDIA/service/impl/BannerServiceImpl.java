package com.example.NVIDIA.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.NVIDIA.dto.BannerDTO;
import com.example.NVIDIA.mapper.BannerDTOMapper;
import com.example.NVIDIA.model.Banner;
import com.example.NVIDIA.model.ImageCloud;
import com.example.NVIDIA.model.Specification;
import com.example.NVIDIA.repository.BannerRepository;
import com.example.NVIDIA.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerRepository bannerRepository;
	
	@Autowired
	private BannerDTOMapper bannerDTOMapper;
	
	@Autowired
    private Cloudinary cloudinary;
	
	@Override
	public BannerDTO getById(Long id) {
		Banner banner = bannerRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Cannot find banner"));
		return bannerDTOMapper.apply(banner);
	}

	@Override
	public List<BannerDTO> getAll() {
		List<Banner> banner = bannerRepository.findAll();
		return banner.stream().map(bannerDTOMapper).collect(Collectors.toList());
	}

	@Override
	public Banner create(Banner banner, MultipartFile file) throws IOException {
		Banner newBanner = new Banner();
		newBanner.setStatus(true);
		newBanner.setName(banner.getName());
	    if (banner.getSpecification() != null) {
	        List<Specification> specifications = banner.getSpecification().stream()
	            .map(spec -> {
	                Specification specification = new Specification();
	                specification.setSpecificationName(spec.getSpecificationName());
	                specification.setSpecificationData(spec.getSpecificationData());
	                specification.setBanner(newBanner);
	                return specification;
	            }).collect(Collectors.toList());

	        newBanner.setSpecification(specifications);  // Assuming you have a list field in `Product`
	    }
	    if (file != null && !file.isEmpty()) {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String publicId = uploadResult.get("public_id").toString();
            String url = uploadResult.get("url").toString();
            String format = uploadResult.get("format").toString();

            ImageCloud imageCloud = new ImageCloud();
            imageCloud.setPublic_id(publicId);
            imageCloud.setUrl(url);
            imageCloud.setFormat(format);
            imageCloud.setMainImage(true);
            newBanner.setImageCloud(imageCloud);
        }
	    
	    Banner savedBanner = bannerRepository.save(newBanner);
	    
		return savedBanner;
	}

	@Override
	public Banner update(Long id, Banner banner, MultipartFile file) throws IOException {
        Optional<Banner> optionalBanner = bannerRepository.findById(id);
        if (!optionalBanner.isPresent()) {
            throw new RuntimeException("Promotion not found with id: " + id);
        }
        Banner updatedBanner = optionalBanner.get();
        updatedBanner.setStatus(true);
        updatedBanner.setName(banner.getName());
	    if (banner.getSpecification() != null) {
	        // Xóa tất cả specifications cũ bằng cách đặt lại danh sách specifications
	        updatedBanner.getSpecification().clear();

	        // Thêm các specifications mới
	        List<Specification> specifications = banner.getSpecification().stream()
	            .map(spec -> {
	                Specification specification = new Specification();
	                specification.setSpecificationName(spec.getSpecificationName());
	                specification.setSpecificationData(spec.getSpecificationData());
	                specification.setBanner(updatedBanner);
	                return specification;
	            }).collect(Collectors.toList());

	        updatedBanner.getSpecification().addAll(specifications);
	    }
        if (file != null && !file.isEmpty()) {
            // Xóa ảnh cũ trên Cloudinary nếu tồn tại
            if (banner.getImageCloud() != null) {
                cloudinary.uploader().destroy(banner.getImageCloud().getPublic_id(), ObjectUtils.emptyMap());
            }

            // Tải ảnh mới lên Cloudinary
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String publicId = uploadResult.get("public_id").toString();
            String url = uploadResult.get("url").toString();
            String format = uploadResult.get("format").toString();

            // Tạo đối tượng ImageCloud mới
            ImageCloud imageCloud = new ImageCloud();
            imageCloud.setPublic_id(publicId);
            imageCloud.setUrl(url);
            imageCloud.setFormat(format);
            imageCloud.setMainImage(true); // Đặt là ảnh chính mới
            updatedBanner.setImageCloud(imageCloud);
        }
	    
	    Banner savedBanner = bannerRepository.save(updatedBanner);
        
		return savedBanner;
	}

	@Override
	public void delete(Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find promotion"));
        
        banner.setStatus(false);

        // Xóa promotion
        bannerRepository.save(banner);
		
	}

}
