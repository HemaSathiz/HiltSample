package com.hilt.hiltsampleproject.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilt.hiltsampleproject.model.PostDetailsItem
import com.hilt.hiltsampleproject.model.Posts
import com.hilt.hiltsampleproject.network.Resource
import com.hilt.hiltsampleproject.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _res = MutableLiveData<Resource<List<PostDetailsItem>>>()

    val res: LiveData<Resource<List<PostDetailsItem>>>
        get() = _res

    init {
        getPostDetailsFromApi()
    }

    private fun getPostDetailsFromApi() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getPostDetails().let {
            getPostDetailsFromDatabase(it)
        }
    }

    private fun getPostDetailsFromDatabase(posts: Posts) = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getLocalOfflinePostDetails(posts).let {
            _res.postValue(Resource.success(it!!))
        }
    }
}
