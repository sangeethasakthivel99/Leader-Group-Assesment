package com.sangeetha.leadertask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sangeetha.leadertask.data.remote.model.ContactsItem
import com.sangeetha.leadertask.repository.ContactsRepository
import com.sangeetha.leadertask.util.NetworkHelper
import com.sangeetha.leadertask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val repository: ContactsRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {

    val users = MutableLiveData<Resource<List<ContactsItem>>>()

    var contactDetail = MutableLiveData<ContactsItem>()

    init {
        fetchContacts()
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                repository.getContacts().also {
                    if (it.isSuccessful) {
                        users.postValue(Resource.success(it.body()))
                    } else users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else {
                val data = repository.getSavedContacts()
                data.collect {
                    users.postValue(Resource.success(it))
                }
            }
        }
    }
}