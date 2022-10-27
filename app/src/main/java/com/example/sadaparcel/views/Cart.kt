package com.example.sadaparcel.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sadaparcel.R
import com.example.sadaparcel.adapters.CartCardAdapter
import com.example.sadaparcel.models.Items
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.viewmodels.HomeVM



class Cart : Fragment() {


    private lateinit var recyclerView: RecyclerView

    private var homeVM: HomeVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM = ViewModelProvider(this).get(HomeVM::class.java)
        homeVM?.getRes()?.observe(viewLifecycleOwner, Observer { itemList ->
        recyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.apply {
            recyclerView.layoutManager = layoutManager
            layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
            adapter= CartCardAdapter(itemList)
            recyclerView.adapter = adapter
        }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


}