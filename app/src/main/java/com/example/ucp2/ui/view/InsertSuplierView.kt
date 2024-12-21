package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.FormSplrErrorState
import com.example.ucp2.ui.viewmodel.InsertSuplierViewModel
import com.example.ucp2.ui.viewmodel.SplrUIState
import com.example.ucp2.ui.viewmodel.SuplierEvent
import com.example.ucp2.ui.viewmodell.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertSuplierView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertSuplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState  //Ambil UI state dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                //tampilkan snackbar
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )

            //isi body
            InsertBodySplr(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    //update state di viewmodel
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    viewModel.saveData()
                    if (uiState.snackBarMessage == "Data berhasil disimpan") {
                        onNavigate()
                    }
                }
            )

        }
    }
}

@Composable
fun InsertBodySplr(
    modifier: Modifier = Modifier,
    onValueChange: (SuplierEvent) -> Unit,
    uiState: SplrUIState,
    onClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            suplierEvent = uiState.suplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }

    }

}

@Composable
fun FormSuplier(
    suplierEvent: SuplierEvent, //ini data class di viewmodel
    onValueChange: (SuplierEvent) -> Unit = {},
    errorState: FormSplrErrorState = FormSplrErrorState(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.namaSuplier,
            onValueChange = {
                onValueChange(suplierEvent.copy(namaSuplier = it))
            },
            label = { Text("Nama Suplier") },
            isError = errorState.namaSuplier != null,
            placeholder = { Text("Masukkan nama suplier") }
        )
        Text(
            text = errorState.namaSuplier ?: "",  //pesan error
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = {
                onValueChange(suplierEvent.copy(kontak = it))
            },
            label = { Text("Kontak") },
            isError = errorState.kontak != null,
            placeholder = { Text("Masukkan kontak") }
        )
        Text(
            text = errorState.kontak ?: "",  //pesan error
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = {
                onValueChange(suplierEvent.copy(alamat = it))
            },
            label = { Text("Alamat Suplier") },
            isError = errorState.alamat != null,
            placeholder = { Text("Masukkan alamat suplier") }
        )
        Text(
            text = errorState.alamat ?: "",  //pesan error
            color = Color.Red
        )

    }
}