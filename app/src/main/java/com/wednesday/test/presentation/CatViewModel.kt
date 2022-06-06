package com.wednesday.test.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wednesday.test.common.Response
import com.wednesday.test.domain.model.Cat
import com.wednesday.test.domain.repository.CatRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class CatViewModel(private val repository: CatRepository): ViewModel() {

    private var _response: MutableLiveData<Response<List<Cat>>> = MutableLiveData()
    val response: LiveData<Response<List<Cat>>> = _response

    init {
        //Call our api to get a list of cute cat images
        viewModelScope.launch {
            getCuteCats()
        }
    }

    //A function to refresh the cats data
    suspend fun refreshCats() = getCuteCats()

    suspend fun getCuteCats() {
        try {
            //First of all, we will notify our users that we are fetching a list of cute cats
            _response.postValue(Response.Loading())
            //Get a list of cute cats from our api
            val cats = repository.getCuteCatImages()
            // Return a success state with our list of cats
            _response.postValue(Response.Success(data = cats))
        } catch (e: HttpException) {
            //Return an error state with a message of the exception
            _response.postValue(Response.Error(
                message = e.localizedMessage ?: "A Network Error occurred!"
            ))
        } catch (e: IOException) {
            //Return and error state with Network unavailable
            _response.postValue(Response.Error(message = "Network Unavailable"))
        }
    }
}