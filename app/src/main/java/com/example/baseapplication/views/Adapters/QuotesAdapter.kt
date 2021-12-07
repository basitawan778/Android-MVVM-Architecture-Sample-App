package com.example.baseapplication.views.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapplication.R
import com.example.baseapplication.databinding.QuotesLayoutBinding
import com.example.baseapplication.models.quotes.GetRandomQuotes

class QuotesAdapter(
    val quotesLists: ArrayList<GetRandomQuotes>
) :
    RecyclerView.Adapter<QuotesAdapter.QuotesItemsHolder>() {
    var binding: QuotesLayoutBinding? = null

    inner class QuotesItemsHolder(var view: QuotesLayoutBinding) :
        RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesItemsHolder {

        val inflater = LayoutInflater.from(parent.context)
        binding =
            DataBindingUtil.inflate<QuotesLayoutBinding>(
                inflater,
                R.layout.quotes_layout,
                parent,
                false
            )
        return QuotesItemsHolder(binding!!)
    }

    fun updateList(rewardList: java.util.ArrayList<GetRandomQuotes>) {
//        this.quotesLists.clear()
        this.quotesLists.addAll(rewardList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = quotesLists.size

    override fun onBindViewHolder(holder: QuotesItemsHolder, position: Int) {
      /*  holder.view.item.setOnClickListener {
        }*/
        binding!!.authorNameTV.setText(quotesLists[position].author)
        binding!!.quotesTV.setText(quotesLists[position].quote)
    }
}