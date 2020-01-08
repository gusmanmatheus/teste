package com.example.teste.modules

import java.math.BigDecimal
import java.text.NumberFormat


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
    fun maskValue(value: String?):String{
        value?.let{

            val cleanString = it
            val parsed = BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR)
                .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
            return NumberFormat.getCurrencyInstance().format(parsed).replace("\$","")
//            editText.setSelection(formatted.length)
             }
        return ""
}
}