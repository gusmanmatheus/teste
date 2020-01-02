package com.example.teste.features.registerCard

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.teste.R
import com.example.teste.databinding.ActivityRegisterCardBinding
import com.example.teste.modules.changeText
import com.example.teste.modules.verifyFieldHasVoids
import kotlinx.android.synthetic.main.activity_register_card.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import org.koin.android.ext.android.bind


class RegisterCardActivity : AppCompatActivity() {
    private val registerViewModel: RegisterCardViewModel by viewModel()
    lateinit var binding: ActivityRegisterCardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_card)
        binding.viewModel = this.registerViewModel
        binding.lifecycleOwner = this

        controlVisibilityButton()
        observableFields()
        clickRegisterCard()
    }

    private fun observableFields() {
        holderNameEd.changeText {
            controlVisibilityButton()
        }
        numberCardEd.changeText {
            controlVisibilityButton()
        }
        cvvCardEd.changeText {
            controlVisibilityButton()
        }
        expirationDateEd.changeText {
            controlVisibilityButton()
        }
    }

    private fun controlVisibilityButton() {
        RegisterCard.isVisible = !EditText(this).verifyFieldHasVoids(
            holderNameEd,
            numberCardEd,
            cvvCardEd,
            expirationDateEd
        )
    }

    private fun verifyCredCardValide() {
        val approvedCvv =
            registerViewModel.verifyNumeberCharacterCvv(registerViewModel.card.value?.cvvCard ?: "")
        val approvedName = registerViewModel.verifyNameContainsNumber(
            registerViewModel.card.value?.holderName ?: ""
        )
        val approvedDate =
            registerViewModel.verifyValidateDate(registerViewModel.card.value?.expirationDate ?: "")
        val approvedNumber = registerViewModel.verifyNumeberCharacterNumberCard(
            registerViewModel.card.value?.numberCard ?: ""
        )
        if (!approvedCvv) {
              binding.cvvCardTl.error = "cvv invalido"
            binding.cvvCardTl.isErrorEnabled = true


        }
        if (!approvedName) {
              binding.holderNameTl.isErrorEnabled = true
            binding.holderNameTl.error = "name errado"
        }
        if (!approvedNumber) {
              binding.numberCardTl.isErrorEnabled = true
            binding.numberCardTl.error = "numero errado"
        }
        if (!approvedDate) {
              binding.expirationDateTl.isErrorEnabled = true
            binding.expirationDateTl.error = "data errada"
        }

        if (approvedCvv && approvedName
            && approvedNumber && approvedDate
        ) {
            Log.i("xrl8", "passou")
            registerViewModel.saveCard()
        }
    }


    private fun clickRegisterCard() {
        RegisterCard.setOnClickListener {
            this.registerViewModel.saveCard()
            verifyCredCardValide()

        }
    }
}



