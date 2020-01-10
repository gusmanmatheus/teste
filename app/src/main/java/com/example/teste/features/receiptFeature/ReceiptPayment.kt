package com.example.teste.features.receiptFeature

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.teste.R
import com.example.teste.databinding.FragmentReceiptPaymentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReceiptPayment() : BottomSheetDialogFragment() {
    lateinit var binding: FragmentReceiptPaymentBinding
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_receipt_payment,
            null,
            true
        )
        binding.lifecycleOwner = this
        val contentView = binding.root
        dialog.setContentView(contentView)


    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(context, "closed", Toast.LENGTH_LONG).show()
        activity?.finish()

    }
}