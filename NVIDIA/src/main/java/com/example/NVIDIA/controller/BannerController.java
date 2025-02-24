package com.example.NVIDIA.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.NVIDIA.dto.BannerDTO;
import com.example.NVIDIA.model.Banner;
import com.example.NVIDIA.service.BannerService;

@RestController
@RequestMapping("/api/banners")
public class BannerController {
	
	@Autowired
	private BannerService bannerService;
	
	@PostMapping
    public ResponseEntity<Banner> createBanner(
    		@RequestPart("banner") Banner bannerDTO,
    		@RequestParam(value="file", required = false) MultipartFile file
    		) throws IOException {
        Banner createdBanner = bannerService.create(bannerDTO,file);
        return ResponseEntity.ok(createdBanner);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<BannerDTO> getBannerById(@PathVariable Long id) {
        BannerDTO Banner = bannerService.getById(id);
        return ResponseEntity.ok(Banner);
    }

    @GetMapping
    public ResponseEntity<List<BannerDTO>> getAllBanners() {
        List<BannerDTO> Banners = bannerService.getAll();
        return ResponseEntity.ok(Banners);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(
            @PathVariable Long id,
            @RequestParam(value="file", required = false) MultipartFile file,
            @RequestPart("banner") Banner BannerDTO) throws IOException{
        Banner updatedBanner = bannerService.update(id, BannerDTO,file);
        return ResponseEntity.ok(updatedBanner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        bannerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
