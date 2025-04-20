package com.example.shop.components

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.parcelize.Parcelize

@Composable
fun FormDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (FormState) -> Unit,
    onFormStateChange: (FormState) -> Unit,
    title: String,
    formState: FormState
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp
                )
                OutlinedTextField(
                    placeholder = { Text(text = "Enter item name") },
                    value = formState.name,
                    onValueChange = { onFormStateChange(formState.copy(name = it)) },
                    label = { Text(text = "Name") },
                    singleLine = true,
                )
                OutlinedTextField(
                    placeholder = { Text(text = "Enter item price") },
                    value = formState.price,
                    onValueChange = { onFormStateChange(formState.copy(price = it)) },
                    label = { Text(text = "Price") },
                    singleLine = true
                )
                OutlinedTextField(
                    placeholder = { Text(text = "Enter item description") },
                    value = formState.description,
                    onValueChange = { onFormStateChange(formState.copy(description = it)) },
                    label = { Text(text = "Description") },
                    minLines = 3
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation(formState) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Parcelize
data class FormState(
    val id: Long? = null,
    val name: String = "",
    val price: String = "",
    val description: String = ""
): Parcelable