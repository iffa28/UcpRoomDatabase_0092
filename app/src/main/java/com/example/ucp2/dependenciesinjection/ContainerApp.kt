package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.database.TokoDatabase
import com.example.ucp2.repository.LocalRepositoryBrg
import com.example.ucp2.repository.LocalRepositorySplr
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.repository.RepositorySplr

interface InterfaceContainerApp{
    val repositoryBrg: RepositoryBrg
    val repositorySplr: RepositorySplr
}
