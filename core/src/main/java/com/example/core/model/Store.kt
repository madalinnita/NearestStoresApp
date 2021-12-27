package com.example.core.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root
data class Store @JvmOverloads constructor(
    @field:Element(name = "posId")
    var posId: Int? = null,
    @field:Element(name = "name")
    var name: String? = null,
    @field:Element(name = "address")
    var address: String? = null,
    @field:Element(name = "latitude")
    var latitude: String? = null,
    @field:Element(name = "longitude")
    var longitude: String? = null,
    @field:Element(name = "postalCode")
    var postalCode: String? = null,
    @field:Element(name = "city")
    var city: String? = null,
    @field:Element(name = "distributorId")
    var distributorId: String? = null,
//    @field:Element(name = "contry")
//    var country: String? = null,
    @field:Element(name = "posTypeLogo")
    var posTypeLogo: String? = null,
    @field:Element(name = "productLogo")
    var productLogo: String? = null,
//    @field:Element(name = "specialText")
//    var specialText: String? = null,
    @field:Element(name = "recommended")
    var recommended: String? = null,
    @field:Element(name = "directload")
    var directload: String? = null,
    @field:Element(name = "mdirectload")
    var mdirectload: String? = null
)