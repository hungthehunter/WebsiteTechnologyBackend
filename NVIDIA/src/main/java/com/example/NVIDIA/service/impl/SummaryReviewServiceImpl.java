package com.example.NVIDIA.service.impl;

import com.example.NVIDIA.model.Order;
import com.example.NVIDIA.model.OrderItem;
import com.example.NVIDIA.model.Product;
import com.example.NVIDIA.repository.OrderRepository;
import com.example.NVIDIA.repository.ProductRepository;
import com.example.NVIDIA.service.SummaryReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryReviewServiceImpl implements SummaryReviewService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private Double totalRevenue = 0.0;
    private Double totalProfit = 0.0;
    private int totalSold = 0;
    private int totalReturned = 0;

    private int pendingOrders = 0;
    private int inProgressOrders = 0;
    private int completedOrders = 0;
    private int canceledOrders = 0;

    private int totalInventory = 0;
    private int totalOrderedProducts = 0;

    /**
     * Tính toán dữ liệu tổng hợp từ đơn hàng và sản phẩm.
     */
@Override
public void calculateSummary() {
    resetSummary(); // Đặt lại tất cả biến trước khi tính toán

    List<Order> orders = orderRepository.findAll();
    List<Product> products = productRepository.findAll();

    // Tính toán thông tin từ đơn hàng
    for (Order order : orders) {
        switch (order.getOrder_status()) {
            case Pending:
                pendingOrders++;
                break;
            case InProgress:
                inProgressOrders++;
                break;
            case Completed:
                completedOrders++;
                // Chỉ tính lợi nhuận cho đơn hàng Completed
                double orderTotal = order.getTotal_price();
                if (Double.isNaN(orderTotal)) {
                    orderTotal = 0.0;
                }
                totalRevenue += orderTotal;

                for (OrderItem orderItem : order.getOrderItem()) {
                    Product product = orderItem.getProduct();
                    int soldQuantity = orderItem.getQuanitty();

                    double profit = (product.getUnitPrice() - getCostPrice(product)) * soldQuantity;
                    if (Double.isNaN(profit)) {
                        profit = 0.0;
                    }
                    totalProfit += profit;
                    totalSold += soldQuantity;
                }
                break;
            case Canceled:
                canceledOrders++;
                break;
        }
    }

    // Tính toán thông tin từ sản phẩm
    for (Product product : products) {
        totalInventory += product.getUnitInStock();
        totalOrderedProducts += product.getUnitInOrder();
    }
}

    /**
     * Đặt lại tất cả các biến thống kê.
     */
    private void resetSummary() {
        totalRevenue = 0.0;
        totalProfit = 0.0;
        totalSold = 0;
        totalReturned = 0;

        pendingOrders = 0;
        inProgressOrders = 0;
        completedOrders = 0;
        canceledOrders = 0;

        totalInventory = 0;
        totalOrderedProducts = 0;
    }

    /**
     * Tính giá vốn sản phẩm.
     */
    private double getCostPrice(Product product) {
        return product.getUnitPrice() * 0.8;
    }

    // Getters
    @Override
    public Double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public Double getTotalProfit() {
        return totalProfit;
    }

    @Override
    public int getTotalSold() {
        return totalSold;
    }

    @Override
    public int getTotalReturned() {
        return totalReturned;
    }

    @Override
    public int getPendingOrders() {
        return pendingOrders;
    }

    @Override
    public int getInProgressOrders() {
        return inProgressOrders;
    }

    @Override
    public int getCompletedOrders() {
        return completedOrders;
    }

    @Override
    public int getCanceledOrders() {
        return canceledOrders;
    }

    @Override
    public int getTotalInventory() {
        return totalInventory;
    }

    @Override
    public int getTotalOrderedProducts() {
        return totalOrderedProducts;
    }
}
