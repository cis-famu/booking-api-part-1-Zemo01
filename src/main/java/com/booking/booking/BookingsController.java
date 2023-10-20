package com.booking.booking.controller;

import com.booking.booking.service.BookingsService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Bookings")
public class BookingsController {
    private BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService)
    {
        this.bookingsService = bookingsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBookings()
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", bookingsService.getAllBookings(),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<ApiResponse> getBookingsById(@PathVariable String bookingId)
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", bookingsService.getbookingsById(bookingId),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
}
