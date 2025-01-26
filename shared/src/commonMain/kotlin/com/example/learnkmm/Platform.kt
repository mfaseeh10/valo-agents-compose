package com.example.learnkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform