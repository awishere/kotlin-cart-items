package com.example.sadaparcel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.databinding.ActivityMainBinding


class Home : Fragment() {
  private lateinit var adapter: CardAdapter
  private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateItems()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.apply {
            recyclerView.layoutManager = layoutManager
            layoutManager = GridLayoutManager(context,2)
            adapter= CardAdapter(itemList)
            recyclerView.adapter =adapter
        }

       // recyclerView.setHasFixedSize(true)

    }

    private fun populateItems() {
        val item = Items(
            "White Square",
            12.00,
            12,
        )
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)
        itemList.add(item)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      return inflater.inflate(R.layout.fragment_home, container, false)

    }


}