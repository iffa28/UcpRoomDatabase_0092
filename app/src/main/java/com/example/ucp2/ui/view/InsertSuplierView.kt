package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ucp2.ui.viewmodel.FormSplrErrorState
import com.example.ucp2.ui.viewmodel.SplrUIState
import com.example.ucp2.ui.viewmodel.SuplierEvent

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