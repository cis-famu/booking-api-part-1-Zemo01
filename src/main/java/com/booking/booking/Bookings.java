package com.booking.booking.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    @DocumentId
    private @Nullable String bookingID; //	Unique identifier for a booking.	String
    private @Nullable Users userID; //	Identifier of the user who made the booking.	String
    private Timestamp checkInDate; //	Date of check-in for the booking.	Date
    private Timestamp checkOutDate; //	Date of check-out for the booking.	Date
    private Double totalPrice; //	Total price for the booking.	Float
    private String status; //	Booking status (e.g., Reserved, Confirmed, Canceled).	String
    private String paymentStatus; //	Payment status for the booking (e.g., Paid, Unpaid).	String
    private Timestamp createdAt;

    public void setCheckInDate(String checkInDate) throws ParseException {
        this.checkInDate = Timestamp.fromProto(Timestamps.parse(checkInDate));
    }

    public void setCheckOutDate(String checkOutDate) throws ParseException {
        this.checkOutDate = Timestamp.fromProto(Timestamps.parse(checkOutDate));
    }

}
