package com.zup.zupbank.ui.login

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zup.zupbank.databinding.ItemPaymentBinding
import com.zup.zupbank.ui.login.Payment.Payment

class AdapterPayment(private val context: Context, private val listPayment: MutableList<Payment>) :
    RecyclerView.Adapter<AdapterPayment.PaymentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val itemList = ItemPaymentBinding.inflate(LayoutInflater.from(context), parent, false)
        return PaymentViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.iconPayment.setBackgroundResource(listPayment[position].iconPayment!!)
        holder.txtTitle.text = listPayment[position].title
    }

    override fun getItemCount() = listPayment.size

    inner class PaymentViewHolder(binding: ItemPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconPayment = binding.icPayment
        val txtTitle = binding.txtPayment
    }
}
