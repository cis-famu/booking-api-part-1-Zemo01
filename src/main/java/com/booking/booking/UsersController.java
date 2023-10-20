package com.booking.booking.controller;

import com.booking.booking.service.UsersService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UsersController
{
    private UsersService usersService;

    public UsersController(UsersService usersService)
    {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers()
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", usersService.getAllUsers(),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUsersById(@PathVariable String userId)
    {
        try
        {
            return ResponseEntity.ok(new ApiResponse(true, "success", usersService.getUsersById(userId),null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
}
