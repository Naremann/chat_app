package com.example.chatapplication.database

import android.util.Log
import com.example.chatapplication.model.AppUser
import com.example.chatapplication.model.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getCollection(collectionName:String) : CollectionReference{
    val db = Firebase.firestore
    return db.collection(collectionName)


}
fun addUserToFireStore(user : AppUser,onSuccessListener: OnSuccessListener<Void>,onFailureListener: OnFailureListener){
   getCollection(AppUser.COLLECTION_NAME)
    val doc = getCollection(AppUser.COLLECTION_NAME).document(user.id!!)
    doc.set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}
fun getUserFromFireStore(userId:String,onSuccessListener: OnSuccessListener<DocumentSnapshot>
                         ,onFailureListener: OnFailureListener){
    val doc = getCollection(AppUser.COLLECTION_NAME).document(userId).get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}

fun addRoom(room: Room,onFailureListener: OnFailureListener,onSuccessListener: OnSuccessListener<Void>){
    val collection = getCollection(Room.COLLECTION_NAME)
    val doc = collection.document()
    room.id=doc.id
    doc.set(room).addOnFailureListener(onFailureListener).addOnSuccessListener(onSuccessListener)
}

fun getRoom(onSuccessListener: OnSuccessListener<QuerySnapshot>,onFailureListener: OnFailureListener){
    val doc = getCollection(Room.COLLECTION_NAME).get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}