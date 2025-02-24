package com.example.NVIDIA.service;

public interface SummaryReviewService {
    void calculateSummary();

    Double getTotalRevenue();

    Double getTotalProfit();

    int getTotalSold();

    int getTotalReturned();

    int getPendingOrders();

    int getInProgressOrders();

    int getCompletedOrders();

    int getCanceledOrders();

    int getTotalInventory();

    int getTotalOrderedProducts();
}
