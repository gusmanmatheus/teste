package com.example.teste.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.teste.data.model.User
import com.example.teste.databinding.UserItemListBinding

class AdapterRC : RecyclerView.Adapter<AdapterRC.ViewHolder>(), Filterable {
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                val filterResult = FilterResults()
                filterResult.values = filterUser(charString)
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFilted = results?.values as MutableList<User>
                notifyDataSetChanged()
            }

        }
    }

    var listFilted = mutableListOf<User>()
    var data = mutableListOf<User>()
    var onItemClick: ((User) -> Unit) = { }

    fun setValues(data: MutableList<User>) {
        this.data = data
        listFilted = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFilted.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFilted[position])
    }

    inner class ViewHolder(private var binding: UserItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listFilted.getOrNull(adapterPosition)?.let { user ->
                    onItemClick.invoke(user)
                }
            }
        }
        fun bind(user: User) {
            binding.user = user
        }
    }

    fun filterUser(constraint: CharSequence?):MutableList<User>{
        val charString = constraint.toString()
        listFilted = if (charString.isEmpty()) {
            data
        } else {
            val listFilter = mutableListOf<User>()
            data.forEach {
                if (it.name.toLowerCase().contains(charString.toLowerCase()) ||
                    it.username.toLowerCase().contains(charString.toLowerCase())
                ) {
                    listFilter.add(it)
                }
            }
            listFilter
        }

        return listFilted
    }
}