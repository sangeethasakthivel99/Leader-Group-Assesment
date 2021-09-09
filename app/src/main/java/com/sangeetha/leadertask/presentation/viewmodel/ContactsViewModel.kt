package com.sangeetha.leadertask.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sangeetha.leadertask.data.remote.model.ContactsItem
import com.sangeetha.leadertask.data.repository.ContactsRepository
import com.sangeetha.leadertask.core.NetworkHelper
import com.sangeetha.leadertask.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val repository: ContactsRepository
): ViewModel() {

    val users = MutableLiveData<Resource<List<ContactsItem>>>()

    init {
        fetchContacts()
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            repository.fetchContacts()
            repository.getSavedContacts().collect {
                if (it.isEmpty()) {
                    users.postValue(Resource.error("Empty List", null))
                } else {
                    users.postValue(Resource.success(it))
                }
            }
        }
    }
}