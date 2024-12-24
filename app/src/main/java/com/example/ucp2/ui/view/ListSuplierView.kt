package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R
import com.example.ucp2.data.entity.Suplier
import com.example.ucp2.ui.viewmodel.ListSuplierUiState
import kotlinx.coroutines.launch


@Composable
fun BodyListSplrView(
    listSuplierUiState: ListSuplierUiState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        listSuplierUiState.isLoading -> {
            //menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        listSuplierUiState.isError -> {
            LaunchedEffect(listSuplierUiState.errorMessage) {
                listSuplierUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        listSuplierUiState.listSplr.isEmpty() -> {
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
            ListSuplier(
                listSplr = listSuplierUiState.listSplr,
                modifier = modifier
            )
        }

    }
}


@Composable
fun ListSuplier(
    listSplr: List<Suplier>,
    modifier: Modifier = Modifier,
) {
    //agar kolomnya bisa di scroll
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listSplr,
            itemContent = { splr ->
                CardSplr(
                    splr = splr,
                )
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSplr(
    splr: Suplier,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.padding(5.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.usr),
                    contentDescription = "", Modifier
                        .size(75.dp)
                )
            }
            Spacer(modifier = Modifier.padding(2.dp))

            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = splr.namaSplr,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    Column {
                        Icon(imageVector = Icons.Filled.Call, contentDescription = "", Modifier.size(16.dp))

                    }
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(text = splr.kontak,
                        fontSize = 15.sp,
                    )
                }

                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically)
                {
                    Column {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = "", Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.padding(1.dp))
                    Text(text = splr.alamat,
                        fontSize = 15.sp
                    )

                }

            }

        }


    }

}