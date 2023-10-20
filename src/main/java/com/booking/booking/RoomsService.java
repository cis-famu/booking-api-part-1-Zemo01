package com.booking.booking.service;

import com.booking.booking.model.Hotels;
import com.booking.booking.model.Rooms;
import com.booking.booking.model.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class RoomsService {
    private Firestore firestore;
    public RoomsService() {this.firestore = FirestoreClient.getFirestore();}

    private Rooms documentSnapshotToRooms(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Rooms rooms = null;
        if(document.exists())
        {

            Hotels hotels = null;
            DocumentReference hotelRef = (DocumentReference) document.get("hotelID");
            DocumentSnapshot hotelSnapshot = hotelRef.get().get();
            if (hotelSnapshot.exists()) {
                HotelsService service = new HotelsService();
                hotels = service.documentSnapshotToHotels(hotelSnapshot);

            }

            //return document.toObject(Rooms.class);
            rooms = new Rooms (document.getId(), hotels, document.getString("roomType"),document.getDouble("price"), document.getDouble("capacity"),
                        document.getString("description"),document.getString("availability"),(ArrayList<String>) document.get("images"),document.getTimestamp("createdAt"));
        }
        return rooms;
    }


    public ArrayList<Rooms> getAllRooms() throws ExecutionException, InterruptedException
    {
        CollectionReference roomsCollection = firestore.collection("Rooms");
        ApiFuture<QuerySnapshot> future = roomsCollection.get();

        ArrayList<Rooms> roomsList = new ArrayList();

        for(DocumentSnapshot document : future.get().getDocuments())
        {
            Rooms rooms = documentSnapshotToRooms(document);
            if(rooms != null)
                roomsList.add(rooms);
        }
        return roomsList;
    }
    public Rooms getRoomsById(String roomId) throws ExecutionException, InterruptedException {
        CollectionReference roomsCollection = firestore.collection("Rooms");
        ApiFuture<DocumentSnapshot> future = roomsCollection.document(roomId).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToRooms(document);

    }


}
