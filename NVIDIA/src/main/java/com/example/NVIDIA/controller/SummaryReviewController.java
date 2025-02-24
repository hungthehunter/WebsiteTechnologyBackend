package com.example.NVIDIA.controller;

import com.example.NVIDIA.dto.SummaryReviewDTO;
import com.example.NVIDIA.service.SummaryReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summary")
public class SummaryReviewController {

    @Autowired
    private SummaryReviewService summaryReviewService;

    /**
     * API Endpoint để lấy dữ liệu tổng hợp.
     *
     * @return SummaryReviewDTO - Dữ liệu tổng hợp đã được tính toán.
     */
    @GetMapping
    public SummaryReviewDTO getSummary() {
        summaryReviewService.calculateSummary();

        // Tạo đối tượng DTO với dữ liệu từ service
        return new SummaryReviewDTO(
            summaryReviewService.getTotalRevenue(),
            summaryReviewService.getTotalProfit(),
            summaryReviewService.getTotalSold(),
            summaryReviewService.getTotalReturned(),
            summaryReviewService.getPendingOrders(),
            summaryReviewService.getInProgressOrders(),
            summaryReviewService.getCompletedOrders(),
            summaryReviewService.getCanceledOrders(),
            summaryReviewService.getTotalInventory(),
            summaryReviewService.getTotalOrderedProducts()
        );
    }
}
