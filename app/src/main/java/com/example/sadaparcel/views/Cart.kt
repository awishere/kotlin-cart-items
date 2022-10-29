package com.example.sadaparcel.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sadaparcel.R
import com.example.sadaparcel.adapters.CartCardAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sadaparcel.adapters.CartInterface
import com.example.sadaparcel.models.CartItems
import com.example.sadaparcel.viewmodels.HomeVM


class Cart : Fragment(), CartInterface {


    private lateinit var recyclerView: RecyclerView
    private lateinit var total: TextView
    private lateinit var confirmBtn: Button


    private var homeVM: HomeVM? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeVM = ViewModelProvider(this).get(HomeVM::class.java)
        homeVM?.getCartList()?.observe(viewLifecycleOwner, Observer { cartList ->
            homeVM?.getTotalPrice()?.observe(viewLifecycleOwner, Observer { price ->
                total = view.findViewById(R.id.amountTotal)
                ("$" + price.toInt().toString()).also { total.text = it }
                confirmBtn = view.findViewById(R.id.confirm_button)
                if (price == null || price < 1) {
                    confirmBtn.isEnabled = false
                }

            })
            recyclerView = view.findViewById(R.id.recyclerViewCart)
            recyclerView.apply {
                recyclerView.layoutManager = layoutManager
                layoutManager = LinearLayoutManager(context)
                recyclerView.setHasFixedSize(true)
                adapter = CartCardAdapter(cartList, this@Cart)
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

    override fun onAddBtnClick(cartItems: CartItems, stock: Int) {
        if (stock < 1) {
            homeVM?.removeItemFromCart(cartItems)
        }
        homeVM?.changeQuantity(cartItems, stock)!!
    }


}