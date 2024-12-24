package com.example.NVIDIA.mapper;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.NVIDIA.dto.BasicAddressDTO;
import com.example.NVIDIA.dto.BasicCategoryDTO;
import com.example.NVIDIA.dto.BasicManufacturerDTO;
import com.example.NVIDIA.dto.BasicProductDTO;
import com.example.NVIDIA.dto.BasicPromotionDTO;
import com.example.NVIDIA.dto.BasicUserDTO;
import com.example.NVIDIA.dto.CreditCardPaymentDTO;
import com.example.NVIDIA.dto.OrderDTO;
import com.example.NVIDIA.dto.OrderItemDTO;
import com.example.NVIDIA.dto.PaymentDTO;
import com.example.NVIDIA.model.Category;
import com.example.NVIDIA.model.CreditCardPayment;
import com.example.NVIDIA.model.Manufacturer;
import com.example.NVIDIA.model.Order;
import com.example.NVIDIA.model.Payment;
import com.example.NVIDIA.model.Product;
import com.example.NVIDIA.model.Promotion;
import com.example.NVIDIA.model.User;

@Component
public class PaymentDTOMapper implements Function<Payment,PaymentDTO> {

	@Override
	public PaymentDTO apply(Payment payment) {

		return new PaymentDTO(
				payment.getPayment_date(),
				payment.getTotal_price(),
				payment.getPayment_method(),
				payment.getPayment_status(),
//				mapTOBasicUserDTO(payment.getUser()),
//				payment.getOrder().stream().map(
//				order -> new OrderDTO(
//						order.getId(),
//						order.getOrder_date(),
//						order.getTotal_price(),
//						order.isStatus(),
//						order.getAddress(),
//						order.getNote(),
//						order.getOrder_status(),
//						order.getOrderItem().stream()
//						.map(orderItem -> new OrderItemDTO(
//							orderItem.getId(),
//						    orderItem.getQuanitty(),
//						    orderItem.getTotalPrice(),
//						    mapToBasicProductDTO(orderItem.getProduct())
//		                )).collect(Collectors.toList()),
//						order.getPayment(),
//						mapTOBasicUserDTO(order.getUser())
//						)		
//						)
//				
//				.collect(Collectors.toList()),
				
				mapToCreditCardPaymentDTO(payment.getCreditCardPayment())
               
				);
	}
	
	private CreditCardPaymentDTO mapToCreditCardPaymentDTO(CreditCardPayment creditCardPayment) {
		return new CreditCardPaymentDTO(
				creditCardPayment.getId(),
				creditCardPayment.getCardNumber(),
				creditCardPayment.getCardHolderName(),
				creditCardPayment.getCardExpiryDate(),
				creditCardPayment.getCvv()
				);
		
	}

    private BasicUserDTO mapTOBasicUserDTO(User user) {
    	if(user == null) {
    		return null;
    	}
    	return new BasicUserDTO(
    			user.getId(),
    			user.getFullname(),
    			user.getMobile(),
    			user.getEmail(),
    			user.getPassword(),
    			user.getDateofbirth(),
                user.getAddresses().stream()
                .map(address -> new BasicAddressDTO(
                    address.getId(),
                    address.getHouseNumber(),
                    address.getStreet(),
                    address.getWard(),
                    address.getDistrict(),
                    address.getCity(),
                    address.getCountry(),
                    address.isStatus()
                ))
                .collect(Collectors.toList())
    			);
    }
    
//    private OrderDTO mapToBasicOrderDTO(Order order) {
//		
//		return new OrderDTO(
//				order.getId(),
//				order.getOrder_date(),
//				order.getTotal_price(),
//				order.isStatus(),
//				order.getAddress(),
//				order.getNote(),
//				order.getOrder_status(),
//				order.getOrderItem().stream()
//				.map(orderItem -> new OrderItemDTO(
//					orderItem.getId(),
//				    orderItem.getQuanitty(),
//				    orderItem.getTotalPrice(),
//				    mapToBasicProductDTO(orderItem.getProduct())
//                )).collect(Collectors.toList()),
//				
//
//				order.getPayment(),
//				mapTOBasicUserDTO(order.getUser())
//				);
//	}
    
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