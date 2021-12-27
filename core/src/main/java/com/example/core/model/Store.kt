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
)