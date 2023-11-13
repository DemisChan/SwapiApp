package com.roku.jsonparser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.roku.jsonparser.data.model.ListOfApps
import com.roku.jsonparser.ui.theme.JsonParserTheme


const val baseUrl = "https://rokumobileinterview.s3.us-west-2.amazonaws.com/apps.json"
const val url = "https://rokumobileinterview.s3.us-west-2.amazonaws.com/"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JsonParserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val viewModel: MyViewModel = viewModel()
    val rokuData = viewModel.myData.collectAsState()
    AppListView(rokuData.value)
}

@Composable
fun AppListView(myData: ListOfApps?) {
    LazyColumn {
        myData?.let {
            items(it.apps) { app ->
                val imageUrl = url + app.imageUrl
                Text(text = app.name.replaceFirstChar { first ->
                    first.uppercaseChar()
                })
                AsyncImage(imageUrl, null)
                Text(text = app.id)
                Text(text = app.imageUrl)


            }
        }
    }
}




