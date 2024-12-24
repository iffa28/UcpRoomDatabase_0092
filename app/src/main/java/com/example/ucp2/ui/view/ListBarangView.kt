package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.ListBarangUiState
import com.example.ucp2.ui.viewmodel.ListBrgViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun ListBarangView(
    onBack: () -> Unit,
    viewModel: ListBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "DAFTAR BARANG",
                modifier = modifier
            )
        },
        snackbarHost = { SnackbarHost(remember { SnackbarHostState() }) }
    ) { innerPadding ->
        val listBarangUiState by viewModel.listBarangUIState.collectAsState()
        BodyListBrgView(
            listBarangUiState = listBarangUiState,
            onClick = {onDetailClick(it)},
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
fun BodyListBrgView(
    listBarangUiState: ListBarangUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        listBarangUiState.isLoading -> {
            //menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        listBarangUiState.isError -> {
            //menampilkan pesan error
            LaunchedEffect(listBarangUiState.errorMessage) {
                listBarangUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message) //tampilkan Snackbar
                    }
                }
            }
        }

        listBarangUiState.listBrg.isEmpty() -> {
            //menampilkan pesan jika data kosong
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Tidak ada data Suplier.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            //Menampilkan daftar mahasiswa
            ListBarang(
                listBrg = listBarangUiState.listBrg,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }

    }
}

@Composable
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    //agar kolomnya bisa di scroll
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listBrg ,
            itemContent = { brg ->
                CardBrg(
                    brg = brg,
                    onClick = { onClick(brg.id.toString())}

                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBrg(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
){
    val cardColors = getCardColorBasedOnStock(brg.stok)
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = cardColors

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.boxes),
                        contentDescription = "", Modifier
                            .size(90.dp)
                            .padding(5.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))

                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(text = brg.nama,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "", Modifier.size(18.dp))
                        Text(text = brg.namaSuplier,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "", Modifier.size(18.dp))
                        Text(text = brg.deskripsi,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "", Modifier.size(18.dp))
                        Text(text = brg.stok.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }


                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Text(text = brg.harga,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Column(Modifier.fillMaxWidth(),horizontalAlignment = Alignment.End) {
                            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "", Modifier.size(30.dp))

                        }
                    }

                }


            }

        }

    }

}

@Composable
fun getCardColorBasedOnStock(stok: Int): androidx.compose.material3.CardColors {
    val color = when {
        stok == 0 -> Color(0xFFB0B0B0)
        stok in 1..10 -> Color(0xFFAF1740)
        else -> Color(0xFF557C56)
    }
    return CardDefaults.cardColors(containerColor = color)
}