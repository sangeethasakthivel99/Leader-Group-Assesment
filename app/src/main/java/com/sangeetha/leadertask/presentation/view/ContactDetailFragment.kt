package com.sangeetha.leadertask.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.sangeetha.leadertask.R
import com.sangeetha.leadertask.data.remote.model.ContactsItem
import kotlinx.android.synthetic.main.fragment_contact_detail.*

class ContactDetailFragment: Fragment() {

    private val contact: ContactsItem? by lazy {
        requireArguments().getParcelable("ContactItem")
    }

    companion object {
        fun getNewInstance(contactsItem: ContactsItem): ContactDetailFragment {
            return ContactDetailFragment().apply {
                arguments = bundleOf("ContactItem" to contactsItem)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return View.inflate(context, R.layout.fragment_contact_detail, null)
    }

    override fun onResume() {
        super.onResume()
        setUpUI()
    }

    private fun setUpUI() {
        require(contact != null)
        contact?.let {
            name.text = it.name
            phoneNumber.text = it.phone
            email.text = it.email
            username.text = it.username
            avatarInitial.text = it.name.first().toString()
            website.text = it.website
            streetName.text = it.address.street
            city.text = it.address.city
            zipCode.text = it.address.zipcode
            suite.text = it.address.suite
            companyAddress.text = it.company.name
            catchPhrase.text = it.company.catchPhrase
            business.text = it.company.bs
        }
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showViews()
    }
}
