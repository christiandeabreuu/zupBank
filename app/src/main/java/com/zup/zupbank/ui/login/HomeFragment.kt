package com.zup.zupbank.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zup.zupbank.R
import com.zup.zupbank.common.extension.viewBinding
import com.zup.zupbank.databinding.FragmentHomeBinding
import com.zup.zupbank.ui.login.Payment.Payment

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var adapterPayment: AdapterPayment
    private val listPayment: MutableList<Payment> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerIconsPayment = binding.rvPaymentIcons
        recyclerIconsPayment.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false,
        )
        recyclerIconsPayment.setHasFixedSize(true)
        adapterPayment = AdapterPayment(requireContext(), listPayment)
        recyclerIconsPayment.adapter = adapterPayment
        listIconsPayment()
    }

    private fun listIconsPayment() {
        val icon1 = Payment(R.drawable.ic_pix, "Área Pix")
        listPayment.add(icon1)

        val icon2 = Payment(R.drawable.barcode, "Pagar")
        listPayment.add(icon2)

        val icon3 = Payment(R.drawable.depositar, "Depositar")
        listPayment.add(icon3)

        val icon4 = Payment(R.drawable.transferencia, "Transferir")
        listPayment.add(icon4)

        val icon5 = Payment(R.drawable.emprestimo, "Pedir")
        listPayment.add(icon5)

        val icon6 = Payment(R.drawable.ic_phone_android, "Recarga")
        listPayment.add(icon6)

        val icon7 = Payment(R.drawable.ic_pix, "Cobrar")
        listPayment.add(icon7)
    }
}
