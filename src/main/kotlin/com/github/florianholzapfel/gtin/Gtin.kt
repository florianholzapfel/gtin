package com.github.florianholzapfel.gtin

fun to13Digits(barcode: String): String {
    return barcode.padStart(13, '0')
}

fun generateCheckDigit(barcode: String): String {
    return ((10 - ((
            to13Digits(barcode)
                .toCharArray()
                .mapIndexed { idx, it -> it.toString().toInt() * if (idx % 2 == 0) 3 else 1 }
                .reduce { acc, it -> acc + it }
            ) % 10)) % 10).toString()
}

fun isGtin(barcode: String): Boolean {
    val rx = """^(\d{12,14}|\d{8})${'$'}""".toRegex()
    return rx.containsMatchIn(barcode)
}

fun isGtinValid(barcode: String): Boolean {
    if (!isGtin(barcode)) {
        return false
    }

    val checkDigit = barcode.takeLast(1)
    val generatedCheckDigit = generateCheckDigit(barcode.take(barcode.length - 1))

    return checkDigit == generatedCheckDigit
}
