package com.example.cbrfxml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.cbrfxml.data.DataStorage
import com.example.cbrfxml.data.Usd

class UsdViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Usd>>()
    private val valueList = DataStorage.getUsdList()

    val items: LiveData<List<Usd>> get() = _items

    init {
        _items.value = valueList
    }

    private val _reqRes = MutableLiveData<String>()
    val reqRes: LiveData<String> get() = _reqRes

    /** Запрос курса на дату и сравнение с прогнозным курсом **/
    fun compareCbrUsd(year: Int, month: Int, dayOfMonth: Int) {
        viewModelScope.launch {
            // Дата в формате API дд.мм.гггг. 0 = January.
            val reqDate: String = if (dayOfMonth < 10) { "0" } else { "" } + dayOfMonth + "/" +
                    if (month < 10) { "0" } else { "" } + (month + 1) + "/" + year

            // Запрос по дате к API
            val cbrValue = CbrApiImpl.getCurr(reqDate)[0].value

            // Сравнение результата со значением в этот день в прогнозе, сообщение о результате
            _reqRes.value = "На $reqDate: $cbrValue " + if (cbrValue == valueList[dayOfMonth-1].value)
            {
                " [V] - совпал с прогнозом"
            } else {
                " [X] - отличается от прогноза"
            }
        }
    }
}