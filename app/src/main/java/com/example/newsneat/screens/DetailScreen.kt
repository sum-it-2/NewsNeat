package com.example.newsneat.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import androidx.core.net.toUri
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImageScope
import com.example.newsneat.MyViewModel.MyViewModel
import com.example.newsneat.ui.theme.Red

@Composable
fun DetailScreen(
    title: String? = "not available",
    description: String?= "not available",
    content: String?= "not available",
    publishedAt: String?= "not available",
    url: String?= "not available",
    urlToImage: String?= "not available",
    padding: PaddingValues
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){

        if (title != null) {
            Text(
                text = title,
                fontSize = 24.sp,
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 35.dp, bottom = 8.dp),
                color = Color.White

            )
        }
        Column(modifier = Modifier.padding(top = 16.dp)){


            SubcomposeAsyncImage(
                model = urlToImage, contentDescription = null, modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 8.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                        CircularProgressIndicator()
                }
                }
            )
            if (publishedAt != null) {
                Text(
                    text = publishedAt,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.End
                )
            }
            Text(text = description + content, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))
            Text(text = "To read the full news click on the button below", fontStyle = FontStyle.Italic, modifier = Modifier.padding(start = 8.dp))
            ElevatedButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, url?.toUri())
                    context.startActivity(intent)
                },
                content = { Text("Visit Site") },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp,pressedElevation = 10.dp),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            )
        }
    }

}