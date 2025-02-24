package com.example.NVIDIA.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * DTO (Data Transfer Object) cho dữ liệu tổng hợp.
 */
public class SummaryReviewDTO {
    private Double totalRevenue;
    private Double totalProfit;
    private int totalSold;
    private int totalReturned;

    private int pendingOrders;
    private int inProgressOrders;
    private int completedOrders;
    private int canceledOrders;

    private int totalInventory;
    private int totalOrderedProducts;

    public SummaryReviewDTO(
        Double totalRevenue,
        Double totalProfit,
        int totalSold,
        int totalReturned,
        int pendingOrders,
        int inProgressOrders,
        int completedOrders,
        int canceledOrders,
        int totalInventory,
        int totalOrderedProducts
    ) {
        this.totalRevenue = totalRevenue;
        this.totalProfit = totalProfit;
        this.totalSold = totalSold;
        this.totalReturned = totalReturned;
        this.pendingOrders = pendingOrders;
        this.inProgressOrders = inProgressOrders;
        this.completedOrders = completedOrders;
        this.canceledOrders = canceledOrders;
        this.totalInventory = totalInventory;
        this.totalOrderedProducts = totalOrderedProducts;
    }

    // Getters và Setters
    public Double getTotalRevenue() {
        return roundValue(totalRevenue);
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalProfit() {
        return roundValue(totalProfit);
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public int getTotalReturned() {
        return totalReturned;
    }

    public void setTotalReturned(int totalReturned) {
        this.totalReturned = totalReturned;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public int getInProgressOrders() {
        return inProgressOrders;
    }

    public void setInProgressOrders(int inProgressOrders) {
        this.inProgressOrders = inProgressOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    public int getCanceledOrders() {
        return canceledOrders;
    }

    public void setCanceledOrders(int canceledOrders) {
        this.canceledOrders = canceledOrders;
    }

    public int getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(int totalInventory) {
        this.totalInventory = totalInventory;
    }

    public int getTotalOrderedProducts() {
        return totalOrderedProducts;
    }

    public void setTotalOrderedProducts(int totalOrderedProducts) {
        this.totalOrderedProducts = totalOrderedProducts;
    }
    private Double roundValue(Double value) {
        if (value == null) return 0.0;
        return BigDecimal.valueOf(value)
                         .setScale(2, RoundingMode.HALF_UP)
                         .doubleValue();
    }
}
