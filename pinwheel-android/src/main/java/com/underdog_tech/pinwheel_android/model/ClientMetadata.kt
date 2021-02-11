package com.underdog_tech.pinwheel_android.model

data class ClientMetadata(
    val os: Int,
    val manufacturer: String,
    val model: String,
    val product: String,
    val device: String,
    val hardware: String,
    val hasOnLogin: Boolean,
    val hasOnSuccess: Boolean,
    val hasOnError: Boolean,
    val hasOnExit: Boolean,
    val hasOnEvent: Boolean,
)
