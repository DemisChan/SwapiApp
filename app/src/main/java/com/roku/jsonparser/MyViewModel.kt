package com.roku.jsonparser

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.roku.jsonparser.data.model.ListOfApps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class MyViewModel : ViewModel() {
    private val _myData = MutableStateFlow<ListOfApps?>(null)
    val myData: StateFlow<ListOfApps?> = _myData

    init {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                fetchJsonData(baseUrl)
            }
            _myData.value = data

        }
    }
}


fun fetchJsonData(url: String): ListOfApps? {
    return try {
        val response = URL(url).readText()
        Gson().fromJson(response, ListOfApps::class.java)
    } catch (e: Exception) {
        Log.e("-------------->", "Exception Caught", e)
        null
    }
}
