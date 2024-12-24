package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.ucp2.R
import com.example.ucp2.ui.customwidget.TopAppBarHome

@Composable
fun HomeView(
    onTambahSplrClick: () -> Unit = {},
    onDaftarSuplierClick: () -> Unit = {},
    onTambahBrgClick: () -> Unit = {},
    onDaftarBrgClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarHome()
        }

    ) { innerPadding ->
        BodyHomeView(
            onAddSuplierClick = {onTambahSplrClick()},
            onAddProductClick = {onTambahBrgClick()},
            onSuplierListClick = {onDaftarSuplierClick()},
            onProductListClick = {onDaftarBrgClick()},
            modifier = modifier.padding(innerPadding)
        )

    }
}

@Composable
fun BodyHomeView(
    onProductListClick: () -> Unit = {},
    onAddProductClick: () -> Unit = {},
    onSuplierListClick: () -> Unit = {},
    onAddSuplierClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(){
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            ElevatedCard(
                onClick = onProductListClick,
                modifier = modifier
                    .padding(5.dp)
                    .size(165.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.asset),
                        contentDescription = "", Modifier
                            .size(80.dp)
                            .padding(5.dp)
                    )
                    Text(text = "List Barang",
                        style = TextStyle(
                            color = Color.DarkGray
                        )
                    )
                }
            }

            ElevatedCard(
                onClick = onAddProductClick,
                modifier = modifier
                    .padding(5.dp)
                    .size(165.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kotak),
                        contentDescription = "", Modifier
                            .size(80.dp)
                            .padding(5.dp)
                    )
                    Text(text = "Tambah Barang",
                        style = TextStyle(
                            color = Color.DarkGray
                        )
                    )
                }
            }
        }
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier
                .padding(top = 200.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(20 .dp)) {
                ElevatedCard(
                    onClick = onSuplierListClick,
                    modifier = modifier
                        .padding(5.dp)
                        .size(165.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.listsuplier),
                            contentDescription = "", Modifier
                                .size(80.dp)
                                .padding(5.dp)
                        )
                        Text(text = "List Suplier",
                            style = TextStyle(
                                color = Color.DarkGray
                            )
                        )
                    }
                }

                ElevatedCard(
                    onClick = onAddSuplierClick,
                    modifier = modifier
                        .padding(5.dp)
                        .size(165.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.addsplr),
                            contentDescription = "", Modifier
                                .size(80.dp)
                                .padding(5.dp)
                        )
                        Text(text = "Tambah Suplier",
                            style = TextStyle(
                                color = Color.DarkGray
                            )
                        )
                    }
                }
            }

        }

    }

}

