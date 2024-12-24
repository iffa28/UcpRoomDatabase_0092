package com.example.ucp2.data.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.ListSplrViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel

object ListSuplierByNama {
    @Composable
    fun DataSuplier(
        viewModel: ListSplrViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String>
    {
        val getListNama by viewModel.listSuplierUiState.collectAsState()
        val splrNama = getListNama.listSplr.map { it.namaSplr }
        return splrNama

    }
}