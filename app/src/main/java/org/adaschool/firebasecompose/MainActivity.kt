package org.adaschool.firebasecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.adaschool.firebasecompose.ui.theme.FirebaseComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onMessageClicked: (String) -> Unit = { message ->
            Log.d("Developer", "Message: $message")
        }
        setContent {
            FirebaseComposeTheme {
                MessagingApp {
                    MessageForm(onMessageClicked)
                }

            }
        }
    }

}

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
            modifier = Modifier.padding(10.dp).weight(0.8f),
            value = messageTextField.value,
            placeholder = { Text(text = "Enter message") },
            onValueChange = { messageTextField.value = it }

        )
        Button(modifier = Modifier.padding(10.dp).weight(0.4f),

            onClick = { onSendMessage(messageTextField.value.text) }) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val onMessageClicked: (String) -> Unit = { message ->
        Log.d("Developer", "Message: $message")
    }

    FirebaseComposeTheme {
        MessagingApp {
            MessageForm(onMessageClicked)
        }
    }
}