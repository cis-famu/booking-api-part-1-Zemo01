package com.booking.booking.service;

import com.booking.booking.model.Hotels;
import com.booking.booking.model.Reviews;
import com.booking.booking.model.Rooms;
import com.booking.booking.model.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class ReviewsService {
    private Firestore firestore;
    public ReviewsService() {this.firestore = FirestoreClient.getFirestore();}

    private Reviews documentSnapshotToReviews(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Reviews reviews = null;
        if(document.exists())
        {

            Hotels hotels = null;
            DocumentReference hotelRef = (DocumentReference) document.get("hotelID");
            DocumentSnapshot hotelSnapshot = hotelRef.get().get();
            if (hotelSnapshot.exists())
            {
                HotelsService service = new HotelsService();
                hotels = service.documentSnapshotToHotels(hotelSnapshot);

            }

            Users users = null;
            DocumentReference userRef = (DocumentReference) document.get("userID");
            DocumentSnapshot userSnapshot = userRef.get().get();
            if (userSnapshot.exists())
            {
                UsersService service = new UsersService();
                users = service.documentSnapshotToUsers(userSnapshot);

            }

            //return document.toObject(Reviews.class);
            reviews = new Reviews (document.getId(), hotels, users, document.getDouble("rating"),document.getString("comment"),
                    document.getTimestamp("date"),document.getTimestamp("createdAt"));
        }
        return reviews;
    }


    public ArrayList<Reviews> getAllReviews() throws ExecutionException, InterruptedException
    {
        CollectionReference reviewsCollection = firestore.collection("Reviews");
        ApiFuture<QuerySnapshot> future = reviewsCollection.get();

        ArrayList<Reviews> reviewsList = new ArrayList();

        for(DocumentSnapshot document : future.get().getDocuments())
        {
            Reviews reviews = documentSnapshotToReviews(document);
            if(reviews!= null)
                reviewsList.add(reviews);
        }
        return reviewsList;
    }
    public Reviews getReviewsById(String reviewId) throws ExecutionException, InterruptedException {
        CollectionReference reviewsCollection = firestore.collection("Reviews");
        ApiFuture<DocumentSnapshot> future = reviewsCollection.document(reviewId).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToReviews(document);

    }


}
