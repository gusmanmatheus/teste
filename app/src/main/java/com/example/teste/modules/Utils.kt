package com.example.teste.modules

import android.text.Editable
import android.widget.EditText
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


object Utils {
    fun maskDate(date: String?): String {
        date?.let {
            val dateAux: String = it.replace("/", "")
            return when {
                dateAux.length == 1 -> "${dateAux[0]}"
                dateAux.length == 2 -> "${dateAux[0]}${dateAux[1]}"
                dateAux.length == 3 -> "${dateAux[0]}${dateAux[1]}/${dateAux[2]}"
                dateAux.length == 4 -> "${dateAux[0]}${dateAux[1]}/${dateAux[2]}${dateAux[3]}"
                else -> dateAux
            }
        }
        return ""
    }

    fun maskNumberCard(numberCard: String?): String {
        numberCard?.let {
            val numberCardAux: String = it.replace("  ", "")
            var numberCardAux1 = ""
            for (i in numberCardAux.indices) {
                if (i % 4 == 0 && numberCardAux1 != "") {
                    numberCardAux1 += "  " + numberCardAux[i]
                } else {
                    numberCardAux1 += numberCardAux[i]
                }
            }
            return numberCardAux1
        }
        return ""
    }

    fun maskValue(editText: EditText): String {
         val typedText = editText.editableText.toString()
        val parsed: BigDecimal

        parsed = if (typedText.isEmpty()) {
            BigDecimal("0.00")
        } else {
            val cleanString = typedText.replace("[,. ]".toRegex(), "")
            BigDecimal(cleanString).divide(BigDecimal("100"))
        }

        val formatted = moneyFormatterForTextWatcher(parsed)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
     return  ""
}
private fun moneyFormatterForTextWatcher(number: BigDecimal): String {
    val fmt = NumberFormat.getInstance(Locale("por", "BR")) as DecimalFormat
    fmt.isGroupingUsed = true
    fmt.minimumFractionDigits = 2
    fmt.maximumFractionDigits = 2
    return fmt.format(number)
}


fun cleanMoneyText(text: String): Double{
    return if(text.isNotEmpty()) {
        text.replace("[.]".toRegex(), "").replace("[,]".toRegex(), ".").toDouble()
    }else{
        0.0
    }
}
}