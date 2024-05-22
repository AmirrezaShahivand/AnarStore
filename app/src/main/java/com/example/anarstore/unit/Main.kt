package com.example.anarstore.unit

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.appwrite.Client
import io.appwrite.ID
import io.appwrite.services.Account
import io.appwrite.services.Databases
import kotlinx.coroutines.launch

//@SuppressLint("CoroutineCreationDuringComposition")
//    @Composable
//    fun Main(context: Context) {
//        val coroutineScope = rememberCoroutineScope()
//
//
//
//        coroutineScope.launch {
//
//            val client = Client(context)
//                .setEndpoint("https://cloud.appwrite.io/v1")
//                .setProject("6639346500317cdc0561")
//                .setSelfSigned(status = true)
//
//            val databases = Databases(client)
//
//            try {
//                val document = databases.createDocument(
//                    databaseId = "66393cd9001965bf1007",
//                    collectionId = "66393cdf0010160d88e0",
//                    documentId = ID.unique(),
//                    data = mapOf("name" to "va")
//
//                )
//
//            } catch (e: Exception) {
//                Log.v("Appwrite", "Error: " + e.message)
//            }

//        try {
//            val documents = databases.listDocuments(
//                databaseId = "6634ec0b003a439a6f38",
//                collectionId = "6634fabc002a96828ee3",
//                queries = listOf(
//                    Query.equal("jb", "ka")
//                )
//            )
//            Log.v("test1" , documents.documents.toString())
//
//        } catch (e: AppwriteException) {
//            Log.e("Appwrite", "Error: " + e.message)
//        }


 //           val account = Account(client)
//sign up
//        val user = account.create(
//            userId = "AmirrezaSHa",
//            email = "email@example.com",
//            password = "password"
//        )


            //sign in
//
//        val session = account.createEmailPasswordSession(
//            email = "email@example.com",
//            password = "password"
//        )
//
//
//        try {
//            val user = account.get()
//            // Logged in
//            Toast.makeText(context, "yes!", Toast.LENGTH_SHORT).show()
//        } catch (e: AppwriteException) {
//            // Not logged in
//            Toast.makeText(context, "no!", Toast.LENGTH_SHORT).show()
//        }


 //       }
 //   }