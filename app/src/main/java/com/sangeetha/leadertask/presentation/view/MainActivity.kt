package com.sangeetha.leadertask.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sangeetha.leadertask.R
import com.sangeetha.leadertask.data.remote.model.ContactsItem
import com.sangeetha.leadertask.core.Status
import com.sangeetha.leadertask.presentation.view.adapter.ContactAdapter
import com.sangeetha.leadertask.presentation.view.adapter.ItemClickListener
import com.sangeetha.leadertask.presentation.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemClickListener {

    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        contactRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContactAdapter(arrayListOf(), this)
        contactRecyclerView.addItemDecoration(
            DividerItemDecoration(
                contactRecyclerView.context,
                (contactRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        contactRecyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.users.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    contactRecyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    contactRecyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<ContactsItem>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClicked(contactsItem: ContactsItem) {
        hideViews()
        attachContactDetailFragment(contactsItem)
    }

    private fun hideViews() {
        progressBar.visibility = View.GONE
        contactRecyclerView.visibility = View.GONE
        heading.visibility = View.GONE
    }

    private fun attachContactDetailFragment(contactsItem: ContactsItem) {
        container.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, ContactDetailFragment.getNewInstance(contactsItem = contactsItem))
            addToBackStack("ContactDetailFragment")
            commit()
        }
    }

    fun showViews() {
        contactRecyclerView.visibility = View.VISIBLE
        heading.visibility = View.VISIBLE
        container.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.onDestroy()
    }
}
