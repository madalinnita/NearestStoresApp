package com.example.core.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "stores", strict = false)
data class Stores @JvmOverloads constructor(@field:ElementList(name = "stores", inline = true, required = false) var stores: List<Store>? = null)