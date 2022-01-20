package com.example.cbrfxml

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
//import retrofit2.http.QueryName
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import com.example.cbrfxml.data.ApiDatas
import com.example.cbrfxml.data.Curr

interface CbrApi {
    @GET("/scripts/XML_daily.asp")
    suspend fun getCurr(@Query("date_req") date_req: String): ApiDatas
}

// Использую актуальный парсер XML - Tickaroo/tikxml
object CbrApiImpl {
    const val BASE_URL = "https://www.cbr.ru"
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(TikXmlConverterFactory.create())
        .build()

    private val CbrService = retrofit.create(CbrApi::class.java)

    /** Получение из REST API списка валют, фильтр по USD **/
    suspend fun getCurr(date_req: String): List<Curr> {
        return withContext(Dispatchers.IO) {
            CbrService.getCurr(date_req)
                .valute
                .filter { it.charcode == "USD" }
                .map { result ->
                    Curr(
                        result.charcode,
                        result.value
                    )
                }
        }
    }
}