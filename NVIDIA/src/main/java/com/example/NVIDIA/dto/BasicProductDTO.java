package com.example.NVIDIA.dto;
import java.util.List;
import com.example.NVIDIA.model.ImageCloud;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicProductDTO {
	private Long id;
	private Double unitPrice;
	private int unitInStock;
	private int unitInOrder;
	private String productName;
	private boolean status;
	private List<ImageCloud> product_image;
	private BasicPromotionDTO promotion;
	private BasicManufacturerDTO manufacturer;
	private BasicCategoryDTO category;

}
