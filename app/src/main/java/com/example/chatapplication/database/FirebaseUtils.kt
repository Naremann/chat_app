package com.example.chatapplication.database

import com.example.chatapplication.model.AppUser
import com.example.chatapplication.model.Message
import com.example.chatapplication.model.Room
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
fun addMessage(message:Message,
               onSuccessListener: OnSuccessListener<Void>,
               onFailureListener: OnFailureListener){

    val messageCollectionRef = message.roomId?.let { getMessageReference(it) }
    val messageRef = messageCollectionRef?.document()
    message.messageId=messageRef?.id
        messageRef?.set(message)
        ?.addOnSuccessListener(onSuccessListener)
        ?.addOnFailureListener(onFailureListener)
}


fun getMessageReference(roomId: String): CollectionReference {
    val collectionRef = getCollection(Room.COLLECTION_NAME)
    val roomRef = collectionRef.document(roomId)
    return roomRef.collection(Message.COLLECTION_NAME)
}




