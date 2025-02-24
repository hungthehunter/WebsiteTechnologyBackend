package com.example.NVIDIA.service;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.example.NVIDIA.dto.BannerDTO;
import com.example.NVIDIA.model.Banner;

public interface BannerService {
    BannerDTO getById(Long id);
    List<BannerDTO> getAll();
    Banner create(Banner  banner , MultipartFile file) throws IOException;
    Banner update(Long id ,Banner banner, MultipartFile file) throws IOException;
    void delete(Long id);
}
