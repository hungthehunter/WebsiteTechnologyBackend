package com.example.NVIDIA.mapper;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.NVIDIA.dto.BasicCategoryDTO;
import com.example.NVIDIA.dto.BasicManufacturerDTO;
import com.example.NVIDIA.dto.BasicProductDTO;
import com.example.NVIDIA.dto.BasicPromotionDTO;
import com.example.NVIDIA.dto.OrderItemDTO;
import com.example.NVIDIA.model.Category;
import com.example.NVIDIA.model.Manufacturer;
import com.example.NVIDIA.model.OrderItem;
import com.example.NVIDIA.model.Product;
import com.example.NVIDIA.model.Promotion;

@Component
public class OrderItemDTOMapper implements Function<OrderItem,OrderItemDTO>{

	@Override
	public OrderItemDTO apply(OrderItem orderItem) {

		return new OrderItemDTO(
				orderItem.getId(),
				orderItem.getQuanitty(),
				orderItem.getTotalPrice(),
				mapToBasicProductDTO(orderItem.getProduct())
				);
		
	}
	
	  private BasicPromotionDTO mapToBasicPromotionDTO(Promotion promotion) {
	    	if(promotion == null) {
	    		return null;
	    	}
	    	return new BasicPromotionDTO(
	    			promotion.getId(),
	    			promotion.getName(),
	    			promotion.getDescription(),
	    			promotion.getDiscountPercentage(),
	    			promotion.getStartDate(),
	    			promotion.getEndDate(),
	    			promotion.isActive(),
	    			promotion.getCreatedAt(),
	    			promotion.getUpdatedAt()
	    			);
	    }
	    
	    private BasicCategoryDTO mapBasicCategoryDTO(Category category) {
	    	if(category == null) {
	    		return null;
	    	}
	    	
	    	return new BasicCategoryDTO(
	    	     
	    		            category.getId(),
	    		            category.getName(),
	    		            category.getDescription(),
	    		            category.getImageCloud()
	);
	    }
	    
	    private BasicManufacturerDTO mapBasicManufacturerDTO(Manufacturer manufacturer) {
	    	if(manufacturer== null) {
	    		return null;
	    	}
	    	
	    	return new BasicManufacturerDTO(
		            manufacturer.getId(), 
		            manufacturer.getName(),
		            manufacturer.getCountry(),
		            manufacturer.getWebsite(),
		            manufacturer.getDescription(),
		            manufacturer.getEmail(),
		            manufacturer.getPhone(),
		            manufacturer.getAddress(),
		            manufacturer.getCreatedAt(),
		            manufacturer.getUpdatedAt(),
		            manufacturer.getImageCloud()
		            );
	    }
	
	
    private BasicProductDTO mapToBasicProductDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new BasicProductDTO(
            product.getId(),
            product.getUnitPrice(),
            product.getUnitInStock(),
            product.getUnitInOrder(),
            product.getProductName(),
            product.isStatus(),
            product.getProduct_image().stream().collect(Collectors.toList()),
            mapToBasicPromotionDTO(product.getPromotion()),
            mapBasicManufacturerDTO(product.getManufacturer()),
            mapBasicCategoryDTO(product.getCategory())
        );

}
}
