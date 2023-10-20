package com.booking.booking.service;

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
public class UsersService {
    private Firestore firestore;
    public UsersService() {this.firestore = FirestoreClient.getFirestore();}

    public Users documentSnapshotToUsers(DocumentSnapshot document)
    {
        Users users = null;
        if(document.exists())
        {
            //return document.toObject(Users.class);
            users = new Users (document.getId(), document.getString("name"), document.getString("email"),document.getString("phone"),
                        document.getString("address"), document.toObject(Users.class).getPaymentInformation(),document.getTimestamp("createdAt"));
        }
        return users;
    }


    public ArrayList<Users> getAllUsers() throws ExecutionException, InterruptedException
    {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = usersCollection.get();

        ArrayList<Users> usersList = new ArrayList();

        for(DocumentSnapshot document : future.get().getDocuments())
        {
            Users users = documentSnapshotToUsers(document);
            if(users != null)
                usersList.add(users);
        }
        return usersList;
    }
    public Users getUsersById(String userId) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = usersCollection.document(userId).get();
        DocumentSnapshot document = future.get();

        return documentSnapshotToUsers(document);

    }




}
