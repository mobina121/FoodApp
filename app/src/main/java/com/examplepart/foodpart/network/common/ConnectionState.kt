package com.examplepart.foodpart.network.common

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}