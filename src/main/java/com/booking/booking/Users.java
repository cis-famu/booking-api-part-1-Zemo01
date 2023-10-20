package com.booking.booking.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.lang.annotation.Documented;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @DocumentId
    private @Nullable String userID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private HashMap<String,String> paymentInformation;
    private Timestamp createdAt;


}
