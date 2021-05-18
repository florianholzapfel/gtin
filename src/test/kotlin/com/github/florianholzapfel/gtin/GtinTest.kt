package com.github.florianholzapfel.gtin

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import kotlin.test.Test

class GtinTest {
    @Test
    fun testIsGtin() {
        val list = arrayOf(
            Pair("abcdabcdabcd", false),
            Pair("1234", false),
            Pair("123412341", false),
            Pair("12341234123412341", false),
            Pair("12341234", true),
            Pair("123412341234", true),
            Pair("1234123412343", true),
            Pair("12341234123434", true)
        )

        for (it in list) {
            val gtin = isGtin(it.first)
            expectThat(gtin).isEqualTo(it.second)
        }
    }

    @Test
    fun testIsValid() {
        val list = arrayOf(
            "12341238",
            "012000007897",
            "1234123412344",
            "12341234123413"
        )

        for (it in list) {
            expectThat(isGtinValid(it)).isTrue()
        }
    }

    @Test
    fun testPartialBarcodeIsValid() {
        val list = arrayOf(
            Pair("1234123", 8),
            Pair("01200000789", 7),
            Pair("123412341234", 4),
            Pair("1234123412341", 3)
        )

        for (it in list) {
            for (i in 0..9) {
                if (i == it.second) continue
                expectThat(isGtinValid("${it.first}${i}")).isFalse()
            }

            expectThat(isGtinValid("${it.first}${it.second}")).isTrue()
        }
    }
}