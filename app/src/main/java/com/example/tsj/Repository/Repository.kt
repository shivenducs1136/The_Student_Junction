package com.example.tsj.Repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.tsj.`interface`.apirequest
import com.example.tsj.api.RetrofitInstance
import com.example.tsj.model.Article
import com.example.tsj.model.Feeds

class Repository{
    val showProgress = MutableLiveData<Boolean>()
    suspend fun getFeed(): Feeds{
        showProgress.value = true
        return RetrofitInstance.api.getFeed()
    }
}