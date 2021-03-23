package com.hilt.hiltsampleproject.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilt.hiltsampleproject.model.Posts
import com.hilt.hiltsampleproject.network.Resource
import com.hilt.hiltsampleproject.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel()
{
    private val _res = MutableLiveData<Resource<Posts>>()

    val res : LiveData<Resource<Posts>>
        get() = _res

    init {
        getEmployees()
    }

    private fun getEmployees()  = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getPostDetails().let {
            if (it.isSuccessful){
                _res.postValue(Resource.success(it.body()!!))
            }else{
                _res.postValue(Resource.error(null,"error"))
            }
        }
    }
}