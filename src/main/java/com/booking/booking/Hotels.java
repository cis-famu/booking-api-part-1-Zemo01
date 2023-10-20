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
public class Hotels {
    @DocumentId
    private @Nullable String hotelID; //	Unique identifier for a hotel.	String
    private String name; //	Name of the hotel.	String
    private String description; //	Description of the hotel.	String
    private String rating; //	Rating of the hotel.	Float
    private String address; //	Address of the hotel.	String
    private String contactInformation; //	Contact email of the hotel.	String
    private ArrayList<String> amenities; // Array of amenities offered by the hotel.
    private Timestamp createdAt;
}
