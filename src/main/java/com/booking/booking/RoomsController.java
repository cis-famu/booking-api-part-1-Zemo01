package com.booking.booking.controller;

import com.booking.booking.service.RoomsService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Rooms")
public class RoomsController {
    private RoomsService roomsService;

    public RoomsController(RoomsService roomsService)
    {
        this.roomsService = roomsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRooms()
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", roomsService.getAllRooms(),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ApiResponse> getRoomsById(@PathVariable String roomId)
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", roomsService.getRoomsById(roomId),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
}
