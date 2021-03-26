package com.hilt.hiltsampleproject.ui.detailpost

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import com.hilt.hiltsampleproject.model.Comments
import com.hilt.hiltsampleproject.model.CommentsItem
import com.hilt.hiltsampleproject.network.Resource
import com.hilt.hiltsampleproject.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val mainRepository: MainRepository, private val state: SavedStateHandle) : ViewModel() {
    private val _res = MutableLiveData<Resource<List<CommentsItem>>>()

    val res: LiveData<Resource<List<CommentsItem>>>
        get() = _res

    init {
        //  var commentsDetails = state.get<CommentsItem>("postdetails")
        getPostDetailsFromApi(1)
    }

    private fun getPostDetailsFromApi(postId: Int?) = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getCommentDetails(postId).let {
            getPostDetailsFromDatabase(it)
        }
    }

    private fun getPostDetailsFromDatabase(posts: Comments) = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getLocalOfflineCommentDetails(posts).let {
            _res.postValue(Resource.success(it!!))
        }
    }
}
