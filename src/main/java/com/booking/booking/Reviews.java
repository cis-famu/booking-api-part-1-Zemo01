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
public class Reviews {
    @DocumentId
    private @Nullable String reviewID; //	Unique identifier for a review.	String
    private @Nullable Hotels hotelID; //	Identifier of the hotel for which the review is written.	String
    private @Nullable Users userID; //	Identifier of the user who wrote the review.	String
    private Double rating; //	Rating given in the review.	Float
    private String comment; //	Comments or feedback provided in the review.	String
    private Timestamp date; //	Date when the review was submitted.	Date
    private Timestamp createdAt;

    public void setDate(String date) throws ParseException {
        this.date = Timestamp.fromProto(Timestamps.parse(date));
    }

}
