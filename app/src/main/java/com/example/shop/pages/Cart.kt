package com.example.shop.pages

import CustomExpandableFAB
import FABItem
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.components.FormDialog
import com.example.shop.components.FormState
import com.example.shop.components.ProductItem
import com.example.shop.models.Product
import com.example.shop.service.ProductViewModel

@Composable
fun CartPage(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    val context = LocalContext.current.applicationContext
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val allProducts by productViewModel.getAllProducts.observeAsState(emptyList())
    val searchedProducts by productViewModel.getProductByName(searchQuery).observeAsState(emptyList())
    val products = if (searchQuery.isBlank()) allProducts else searchedProducts
    var showDialog by remember { mutableStateOf(false) }
    var formState by rememberSaveable { mutableStateOf(FormState()) }

    val itemList = listOf(
        FABItem(icon = Icons.Rounded.Add, text = "Add"),
    )

    if(showDialog) {
        FormDialog(
            title = if (formState.id != null) "Edit Item" else "Add Item",
            formState = formState,
            onFormStateChange = { formState = it },
            onConfirmation = { formState ->
                if (formState.name.isBlank() || formState.price.isBlank() || formState.description.isBlank()) {
                    Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show()
                    return@FormDialog
                }

                val priceValue = formState.price.toDoubleOrNull()
                if (priceValue == null) {
                    Toast.makeText(context, "Price must be a valid number!", Toast.LENGTH_SHORT).show()
                    return@FormDialog
                }

                if (formState.id == null) {
                    productViewModel.addProduct(
                        name = formState.name,
                        description = formState.description,
                        price = priceValue,
                    )
                    Toast.makeText(context, "New item added!", Toast.LENGTH_SHORT).show()
                } else {
                    productViewModel.updateProduct(
                        Product(
                            id = formState.id,
                            name = formState.name,
                            description = formState.description,
                            price = priceValue,
                        )
                    )
                    Toast.makeText(context, "Item updated!", Toast.LENGTH_SHORT).show()
                }

                showDialog = false
            },
            onDismissRequest = {
                showDialog = false
                formState = FormState()
            },
        )
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Items",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Search by product name…") },
            singleLine = true
        )

        if (products.isEmpty()) {
            Text(
                text = if (searchQuery.isBlank()) "No items yet" else "Search not found",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(products) { product ->
                    ProductItem(
                        product,
                        onEdit = { product ->
                            formState = FormState(
                                id = product.id,
                                name = product.name,
                                description = product.description,
                                price = product.price.toString()
                            )
                            showDialog = true
                        },
                        onDelete = {
                            productViewModel.deleteProductById(product.id)
                        }
                    )
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        CustomExpandableFAB(
            items = itemList,
            onItemClick = { item ->
                when (item.text) {
                    "Add" -> {
                        formState = FormState()
                        showDialog = true
                    }
                }
                          },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }
}



