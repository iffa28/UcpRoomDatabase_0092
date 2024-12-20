package com.example.ucp2.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun TopAppBarHome(
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
    ) {
        Column(modifier=Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.Ctophome),
                shape = RoundedCornerShape(bottomEnd = 85.dp))
        )
        {
            Row(Modifier.padding(10.dp)) {
                Column(Modifier.padding(10.dp)) {
                    Icon(
                        Icons.Filled.Menu, contentDescription = "",
                        Modifier.padding(3.dp) .size(45.dp),
                        tint = Color.White
                    )
                    Column(Modifier.padding(8.dp)) {
                        Text(
                            text = "Inventaris",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 45.sp
                            )
                        )
                        Text(
                            text = "Toko Skz",
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 30.sp)
                        )

                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.End)
                {
                    Image(
                        painter = painterResource(id = R.drawable.logoskz),
                        contentDescription = "", Modifier.size(80.dp)
                            .clip(CircleShape)

                    )


                }


            }

        }


    }


}
