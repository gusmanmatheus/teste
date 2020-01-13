package com.example.teste.features.receiptFeature

import android.app.Dialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.teste.R
import com.example.teste.data.model.Receipt
import com.example.teste.databinding.FragmentReceiptPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReceiptPayment(private val receipt: Receipt) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentReceiptPaymentBinding
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_receipt_payment,
            null,
            true
        )
        binding.receipt = receipt
        binding.lifecycleOwner = this
        val contentView = binding.root
        dialog.setContentView(contentView)


    }

    override fun onDestroy() {
        super.onDestroy()
         activity?.finish()

    }
}