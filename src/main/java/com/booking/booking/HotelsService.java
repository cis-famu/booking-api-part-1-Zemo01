package com.booking.booking.service;

import com.booking.booking.model.Hotels;
import com.booking.booking.model.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class HotelsService
{
    private Firestore firestore;
    public HotelsService() {this.firestore = FirestoreClient.getFirestore();}

    public Hotels documentSnapshotToHotels(DocumentSnapshot document)
    {
        Hotels hotels = null;
        if(document.exists())
        {
            //return document.toObject(Users.class);
            hotels = new Hotels (document.getId(), document.getString("name"), document.getString("description"),document.getString("rating"),
                        document.getString("address"), document.getString("contactInformation"),(ArrayList<String>) document.get("amenities"),
                    document.getTimestamp("createdAt"));
        }
        return hotels;
    }


    public ArrayList<Hotels> getAllHotels() throws ExecutionException, InterruptedException
    {
        CollectionReference hotelsCollection = firestore.collection("Hotels");
        ApiFuture<QuerySnapshot> future = hotelsCollection.get();

        ArrayList<Hotels> hotelsList = new ArrayList();

        for(DocumentSnapshot document : future.get().getDocuments())
        {
            Hotels hotels = documentSnapshotToHotels(document);
            if(hotels != null)
                hotelsList.add(hotels);
        }
        return hotelsList;
    }
    public Hotels getHotelsById(String hotelId) throws ExecutionException, InterruptedException {
        CollectionReference hotelsCollection = firestore.collection("Hotels");
        ApiFuture<DocumentSnapshot> future = hotelsCollection.document(hotelId).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToHotels(document);

    }

}
