package com.example.news.view.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.adapter.HeadlinesAdapter
import com.example.news.model.Article
import com.example.news.viewmodel.ApiInterface
import com.example.news.viewmodel.Retrofit
import kotlinx.android.synthetic.main.fragment_headlines.*
import kotlinx.android.synthetic.main.item_holder.*
import retrofit2.Call
import retrofit2.Response
import java.lang.IllegalArgumentException


class Headlines : Fragment() {

    private lateinit var mAdapter: HeadlinesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_headlines, container, false)


        return view
    }


    override fun onResume() {
        super.onResume()

        val api = Retrofit.retrofit.create(ApiInterface::class.java)

        api.getNews().enqueue(object : retrofit2.Callback<com.example.news.model.Response> {
            override fun onResponse(call: Call<com.example.news.model.Response>, response: Response<com.example.news.model.Response>) {
                //Log.d(TAG, Gson().toJson(response.body()))

                val newsList = response.body()!!.articles
                mAdapter = HeadlinesAdapter(newsList, context!!)
                val mLayoutManager = LinearLayoutManager(context)
                headlines_rv.layoutManager = mLayoutManager
                headlines_rv.itemAnimator = DefaultItemAnimator()
                headlines_rv.adapter = mAdapter


                mAdapter.setOnItemClickListener {
                    val bundle = Bundle().apply {
                        putSerializable("url", it.url)
                    }

                    try {
                        findNavController().navigate(R.id.action_headlines_to_details, bundle)
                    }
                    catch (e: IllegalArgumentException){}
                }

            }
            override fun onFailure(call: Call<com.example.news.model.Response>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}