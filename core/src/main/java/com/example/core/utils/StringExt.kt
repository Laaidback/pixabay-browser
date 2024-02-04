package com.example.core.utils

import java.net.URLEncoder

val String.urlEncoded: String
    get() = URLEncoder.encode(this, "utf-8")
