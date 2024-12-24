package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.model.ListSuplierByNama
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.BarangEvent
import com.example.ucp2.ui.viewmodel.BrgUIState
import com.example.ucp2.ui.viewmodel.FormErrorState
import com.example.ucp2.ui.viewmodel.InsertBarangViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertBarangViewModel = viewModel(factory = PenyediaViewModel.Factory)  //inisialisasi viewmode
){
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
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "TAMBAH BARANG",
                modifier = modifier
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            //isi body
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = {
                        updatedEvent ->
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
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),  //ini data class di viewmodel
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier)
    {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama") }
        )
        Text(
            text = errorState.nama ?: "",  //pesan error
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi") }
        )
        Text(
            text = errorState.deskripsi ?: "",  //pesan error
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("Harga") },
            isError = errorState.harga != null,
            placeholder = { Text("Masukkan Harga") }
        )
        Text(
            text = errorState.harga ?: "",  //pesan error
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = { Text("Stok") },
            isError = errorState.stok != null,
            placeholder = { Text("Masukkan Stok") }
        )
        Text(
            text = errorState.stok ?: "",  //pesan error
            color = Color.Red
        )

        SelectDropDown(
            modifier = Modifier,
            selectedValue = barangEvent.namaSuplier,
            label = "Nama Suplier",
            onValueChange = {
                    selectedSplr ->
                onValueChange(barangEvent.copy(namaSuplier = selectedSplr))
            },
            dropDownItem = ListSuplierByNama.DataSuplier()
        )

        Text(
            text = errorState.namaSuplier ?: "",  //pesan error
            color = Color.Red
        )


    }

}

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    onClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDropDown(
    selectedValue: String,
    dropDownItem: List<String>,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier

) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded =! expanded},
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label )},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            dropDownItem.forEach { pilihan : String ->
                DropdownMenuItem(
                    text =  {
                        Text(text = pilihan)
                    },
                    onClick = {
                        expanded = false
                        onValueChange(pilihan)
                    }
                )
            }
        }
    }
}
