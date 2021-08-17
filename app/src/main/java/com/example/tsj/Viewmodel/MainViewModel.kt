package com.example.tsj.Viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tsj.Repository.Repository
import com.example.tsj.model.Article
import com.example.tsj.model.Feeds
import com.example.tsj.model.feed
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Feeds> = MutableLiveData()
    lateinit var showProgress: LiveData<Boolean>
    init {
        showProgress = repository.showProgress
    }
    fun getFeed(context: Context){
        viewModelScope.launch {
            val response: Feeds = repository.getFeed()
            myResponse.value = response
        }
    }

}