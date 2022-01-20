package com.example.cbrfxml.data

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement

@Xml // (name = "ValCurs")
// Регистр символов, уровни тегов, полная иерархия важны для TickXML
data class ApiDatas constructor( // @JvmOverloads
        @Attribute(name = "Date") val date : String?,
        @Attribute(name = "name") val name : String?,
        @Element(name = "Valute") val valute: List<Valute>
)
@Xml // (name = "Valute")
data class Valute constructor(
@Attribute(name = "ID") val id : String?,
        @PropertyElement(name = "NumCode") val numcode: String?,
        @PropertyElement(name = "CharCode") val charcode: String?,
        @PropertyElement(name = "Nominal") val nominal: String?,
        @PropertyElement(name = "Name") val name: String?,
        @PropertyElement(name = "Value") val value: String?
)
