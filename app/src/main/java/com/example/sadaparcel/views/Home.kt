package com.example.sadaparcel.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.adapters.CardAdapter
import com.example.sadaparcel.R
import com.example.sadaparcel.adapters.ItemInterface
import com.example.sadaparcel.models.Items
import com.example.sadaparcel.viewmodels.HomeVM


class Home : Fragment(), ItemInterface {

    private lateinit var recyclerView: RecyclerView
    private var homeVM: HomeVM? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM = ViewModelProvider(this).get(HomeVM::class.java)
        homeVM?.getItemsList()?.observe(viewLifecycleOwner, Observer { itemList ->
            recyclerView = view.findViewById(R.id.recyclerView)
            recyclerView.apply {
                recyclerView.layoutManager = layoutManager
                layoutManager = GridLayoutManager(context, 2)
                recyclerView.setHasFixedSize(true)
                adapter = CardAdapter(itemList, this@Home)
                recyclerView.adapter = adapter
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun addItem(items: Items) {
        homeVM?.addItemToCart(items)!!
    }


}