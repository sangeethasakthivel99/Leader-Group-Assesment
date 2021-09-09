package com.sangeetha.leadertask.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sangeetha.leadertask.R
import com.sangeetha.leadertask.data.remote.model.ContactsItem
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(
    private var contacts: MutableList<ContactsItem>,
    private var itemClickListener: ItemClickListener?
): RecyclerView.Adapter<ContactAdapter.ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)

        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val item = contacts[position]
        holder.bind(item, itemClickListener)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class ContactsViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        fun bind(item: ContactsItem, itemClickListener: ItemClickListener?) {
            itemView.name.text = item.name
            itemView.phoneNumber.text = item.phone
            itemView.avatarSymbol.text = item.name.first().toString()
            itemView.setOnClickListener {
                itemClickListener?.onItemClicked(item)
            }
        }
    }

    fun addData(list: List<ContactsItem>) {
        contacts.addAll(list)
    }

    fun onDestroy() {
        itemClickListener = null
    }
}

interface ItemClickListener {
    fun onItemClicked(contactsItem: ContactsItem)
}
