package org.adaschool.firebasecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import org.adaschool.firebase.compose.R
import org.adaschool.firebasecompose.ui.theme.FirebaseComposeTheme

const val TAG = "Developer"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val db = Firebase.firestore
        val messagesCollection = db.collection("messages")
        val messagesListState = SnapshotStateList<Message>()


        messagesCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                val messagesList = snapshot.toObjects<Message>()
                messagesListState.clear()
                messagesListState.addAll(messagesList)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }

        val onMessageClicked: (String) -> Unit = { message ->
            Log.d("Developer", "Message: $message")

            messagesCollection
                .add(Message(message))
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error adding document", e)
                }

        }


        setContent {
            FirebaseComposeTheme {
                MessagingApp {
                    Column {
                        MessageForm(onMessageClicked)
                        MessagesList(messagesListState)
                    }

                }
            }
        }
    }

}

data class Message(var text: String? = "")

@Composable
fun MessagingApp(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.onPrimary
    ) {
        content()
    }
}

@Composable
fun MessageForm(onSendMessage: (String) -> Unit) {
    val messageTextField = remember { mutableStateOf(TextFieldValue()) }
    Row(verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .padding(10.dp)
                .weight(0.8f),
            value = messageTextField.value,
            placeholder = { Text(text = "Enter message") },
            onValueChange = { messageTextField.value = it }

        )
        Button(modifier = Modifier
            .padding(10.dp)
            .weight(0.4f),

            onClick = {
                onSendMessage(messageTextField.value.text)
                messageTextField.value = TextFieldValue()
            }) {
            Text(text = "Send")
        }
    }
}

@Composable
fun MessagesList(messages: SnapshotStateList<Message>) {

    LazyColumn {
        items(messages) { message ->
            MessageRow(message.text!!)
        }
    }
}

@Composable
fun MessageRow(message: String) {
    Surface(
        color = colorResource(id = R.color.alligator_green),
        modifier = Modifier.padding(15.dp, 6.dp, 15.dp, 0.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.white)),
        shape = RoundedCornerShape(5.dp)
    )
    {
        Text(
            text = message, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white),
                fontSize = 16.sp
            )
        )
        Divider()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val onMessageClicked: (String) -> Unit = { message ->
        Log.d("Developer", "Message: $message")
    }
    val messagesListState = SnapshotStateList<Message>()

    FirebaseComposeTheme {
        MessagingApp {
            Column {
                MessageForm(onMessageClicked)
                MessagesList(messagesListState)
            }

        }
    }
}