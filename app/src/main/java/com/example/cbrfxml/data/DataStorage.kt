package com.example.cbrfxml.data

import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object DataStorage {
    fun getUsdList(): List<Usd> {
        val current = LocalDateTime.now()
        val formatterMonth = DateTimeFormatter.ofPattern("MM")
        var month = current.format(formatterMonth).toInt()
        val formatterYear = DateTimeFormatter.ofPattern("yyyy")
        var year = current.format(formatterYear).toInt()
        val monthLen = (YearMonth.of(year, Month.of(month))).lengthOfMonth()
        val initList = mutableListOf<Usd>()
        for (i in 1..monthLen) initList += Usd("$year/$month/$i","74,2926")
        return initList
    }
}
