package com.booking.booking.service;

import com.booking.booking.model.Bookings;
import com.booking.booking.model.Rooms;
import com.booking.booking.model.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.remoteconfig.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class BookingsService {
    private Firestore firestore;
    public BookingsService() {this.firestore = FirestoreClient.getFirestore();}

    private Bookings documentSnapshotToBookings(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Bookings bookings = null;
        if(document.exists())
        {

            Users users = null;
            DocumentReference userRef = (DocumentReference) document.get("userID");
            DocumentSnapshot userSnapshot = userRef.get().get();
            if (userSnapshot.exists()) {
                UsersService service = new UsersService();
                users = service.documentSnapshotToUsers(userSnapshot);

            }

            //return document.toObject(Rooms.class);
            bookings = new Bookings (document.getId(), users, document.getTimestamp("checkInDate"),document.getTimestamp("checkOutDate"), document.getDouble("totalPrice"),
                    document.getString("status"),document.getString("paymentStatus"),document.getTimestamp("createdAt"));
        }
        return bookings;
    }


    public ArrayList<Bookings> getAllBookings() throws ExecutionException, InterruptedException
    {
        CollectionReference bookingsCollection = firestore.collection("Bookings");
        ApiFuture<QuerySnapshot> future = bookingsCollection.get();

        ArrayList<Bookings> bookingsList = new ArrayList();

        for(DocumentSnapshot document : future.get().getDocuments())
        {
            Bookings bookings = documentSnapshotToBookings(document);
            if(bookings != null)
                bookingsList.add(bookings);
        }
        return bookingsList;
    }
    public Bookings getbookingsById(String bookingId) throws ExecutionException, InterruptedException {
        CollectionReference bookingsCollection = firestore.collection("Bookings");
        ApiFuture<DocumentSnapshot> future = bookingsCollection.document(bookingId).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToBookings(document);

    }

}
