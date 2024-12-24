package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.TokoApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            InsertSuplierViewModel(
                tokoApp().containerApp.repositorySplr
            )
        }
        initializer {
            InsertBarangViewModel(
                tokoApp().containerApp.repositoryBrg,
            )
        }

        initializer {
            ListSplrViewModel(
                tokoApp().containerApp.repositorySplr
            )
        }

        initializer {
            ListBrgViewModel(
                tokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }

    }
}

fun CreationExtras.tokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)