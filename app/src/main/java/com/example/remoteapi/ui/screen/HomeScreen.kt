package com.example.remoteapi.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.remoteapi.domain.model.Beer
import com.example.remoteapi.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    onClickDetails: () -> Unit,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello World",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Button(onClick = onClickDetails) {
            Text(text = "Show Details")
        }
    }
}

/*
@Composable
fun BeerScreen(
    beers: LazyPagingItems<Beer>
){
    val context = LocalContext.current

    LaunchedEffect(key1 = beers.loadState){
        if(beers.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: ${(beers.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun BeerItem(
    beer: Beer,
    modifier: Modifier = Modifier
){

}

@Preview
@Composable
fun
*/
