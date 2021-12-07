package com.example.baseapplication.views.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseapplication.R
import com.example.baseapplication.databinding.ActivityMainBinding
import com.example.baseapplication.models.quotes.GetRandomQuotes
import com.example.baseapplication.utils.RequestHandler
import com.example.baseapplication.utils.showShort
import com.example.baseapplication.viewModels.MainViewModel
import com.example.baseapplication.views.Adapters.QuotesAdapter
import com.example.baseapplication.views.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesActivity :
    BaseActivity<MainViewModel/*, ActivityMainBinding*/>(MainViewModel::class.java) {

    private lateinit var binding: ActivityMainBinding
    private lateinit var getRandomQuotesList: java.util.ArrayList<GetRandomQuotes>

    var adpter = QuotesAdapter(ArrayList<GetRandomQuotes>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getQuotes()


        binding.quotesRV.layoutManager = GridLayoutManager(this, 2)
        binding.quotesRV.adapter = adpter

        binding.backBtnQuotesLayout.setOnClickListener {
            finish()
        }

    }

    override fun onClick(view: View, obj: Any) {}

    override fun onLoading(obj: RequestHandler) {
        showPDialog()
    }

    override fun onSuccess(obj: RequestHandler) {
        dismissDialog()
        val availableRedeemsModelItem = obj.any as java.util.ArrayList<GetRandomQuotes>
        adpter.updateList(availableRedeemsModelItem)

    }

    override fun onError(obj: RequestHandler) {
        dismissDialog()
    }

    override fun initViewModel(viewModel: MainViewModel) {}

}