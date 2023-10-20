package com.booking.booking.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {
    @DocumentId
    private @Nullable String roomID;
    private @Nullable Hotels hotelID;
    private String roomType;
    private Double price;
    private Double capacity;
    private String description;
    private String availability;
    private ArrayList<String> images;
    private Timestamp createdAt;

}
